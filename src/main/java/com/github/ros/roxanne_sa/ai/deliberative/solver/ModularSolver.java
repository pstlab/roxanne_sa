package com.github.ros.roxanne_sa.ai.deliberative.solver;

import java.util.HashSet;
import java.util.Set;

import com.github.ros.roxanne_sa.ai.deliberative.strategy.ex.EmptyFringeException;
import com.github.ros.roxanne_sa.ai.framework.domain.component.PlanElementStatus;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.ConsistencyCheckException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.NoFlawFoundException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.NoSolutionFoundException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.PlanRefinementException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.flaw.Flaw;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.flaw.FlawType;
import com.github.ros.roxanne_sa.ai.framework.microkernel.resolver.ex.UnsolvableFlawException;

/**
 * 
 * @author anacleto
 *
 */
public abstract class ModularSolver extends Solver 
{
	protected FlawType[] fTypes;			// type of flaws managed
	protected SearchSpaceNode last;			// last solution node found
	
	/**
	 * 
	 * @param label
	 * @param timeout
	 */
	protected ModularSolver(String label, long timeout) {
		super(label, timeout);
		// set last node to null
		this.last = null;
	}
	
	
	/**
	 * 
	 */
	@Override
	public SearchSpaceNode solve() 
			throws NoSolutionFoundException 
	{
		// set the search space
		this.last = null;
		// clear the fringe
		this.fringe.clear();
		// create the root node
		SearchSpaceNode root = this.createSearchSpaceNode();
		// add the root to the fringe
		this.fringe.enqueue(root);
		// find a solution to the planning problem
		return this.doSearchNextSolution();
	}
	
	/**
	 * 
	 * @return
	 * @throws NoSolutionFoundException
	 */
	public SearchSpaceNode next()
			throws NoSolutionFoundException
	{
		// prepare the search 
		if (this.last == null) {
			// clear the search space
			this.fringe.clear();
			// set the search space for the first run
			SearchSpaceNode root = this.createSearchSpaceNode();
			// add the root node to the fringe
			this.fringe.enqueue(root);
		}
		
		// ignore timeout when using as solving module
		this.timeout = -1;
		// find a solution 
		SearchSpaceNode solution = this.doSearchNextSolution();
		// set solution as last node
		this.last = solution;
		// get the solution 
		return solution;
	}
	
	/**
	 * Restore the search of a solution. 
	 * 
	 * This method is typically used when some "external" changes in the 
	 * plan database occurs and the search should be restored starting 
	 * from the last (solution) node previously computed.  
	 * 
	 * @return
	 * @throws NoSolutionFoundException
	 */
	public SearchSpaceNode restore() 
			throws NoSolutionFoundException
	{
		// ignore timeout when using as solving module
		this.timeout = -1;
		// find a solution
		SearchSpaceNode solution = this.doRestoreSearchNextSolution();
		// set solution as last node
		this.last = solution;
		// get the solution
		return solution;
	}


	/**
	 * Abort all the refinements performed by the solver on the plan database.
	 * 
	 * It retract all the operators associated to the solution node
	 * computed by the solver.
	 */
	public void rollback() 
	{
		// check the last node
		if (this.last != null) 
		{
			// backtrack all associated operators
			this.backtrack(this.last);
			// clear last node
			this.last = null;
			// clear the search space
			this.fringe.clear();
		}
	}
	
	/**
	 * 
	 * @return
	 * @throws NoSolutionFoundException
	 */
	protected SearchSpaceNode doSearchNextSolution() 
			throws NoSolutionFoundException 
	{
		// set solving start time
		long start = System.currentTimeMillis();
		// set solving step counter
		this.stepCounter = 0;
		// current node
		SearchSpaceNode current = null;
		// search condition
		boolean search = true;
		// search a solution
		while (search) 
		{
			try 
			{
				// update step counter
				this.stepCounter++;
				// get time passed from the start 
				long now = System.currentTimeMillis() - start;
				// check timeout
				if (this.timeout > 0 && now > this.timeout) 
				{
					// no solution found stop search
					search = false;
					// set solving time
					this.time = System.currentTimeMillis() - start;
					// backtrack from the last propagated node
					this.backtrack(this.last);
					// timeout exception
					throw new NoSolutionFoundException("Timeout: no solution found after " + this.time + " msecs and " + this.stepCounter + " solving steps");
				}
				
				// extract a node from the fringe
				current = this.fringe.dequeue();
				info("Solving step: " + this.stepCounter +"\n"
						+ "- Extracted node: " + current + "\n"
						+ "- Applied operator: " + current.getGenerator() + "\n");
				
				// propagate extracted node
				this.contextSwitch(this.last, current);
				// check consistency of the resulting partial plan
				this.pdb.verify();
				// updated previous node
				this.last = current;
				
				// print information concerning current partial plan	
				info("Partial plan after propagation of operator: "  + current.getGenerator() + "\n"
							+ "- plan:\n"
							+ "---- decisions= " + this.pdb.getPlan().getDecisions() + "\n"
							+ "---- relations= " + this.pdb.getPlan().getRelations() + "\n\n"
							+ "- pending plan (agenda):\n"
							+ "---- decisions= " + this.pdb.getPlan(PlanElementStatus.PENDING).getDecisions() + "\n"
							+ "---- relations= " + this.pdb.getPlan(PlanElementStatus.PENDING).getRelations() + "\n\n"
							+ "- silent plan:\n"
							+ "---- decisions= " + this.pdb.getPlan(PlanElementStatus.SILENT).getDecisions() + "\n"
							+ "---- relations= " + this.pdb.getPlan(PlanElementStatus.SILENT).getRelations() + "\n\n");

				
				
				// detect PDB flaws by type
				Set<Flaw> flaws = new HashSet<>();
				for (FlawType type : this.fTypes) {
					// get flaw by type
					flaws.addAll(this.pdb.detectFlaws(type));
				}
				
				
 				// use the heuristics to filter detected flaws
				flaws = this.heuristic.filter(new HashSet<Flaw>(flaws));
				// create a branch for each "equivalent" flaw to solve next
				for (Flaw flaw : flaws)
				{
					// expand the search space with the available solutions of the flaw
					for (SearchSpaceNode child : this.expand(this.last, flaw)) {
						// add the node to the fringe
						this.fringe.enqueue(child);
						// expand the search space
						info("Search tree expansion:\n- node: " + child + "\n"
								+ "- generator: " + child.getGenerator() + "\n");
					}
				}
			}
			catch (PlanRefinementException ex) {
				// error while refining the current plan
				warning("Error while refining the current plan\n"
						+ "- operator: " + current.getGenerator() + "\n"
						+ "- message: " + ex.getMessage() + "\n");
			}
			catch (UnsolvableFlawException | ConsistencyCheckException  ex) {
				// not feasible partial plan
				warning("Not feasible partial plan found\n"
						+ "- oeprator: " + current.getGenerator() + "\n"
						+ "- plan:\n"
							+ "---- decisions= " + this.pdb.getPlan().getDecisions() + "\n"
							+ "---- relations= " + this.pdb.getPlan().getRelations() + "\n"
						+ "- agenda:\n"
							+ "---- decisions= " + this.pdb.getPlan(PlanElementStatus.PENDING).getDecisions() + "\n"
							+ "---- relations= " + this.pdb.getPlan(PlanElementStatus.PENDING).getRelations() + "\n\n");
			}
			catch (NoFlawFoundException ex)
			{
				// solution found stop search
				search = false;
				
				// set solving time
				this.time = System.currentTimeMillis() - start;
				// pseudo-controllable solution found
				info("Pseudo-controllable solution found after " + (this.time / 1000) + " (secs) and " + this.stepCounter + " solving steps\n");
			}
			catch (EmptyFringeException ex) 
			{
				// no solution found stop search
				search = false;
				// set solving time
				this.time = System.currentTimeMillis() - start;
				// backtrack from the last propagated node
				this.backtrack(this.last);
				// clear last node
				this.last = null;
				// throw exception
				throw new NoSolutionFoundException("No pseudo-controllable solution found after " + (this.time / 1000) + " (secs) and " + this.stepCounter + " solving steps\n");
			}
			
		} // end while
		
		// get last expanded node
		return this.last;
	}
	
	/**
	 * 
	 * @return
	 * @throws NoSolutionFoundException
	 */
	protected SearchSpaceNode doRestoreSearchNextSolution() 
			throws NoSolutionFoundException 
	{
		// set solving start time
		long start = System.currentTimeMillis();
		// set solving step counter
		this.stepCounter = 0;
		// current node
		SearchSpaceNode current = null;
		// search condition
		boolean search = true;
		// search a solution
		while (search) 
		{
			try 
			{
				// update step counter
				this.stepCounter++;
				// get time passed from the start 
				long now = System.currentTimeMillis() - start;
				// check timeout
				if (this.timeout > 0 && now > this.timeout) 
				{
					// no solution found stop search
					search = false;
					// set solving time
					this.time = System.currentTimeMillis() - start;
					// backtrack from the last propagated node
					this.backtrack(this.last);
					// timeout exception
					throw new NoSolutionFoundException("Timeout: no solution found after " + this.time + " msecs and " + this.stepCounter + " solving steps");
				}
				
				
				// at the beginning take into account the last computed solution
				if (this.stepCounter == 1) {
					// set the current as the last node
					current = this.last;
				}
				else {
					// extract a node from the fringe
					current = this.fringe.dequeue();
				}
				
				info("Solving step: " + this.stepCounter +"\n"
						+ "- Extracted node: " + current + "\n"
						+ "- Applied operator: " + current.getGenerator() + "\n");
				
				// propagate extracted node
				this.contextSwitch(this.last, current);
				// check consistency of the resulting partial plan
				this.pdb.verify();
				// updated previous node
				this.last = current;
				
				// print information concerning current partial plan	
				info("Partial plan after propagation of operator: "  + current.getGenerator() + "\n"
							+ "- plan:\n"
							+ "---- decisions= " + this.pdb.getPlan().getDecisions() + "\n"
							+ "---- relations= " + this.pdb.getPlan().getRelations() + "\n\n"
							+ "- pending plan (agenda):\n"
							+ "---- decisions= " + this.pdb.getPlan(PlanElementStatus.PENDING).getDecisions() + "\n"
							+ "---- relations= " + this.pdb.getPlan(PlanElementStatus.PENDING).getRelations() + "\n\n"
							+ "- silent plan:\n"
							+ "---- decisions= " + this.pdb.getPlan(PlanElementStatus.SILENT).getDecisions() + "\n"
							+ "---- relations= " + this.pdb.getPlan(PlanElementStatus.SILENT).getRelations() + "\n\n");

				
				
				// detect PDB flaws by type
				Set<Flaw> flaws = new HashSet<>();
				for (FlawType type : this.fTypes) {
					// get flaw by type
					flaws.addAll(this.pdb.detectFlaws(type));
				}
				
				
 				// use the heuristics to filter detected flaws
				flaws = this.heuristic.filter(new HashSet<Flaw>(flaws));
				// create a branch for each "equivalent" flaw to solve next
				for (Flaw flaw : flaws)
				{
					// expand the search space with the available solutions of the flaw
					for (SearchSpaceNode child : this.expand(this.last, flaw)) {
						// add the node to the fringe
						this.fringe.enqueue(child);
						// expand the search space
						info("Search tree expansion:\n- node: " + child + "\n"
								+ "- generator: " + child.getGenerator() + "\n");
					}
				}
			}
			catch (PlanRefinementException ex) {
				// error while refining the current plan
				warning("Error while refining the current plan\n"
						+ "- operator: " + current.getGenerator() + "\n"
						+ "- message: " + ex.getMessage() + "\n");
			}
			catch (UnsolvableFlawException | ConsistencyCheckException  ex) {
				// not feasible partial plan
				warning("Not feasible partial plan found\n"
						+ "- oeprator: " + current.getGenerator() + "\n"
						+ "- plan:\n"
							+ "---- decisions= " + this.pdb.getPlan().getDecisions() + "\n"
							+ "---- relations= " + this.pdb.getPlan().getRelations() + "\n"
						+ "- agenda:\n"
							+ "---- decisions= " + this.pdb.getPlan(PlanElementStatus.PENDING).getDecisions() + "\n"
							+ "---- relations= " + this.pdb.getPlan(PlanElementStatus.PENDING).getRelations() + "\n\n");
			}
			catch (NoFlawFoundException ex)
			{
				// solution found stop search
				search = false;
				
				// set solving time
				this.time = System.currentTimeMillis() - start;
				// pseudo-controllable solution found
				info("Pseudo-controllable solution found after " + (this.time / 1000) + " (secs) and " + this.stepCounter + " solving steps\n");
			}
			catch (EmptyFringeException ex) 
			{
				// no solution found stop search
				search = false;
				// set solving time
				this.time = System.currentTimeMillis() - start;
				// backtrack from the last propagated node
				this.backtrack(this.last);
				// clear last node
				this.last = null;
				// throw exception
				throw new NoSolutionFoundException("No pseudo-controllable solution found after " + (this.time / 1000) + " (secs) and " + this.stepCounter + " solving steps\n");
			}
			
		} // end while
		
		// get last expanded node
		return this.last;
	}
}

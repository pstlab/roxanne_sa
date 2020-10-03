package com.github.ros.roxanne_sa.ai.deliberative.solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.ros.roxanne_sa.ai.deliberative.heuristic.FlawSelectionHeuristic;
import com.github.ros.roxanne_sa.ai.deliberative.strategy.SearchStrategy;
import com.github.ros.roxanne_sa.ai.framework.domain.component.PlanDataBase;
import com.github.ros.roxanne_sa.ai.framework.microkernel.FrameworkObject;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject.deliberative.FlawSelectionHeuristicPlaceholder;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject.deliberative.SearchStrategyPlaceholder;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject.framework.PlanDataBasePlaceholder;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.lifecycle.PostConstruct;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.ConsistencyCheckException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.NoSolutionFoundException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.OperatorPropagationException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.PlanRefinementException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.flaw.Flaw;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.flaw.FlawSolution;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.plan.Plan;
import com.github.ros.roxanne_sa.ai.framework.microkernel.resolver.ex.UnsolvableFlawException;

/**
 * 
 * @author anacleto
 *
 */
public abstract class Solver extends FrameworkObject 
{
	@PlanDataBasePlaceholder
	protected PlanDataBase pdb;
	
	@SearchStrategyPlaceholder
	protected SearchStrategy fringe;
	
	@FlawSelectionHeuristicPlaceholder
	protected FlawSelectionHeuristic heuristic;
	
	protected long timeout;
	protected long time;
	protected long stepCounter;
	protected String label;
	
	/**
	 * 
	 * @param label
	 * @param timeout
	 */
	protected Solver(String label, long timeout) {
		super();
		this.label = label;
		this.timeout = timeout;
	}
	
	/**
	 * 
	 */
	@PostConstruct
	protected void init() {
		// create the root node
		SearchSpaceNode root = new SearchSpaceNode();
		// set initial partial plan
		root.setPartialPlan(this.pdb.getPlan());
		// check flaws
		for (Flaw f : this.pdb.checkFlaws()) {
			root.addCheckedFlaw(f);
		}
		
		// enqueue the root node
		this.fringe.enqueue(root);
	}
	
	/**
	 * 
	 * @return
	 * @throws NoSolutionFoundException
	 */
	public abstract SearchSpaceNode solve() 
			throws NoSolutionFoundException;
	
	/**
	 * 
	 * @return
	 */
	protected SearchSpaceNode createSearchSpaceNode() {
		return new SearchSpaceNode();
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * 
	 * @param node
	 */
	protected void backtrack(SearchSpaceNode node) 
	{
		// list of operators that have been applied to generate the node
		List<Operator> operators = node.getOperators();
		// retract operators starting from the more recent ones
		Collections.reverse(operators);
		// retract all operators
		for (Operator operator : operators) {
			// retract operator
			this.pdb.retract(operator);
		}
	}
	
	/**
	 * 
	 * @param node
	 * @throws OperatorPropagationException
	 */
	protected void propagate(SearchSpaceNode node) 
			throws PlanRefinementException 
	{
		// get the list of applied operators
		List<Operator> operators = node.getOperators();
		// list of committed operators
		List<Operator> committed = new ArrayList<>();
		try 
		{
			// propagate operators in chronological order
			for (Operator operator : operators) {
				// propagate operator
				this.pdb.propagate(operator);
				// add committed operator
				committed.add(operator);
			}
		}
		catch (OperatorPropagationException ex) {
			// retract committed operators in reverse order
			Collections.reverse(committed);
			for (Operator operator : committed) {
				this.pdb.retract(operator);
			}

			// throw exception
			throw new PlanRefinementException("Error while propagating node:\n" + node + "\n- message: " + ex.getMessage() + "\n");
		}
	}
	
	/**
	 * 
	 * @param last
	 * @param extracted
	 * @throws PlanRefinementException
	 */
	protected void contextSwitch(SearchSpaceNode last, SearchSpaceNode extracted) 
			throws PlanRefinementException 
	{
		// compare the two nodes
		if (last != null) 
		{
			// prepare a list of operators to retract 
			List<Operator> toRetract = new ArrayList<>();
			// prepare a list of operators to propagate
			List<Operator> toPropagate = new ArrayList<>();
			
			// get the list of operators of the nodes
			List<Operator> lastNodeOperators = last.getOperators();
			List<Operator> extractedNodeOperators = extracted.getOperators();
			// check min length between the two lists
			int minLength = Math.min(lastNodeOperators.size(), extractedNodeOperators.size());
			// check potentially common operators
			boolean common = true;
			for (int i = 0; i < minLength; i++) {
				// check common flag
				if (common && !lastNodeOperators.get(i).equals(extractedNodeOperators.get(i))) {
					common = false;
				}
				
				// check if no common operators have been found
				if (!common) {
					// add operator to the different lists
					toRetract.add(lastNodeOperators.get(i));
					toPropagate.add(extractedNodeOperators.get(i));
				}
			}
			
			// check other operators to retract
			for (int i = minLength; i < lastNodeOperators.size(); i++) {
				toRetract.add(lastNodeOperators.get(i));
			}
			
			// check other operators to propagate
			for (int i = minLength; i < extractedNodeOperators.size(); i++) {
				toPropagate.add(extractedNodeOperators.get(i));
			}
			
			
			// retract operators in reverse order
			Collections.reverse(toRetract);
			// retract all operators
			for (Operator operator : toRetract) {
				// retract operator
				this.pdb.retract(operator);
			}
			
			// list of committed operators
			List<Operator> committed = new ArrayList<>();
			try 
			{
				// propagate operators in chronological order
				for (Operator operator : toPropagate) {
					// propagate operator
					this.pdb.propagate(operator);
					// add committed operator
					committed.add(operator);
				}
			}
			catch (OperatorPropagationException  ex) {
				// retract committed operators in reverse order
				Collections.reverse(committed);
				for (Operator operator : committed) {
					this.pdb.retract(operator);
				}

				// throw exception
				throw new PlanRefinementException("Error while propagating node:\n" + extracted + "\n- message: " + ex.getMessage() + "\n");
			}
		}
		else {
			// simply propagate node extracted node
			this.propagate(extracted);
		}
	}
	
	/**
	 * 
	 * Expand the search space by adding a new node for each solution of the flaw selected for 
	 * plan refinement
	 * 
	 * @param current
	 * @param flaw
	 * @return
	 * @throws UnsolvableFlawException
	 */
	protected List<SearchSpaceNode> expand(SearchSpaceNode current, Flaw flaw) 
			throws UnsolvableFlawException
	{
		// list of child nodes
		List<SearchSpaceNode> list = new ArrayList<>();
		// check flaw solutions
		for (FlawSolution solution : flaw.getSolutions()) 
		{
			// create operator
			Operator op = new Operator(solution);
			try
			{
				// try to propagate operator
				this.pdb.propagate(op);
				// check plan database feasibility
				this.pdb.verify();
				
				// get plan with updated temporal bounds and variable binding
				Plan plan = this.pdb.getPlan();
				// create a child search space node
				SearchSpaceNode child = new SearchSpaceNode(current, op);
				// set partial plan
				child.setPartialPlan(plan);
				// look ahead of flaws representing the agenda of the node
				for (Flaw f : this.pdb.checkFlaws()) {
					// add checked flaw
					child.addCheckedFlaw(f);
				}

				// add child
				list.add(child);
			}
			catch (OperatorPropagationException | ConsistencyCheckException ex) {
				warning("Invalid operator found during search expansion phase:\n"
						+ "- current node: " + current + "\n"
						+ "- flaw: " + flaw + "\n"
						+ "- failed operator: " + op + "\n");
				
				// debug
				System.err.println("Invalid operator found during search expansion phase:\n"
						+ "- current node: " + current + "\n"
						+ "- flaw: " + flaw + "\n"
						+ "- failed operator: " + op + "\n");
			}
			finally {
				// retract operator
				this.pdb.retract(op);
			}
		}
		
		// check if no expansion has done
		if (list.isEmpty()) {
			// this is for debug mainly, as this condition should never occur. Computed flaw solutions should be valid 
			throw new UnsolvableFlawException("Search space expansion failure as no valid operator can be applied:\n"
					+ "- current node: " + current + "\n"
					+ "- flaw: " + flaw + "\n");
		}
		
		// get children
		return list;
	}
}

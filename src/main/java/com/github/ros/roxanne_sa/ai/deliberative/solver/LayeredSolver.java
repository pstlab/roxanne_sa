package com.github.ros.roxanne_sa.ai.deliberative.solver;

import com.github.ros.roxanne_sa.ai.deliberative.heuristic.HierarchicalFlawSelectionHeuristic;
import com.github.ros.roxanne_sa.ai.deliberative.strategy.CostDepthSearchStrategy;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.FlawSelectionHeuristicsConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.ModularSolverConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.SearchStrategyConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.NoSolutionFoundException;


/**
 * 
 * @author alessandroumbrico
 *
 */
@FlawSelectionHeuristicsConfiguration(
	heuristics = HierarchicalFlawSelectionHeuristic.class
)
@SearchStrategyConfiguration(
		strategy = CostDepthSearchStrategy.class
)
public class LayeredSolver extends Solver 
{
	@ModularSolverConfiguration(
			solver =  PlanningLayerSolver.class)
	private ModularSolver pSolver;								// planning solver layer
	
	@ModularSolverConfiguration(
			solver = SchedulingLayerSolver.class)
	private ModularSolver sSolver;								// scheduling solver layer
	
	@ModularSolverConfiguration(
			solver = BehaviorLayerSolver.class)
	private ModularSolver bSolver;								// behavior solver layer
	
	
	/**
	 * 
	 * @param timeout
	 */
	protected LayeredSolver(long timeout) {
		super("LayeredSolver", timeout);
	}
	
	/**
	 * 
	 */
	@Override
	protected void init() {
		
		/*
		 * TODO : TO BE IMPLEMENTED
		 */
	}
	
	/**
	 * 
	 */
	@Override
	public SearchSpaceNode solve() 
			throws NoSolutionFoundException 
	{
		// count the step of the layered solver
		int counter = 0;
		// skip planning flag
		boolean skipPlanning = false;
		// set solution
		SearchSpaceNode solution = null;
		// run until a solution is found
		while (solution == null)
		{
			// increment step counter
			counter++;
			// check skip planning flag
			if (!skipPlanning) {
				// call the planning solver layer
				SearchSpaceNode s1 = this.pSolver.next();
				info("[" + this.label + "][step " + counter + "] Planning solution: " + s1 + "\n");
			}
			
			// further refine the current plan through scheduling
			try
			{
				// search for a scheduling solution
				SearchSpaceNode s2 = this.sSolver.next();
				info("[" + this.label + "][step " + counter + "] Scheduling solution: " + s2 + "\n");
				
				try
				{
					// search for a scheduling solution
					SearchSpaceNode s3 = this.bSolver.next();
					info("[" + this.label + "][step " + counter + "] Behavior solution: " + s3 + "\n");
					// set skip planning flag
					skipPlanning = false;
					//  keep this as solution
					solution = s3;
				}
				catch (NoSolutionFoundException ex) {
					warning("[" + this.label + "][step " + counter + "] No behavior solution found, look for an alternative scheduling solution:\n" + ex.getMessage() + "\n");
					// look for an alternative plan 
					solution = null;
					// abort any scheduling decision made
					this.bSolver.rollback();
					// set skip planning flag
					skipPlanning = true;
				}
			}
			catch (NoSolutionFoundException ex) {
				warning("[" + this.label + "][step " + counter + "] No scheduling solution found, look for an alternative planning solution:\n" + ex.getMessage() + "\n");
				// look for an alternative plan 
				solution = null;
				// abort any scheduling decision made
				this.sSolver.rollback();
			}
		}
		
		
		// get the solution 
		return solution;
	}
}	

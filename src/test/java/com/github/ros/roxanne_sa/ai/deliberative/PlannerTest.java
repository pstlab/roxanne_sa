package com.github.ros.roxanne_sa.ai.deliberative;

import it.cnr.istc.pst.platinum.ai.deliberative.Planner;
import it.cnr.istc.pst.platinum.ai.deliberative.heuristic.pipeline.PipelineFlawSelectionHeuristic;
import it.cnr.istc.pst.platinum.ai.deliberative.solver.PseudoControllabilityAwareSolver;
import it.cnr.istc.pst.platinum.ai.deliberative.strategy.GreedyDepthSearchStrategy;
import it.cnr.istc.pst.platinum.ai.framework.microkernel.annotation.cfg.FrameworkLoggerConfiguration;
import it.cnr.istc.pst.platinum.ai.framework.microkernel.annotation.cfg.deliberative.FlawSelectionHeuristicsConfiguration;
import it.cnr.istc.pst.platinum.ai.framework.microkernel.annotation.cfg.deliberative.PlannerSolverConfiguration;
import it.cnr.istc.pst.platinum.ai.framework.microkernel.annotation.cfg.deliberative.SearchStrategyConfiguration;
import it.cnr.istc.pst.platinum.ai.framework.utils.log.FrameworkLoggingLevel;


@PlannerSolverConfiguration(
		solver = PseudoControllabilityAwareSolver.class
//		timeout = 180000
)
@FlawSelectionHeuristicsConfiguration(
		heuristics = PipelineFlawSelectionHeuristic.class
//		heuristics = HierarchicalFlawSelectionHeuristic.class
)
@SearchStrategyConfiguration(
//		strategy = DepthFirstSearchStrategy.class
		strategy = GreedyDepthSearchStrategy.class
//		strategy = MakespanGreedyDepthSearchStrategy.class
//		strategy = WeightedAStarSearchStrategy.class
//		strategy = CostDepthSearchStrategy.class
//		strategy = WorkloadBalancingSearchStrategy.class
//		strategy = RiskAwarenessSearchStrategy.class
)
@FrameworkLoggerConfiguration(		
		// set logging level
		level = FrameworkLoggingLevel.INFO
)
public class PlannerTest extends Planner {

	/**
	 * 
	 */
	protected PlannerTest() {
		super();
	}
}

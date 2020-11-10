package com.github.ros.roxanne_sa.ai.deliberative;

import com.github.ros.roxanne_sa.ai.deliberative.Planner;
import com.github.ros.roxanne_sa.ai.deliberative.heuristic.pipeline.PipelineFlawSelectionHeuristic;
import com.github.ros.roxanne_sa.ai.deliberative.solver.PseudoControllabilityAwareSolver;
import com.github.ros.roxanne_sa.ai.deliberative.strategy.GreedyDepthSearchStrategy;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.FrameworkLoggerConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.FlawSelectionHeuristicsConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.PlannerSolverConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.SearchStrategyConfiguration;
import com.github.ros.roxanne_sa.ai.framework.utils.log.FrameworkLoggingLevel;


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

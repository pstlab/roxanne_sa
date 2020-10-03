package com.github.ros.roxanne_sa.ai.deliberative.solver;

import com.github.ros.roxanne_sa.ai.deliberative.heuristic.pipeline.PipelineFlawSelectionHeuristic;
import com.github.ros.roxanne_sa.ai.deliberative.strategy.CostDepthSearchStrategy;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.FlawSelectionHeuristicsConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.SearchStrategyConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.flaw.FlawType;

/**
 * 
 * @author alessandroumbrico
 *
 */
@FlawSelectionHeuristicsConfiguration(
	heuristics = PipelineFlawSelectionHeuristic.class
)
@SearchStrategyConfiguration(
		strategy = CostDepthSearchStrategy.class
)
public class BehaviorLayerSolver extends ModularSolver 
{
	/**
	 * 
	 * @param timeout
	 */
	protected BehaviorLayerSolver(long timeout) {
		super("BehaviorLayerSolver", timeout);
	}
	
	/**
	 * 
	 */
	@Override
	protected void init() {
		// set last node
		this.last = null;
		// set flaw types
		this.fTypes = new FlawType[] {
			FlawType.PLAN_REFINEMENT,	
			FlawType.TIMELINE_BEHAVIOR_PLANNING
		};
	}
	
}	

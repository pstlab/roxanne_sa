package com.github.ros.roxanne_sa.ai.deliberative.strategy;

import java.util.Map;

import com.github.ros.roxanne_sa.ai.deliberative.solver.SearchSpaceNode;
import com.github.ros.roxanne_sa.ai.framework.domain.component.DomainComponent;

/**
 * 
 * @author anacleto
 *
 */
public class GreedyDepthSearchStrategy extends SearchStrategy 
{
	/**
	 * 
	 */
	protected GreedyDepthSearchStrategy() {
		super("GreedyDepthSearchStrategy");
	}
	
	/**
	 * 
	 */
	@Override
	public void enqueue(SearchSpaceNode node) 
	{
		// compute heuristic cost
		Map<DomainComponent, Double[]> h = this.computeHeuristicCost(node);
		// set heuristic estimation
		node.setHeuristicCost(h);
		// add the node to the priority queue
		this.fringe.offer(node);
	}
	
	/**
	 * 
	 */
	@Override
	public int compare(SearchSpaceNode o1, SearchSpaceNode o2) 
	{
		// compare heuristics and makespan of nodes and use depth as last selection criterion
		return o1.getPlanHeuristicCost()[0] < o2.getPlanHeuristicCost()[0] ? -1 : o1.getPlanHeuristicCost()[0] > o2.getPlanHeuristicCost()[0] ? 1 : 
			o1.getDepth() > o2.getDepth() ? -1 : o1.getDepth() < o2.getDepth() ? 1 : 0; 
			 
	}
}

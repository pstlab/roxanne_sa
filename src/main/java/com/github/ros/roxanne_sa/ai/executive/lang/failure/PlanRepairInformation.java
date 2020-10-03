package com.github.ros.roxanne_sa.ai.executive.lang.failure;

import com.github.ros.roxanne_sa.ai.executive.pdb.ExecutionNode;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class PlanRepairInformation 
{
	private ExecutionNode node;
	private long duration;
	
	/**
	 * 
	 * @param node
	 * @param duration
	 */
	protected PlanRepairInformation(ExecutionNode node, long duration) {
		this.node = node;
		this.duration = duration;
	}
	
	public long getDuration() {
		return duration;
	}
	
	public ExecutionNode getNode() {
		return node;
	}
}

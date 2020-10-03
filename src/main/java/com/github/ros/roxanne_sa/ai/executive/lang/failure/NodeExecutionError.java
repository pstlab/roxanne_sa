package com.github.ros.roxanne_sa.ai.executive.lang.failure;

import com.github.ros.roxanne_sa.ai.executive.pdb.ExecutionNode;

/**
 * 
 * @author anacleto
 *
 */
public class NodeExecutionError extends ExecutionFailureCause 
{
	/**
	 * 
	 * @param tick
	 * @param node
	 */
	public NodeExecutionError(long tick, ExecutionNode node) {
		super(tick, ExecutionFailureCauseType.NODE_EXECUTION_ERROR, node);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[ActionExecutionError] Failure while executing the action\n";
	}
}

package com.github.ros.roxanne_sa.ai.executive.lang.ex;

import com.github.ros.roxanne_sa.ai.executive.lang.failure.ExecutionFailureCause;

/**
 * 
 * @author anacleto
 *
 */
public class NodeExecutionErrorException extends ExecutionException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param cause
	 */
	public NodeExecutionErrorException(String msg, ExecutionFailureCause cause) {
		super(msg, cause);
	}
}
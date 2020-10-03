package com.github.ros.roxanne_sa.ai.executive.lang.ex;

import com.github.ros.roxanne_sa.ai.executive.lang.failure.ExecutionFailureCause;

/**
 * 
 * @author anacleto
 *
 */
public class NodeDispatchingException extends ExecutionException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 * @param cause
	 */
	public NodeDispatchingException(String msg, ExecutionFailureCause cause) {
		super(msg, cause);
	}
}

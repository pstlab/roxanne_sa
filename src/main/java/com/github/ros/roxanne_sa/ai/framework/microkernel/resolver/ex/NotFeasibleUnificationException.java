package com.github.ros.roxanne_sa.ai.framework.microkernel.resolver.ex;

/**
 * 
 * @author anacleto
 *
 */
public class NotFeasibleUnificationException extends UnsolvableFlawException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public NotFeasibleUnificationException(String msg) {
		super(msg);
	}
}

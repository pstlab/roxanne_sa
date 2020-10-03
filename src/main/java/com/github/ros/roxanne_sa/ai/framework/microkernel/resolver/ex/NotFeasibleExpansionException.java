package com.github.ros.roxanne_sa.ai.framework.microkernel.resolver.ex;

/**
 * 
 * @author anacleto
 *
 */
public class NotFeasibleExpansionException extends UnsolvableFlawException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public NotFeasibleExpansionException(String msg) {
		super(msg);
	}
}

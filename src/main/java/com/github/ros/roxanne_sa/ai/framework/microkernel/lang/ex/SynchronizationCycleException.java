package com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex;

/**
 * 
 * @author anacleto
 *
 */
public class SynchronizationCycleException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public SynchronizationCycleException(String msg) {
		super(msg);
	}
}

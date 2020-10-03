package com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex;

/**
 * 
 * @author anacleto
 *
 */
public class ConsistencyCheckException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public ConsistencyCheckException(String msg) {
		super(msg);
	}
}

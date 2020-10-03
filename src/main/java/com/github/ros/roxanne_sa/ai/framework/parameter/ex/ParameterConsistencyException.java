package com.github.ros.roxanne_sa.ai.framework.parameter.ex;

import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.ConsistencyCheckException;

/**
 * 
 * @author anacleto
 *
 */
public class ParameterConsistencyException extends ConsistencyCheckException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public ParameterConsistencyException(String msg) {
		super(msg);
	}
}

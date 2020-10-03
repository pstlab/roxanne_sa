package com.github.ros.roxanne_sa.ai.framework.parameter.ex;

import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.ConstraintPropagationException;

/**
 * 
 * @author anacleto
 *
 */
public class ParameterConstraintPropagationException extends ConstraintPropagationException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public ParameterConstraintPropagationException(String msg) {
		super(msg);
	}
}

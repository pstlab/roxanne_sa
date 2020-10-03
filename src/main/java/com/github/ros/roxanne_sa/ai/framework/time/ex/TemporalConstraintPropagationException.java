package com.github.ros.roxanne_sa.ai.framework.time.ex;

import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.ConstraintPropagationException;

/**
 * 
 * @author anacleto
 *
 */
public class TemporalConstraintPropagationException extends ConstraintPropagationException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public TemporalConstraintPropagationException(String msg) {
		super(msg);
	}
}

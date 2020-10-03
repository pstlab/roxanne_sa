package com.github.ros.roxanne_sa.ai.framework.time.tn.ex;

/**
 * 
 * @author anacleto
 *
 */
public class UnableToHandleContingentConstraintsException extends InconsistentDistanceConstraintException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public UnableToHandleContingentConstraintsException(String msg) {
		super(msg);
	}
}

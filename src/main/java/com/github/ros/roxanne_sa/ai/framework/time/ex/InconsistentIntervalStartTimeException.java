package com.github.ros.roxanne_sa.ai.framework.time.ex;

/**
 * 
 * @author anacleto
 *
 */
public class InconsistentIntervalStartTimeException extends TemporalIntervalCreationException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public InconsistentIntervalStartTimeException(String msg) {
		super(msg);
	}
}

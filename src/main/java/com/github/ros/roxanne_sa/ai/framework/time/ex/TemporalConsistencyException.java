package com.github.ros.roxanne_sa.ai.framework.time.ex;

import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.ConsistencyCheckException;

/**
 * 
 * @author anacleto
 *
 */
public class TemporalConsistencyException extends ConsistencyCheckException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public TemporalConsistencyException(String msg) {
		super(msg);
	}
}

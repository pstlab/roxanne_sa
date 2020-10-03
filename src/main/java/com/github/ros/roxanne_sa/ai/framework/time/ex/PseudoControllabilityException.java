package com.github.ros.roxanne_sa.ai.framework.time.ex;

import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.ConsistencyCheckException;

/**
 * 
 * @author anacleto
 *
 */
public class PseudoControllabilityException extends ConsistencyCheckException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param msg
	 */
	public PseudoControllabilityException(String msg) {
		super(msg);
	}
}

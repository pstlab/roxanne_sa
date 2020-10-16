package com.github.ros.roxanne_sa.ai.framework.microkernel;

import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject.FrameworkLoggerPlaceholder;
import com.github.ros.roxanne_sa.ai.framework.utils.log.FrameworkLogger;

/**
 * 
 * @author anacleto
 *
 */
public abstract class FrameworkObject 
{
	// set framework home
	protected static final String FRAMEWORK_HOME = System.getenv("ROXANNE_HOME") != null ?
			System.getenv("ROXANNE_HOME") + "/" : "";
	
	@FrameworkLoggerPlaceholder
	private static FrameworkLogger logger;

	/**
	 * 
	 */
	protected FrameworkObject() {}
	
	/**
	 * 
	 * @param msg
	 */
	protected static void error(String msg) {
		if (logger != null) {
			logger.error(msg);
		}
	}
	
	/**
	 * 
	 * @param msg
	 */
	protected static void warning(String msg) {
		if (logger != null) {
			logger.warning(msg);
		}
	}
	
	/**
	 * 
	 * @param msg
	 */
	protected static void debug(String msg) {
		if (logger != null) {
			logger.debug(msg);
		}
	}
	
	/**
	 * 
	 * @param msg
	 */
	protected static void info(String msg) {
		if (logger != null) {
			logger.info(msg);
		}
	}
}

package com.github.ros.roxanne_sa.platform;

import com.github.ros.roxanne_sa.platform.lang.ex.PlatformException;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class RunnablePlatformProxy extends PlatformProxy 
{
	/**
	 * 
	 */
	protected RunnablePlatformProxy() {
		super();
	}
	
	/**
	 * 
	 * @throws PlatformException
	 */
	public abstract void start() 
			throws PlatformException;
	
	/**
	 * 
	 * @throws PlatformException
	 */
	public abstract void stop() 
			throws PlatformException;
}

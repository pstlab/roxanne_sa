package com.github.ros.roxanne_sa.platform;

import com.github.ros.roxanne_sa.control.lang.PlatformFeedback;
import com.github.ros.roxanne_sa.control.lang.PlatformObservation;

/**
 * 
 * @author anacleto
 *
 */
public interface PlatformObserver 
{
	/**
	 * 
	 * @param feedback
	 */
	public void feedback(PlatformFeedback feedback);
	
	/**
	 * Asynchronous notification of some observed events from 
	 * the platform
	 * 
	 * @param obs
	 */
	public void observation(PlatformObservation<? extends Object> obs);
}

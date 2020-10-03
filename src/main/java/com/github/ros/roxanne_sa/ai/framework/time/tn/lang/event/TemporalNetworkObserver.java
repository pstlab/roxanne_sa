package com.github.ros.roxanne_sa.ai.framework.time.tn.lang.event;

import com.github.ros.roxanne_sa.ai.framework.time.tn.lang.event.ex.NotificationPropagationFailureException;


/**
 * 
 * @author alessandroumbrico
 *
 */
public interface TemporalNetworkObserver 
{
	/**
	 * Notify the observer about some changes in the 
	 * Temporal Network structure. 
	 * 
	 * Returns true if and only if the notification is successfully propagated
	 * 
	 * @param info
	 * @return
	 * @throws NotificationPropagationFailureException
	 */
	public void notify(TemporalNetworkNotification info) 
			throws NotificationPropagationFailureException;
}

package com.github.ros.roxanne_sa.platform.rosbridge;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.ros.roxanne_sa.control.lang.PlatformObservation;
import com.github.ros.roxanne_sa.platform.lang.ex.PlatformCommunicationException;
import com.github.ros.roxanne_sa.platform.lang.ex.PlatformException;

import ros.RosListenDelegate;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class ROSObservationListener<T extends Object> implements RosListenDelegate 
{
	protected ROSBridgePlatformProxy proxy;
	
	/**
	 * 
	 * @param proxy
	 */
	protected ROSObservationListener(ROSBridgePlatformProxy proxy) {
		this.proxy = proxy;
	}
	
	/**
	 * 
	 */
	@Override
	public void receive(JsonNode data, String stringRep) 
	{
		try
		{
			// get observation
			PlatformObservation<T> observation = this.doHandleMessage(data, stringRep);
			// notify observation
			this.proxy.notify(observation);
		}
		catch (PlatformException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	/**
	 * 
	 * @param data
	 * @param stringRep
	 * @return
	 * @throws PlatformCommunicationException
	 */
	protected abstract PlatformObservation<T> doHandleMessage(JsonNode data, String stringRep) 
			throws PlatformCommunicationException;
}

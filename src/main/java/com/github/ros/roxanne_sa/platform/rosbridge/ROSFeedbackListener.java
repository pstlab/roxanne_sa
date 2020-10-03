package com.github.ros.roxanne_sa.platform.rosbridge;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.ros.roxanne_sa.control.lang.PlatformFeedback;
import com.github.ros.roxanne_sa.platform.lang.ex.PlatformCommunicationException;
import com.github.ros.roxanne_sa.platform.lang.ex.PlatformException;

import ros.RosListenDelegate;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class ROSFeedbackListener implements RosListenDelegate 
{
	protected ROSBridgePlatformProxy proxy;
	
	/**
	 * 
	 * @param proxy
	 */
	protected ROSFeedbackListener(ROSBridgePlatformProxy proxy) {
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
			// handle message and create action feedback
			PlatformFeedback feedback = this.doHandleMessage(data, stringRep);
			// print message
			System.out.println("<<<< Receiving msg :\n\t- cmd: " + feedback.getCmd() + "\n\t- data: " + data + "\n");
			// send feedback notification
			this.proxy.notify(feedback);
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
	protected abstract PlatformFeedback doHandleMessage(JsonNode data, String stringRep) 
			throws PlatformCommunicationException;
}

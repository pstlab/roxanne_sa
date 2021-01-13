package com.github.ros.roxanne_sa.platform.rosbridge;

import com.fasterxml.jackson.databind.JsonNode;

import it.cnr.istc.pst.platinum.control.lang.PlatformMessage;
import it.cnr.istc.pst.platinum.control.lang.ex.PlatformCommunicationException;
import it.cnr.istc.pst.platinum.control.lang.ex.PlatformException;
import ros.RosListenDelegate;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class RosBridgeTopicListener <T extends PlatformMessage> extends RosBridgeTopicHandler implements RosListenDelegate 
{
	protected RosBridgePlatformProxy proxy;
	
	/**
	 * 
	 * @param proxy
	 */
	protected RosBridgeTopicListener(RosBridgePlatformProxy proxy) {
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
			T msg = this.doHandleMessage(data, stringRep);
			// print message
			System.out.println("<<<< Receiving msg :\n\t- data: " + data + "\n");
			// send feedback notification
			this.proxy.notify(msg);
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
	protected abstract T doHandleMessage(JsonNode data, String stringRep) 
			throws PlatformCommunicationException;
}

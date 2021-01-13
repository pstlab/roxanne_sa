package com.github.ros.roxanne_sa.platform.rosbridge;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.ros.roxanne_sa.platform.msgs.Observation;

import it.cnr.istc.pst.platinum.control.lang.PlatformObservation;
import it.cnr.istc.pst.platinum.control.lang.ex.PlatformCommunicationException;
import ros.tools.MessageUnpacker;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RoxanneObservationListener extends RosBridgeTopicListener<PlatformObservation<Observation>> 
{
	/**
	 * 
	 * @param proxy
	 */
	protected RoxanneObservationListener(RosBridgePlatformProxy proxy) {
		super(proxy);
	}

	/**
	 * 
	 */
	@Override
	protected PlatformObservation<Observation> doHandleMessage(JsonNode data, String stringRep) 
			throws PlatformCommunicationException 
	{
		// get message unpacker
		MessageUnpacker<Observation> unpacker = 
				new MessageUnpacker<Observation>(Observation.class);
		// unpack content observation data
		Observation obsData = unpacker.unpackRosMessage(data);
		
		// create platform observation
		PlatformObservation<Observation> observation = new PlatformObservation<Observation>(
				msgIdCounter.getAndIncrement(), obsData);
		// get observation
		return observation;
	}
}

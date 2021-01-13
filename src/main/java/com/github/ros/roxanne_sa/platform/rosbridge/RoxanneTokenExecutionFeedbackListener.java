package com.github.ros.roxanne_sa.platform.rosbridge;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.ros.roxanne_sa.platform.msgs.TokenExecutionFeedback;

import it.cnr.istc.pst.platinum.control.lang.PlatformCommand;
import it.cnr.istc.pst.platinum.control.lang.PlatformFeedback;
import it.cnr.istc.pst.platinum.control.lang.ex.PlatformCommunicationException;
import ros.tools.MessageUnpacker;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RoxanneTokenExecutionFeedbackListener extends RosBridgeTopicListener<PlatformFeedback> 
{
	/**
	 * 
	 * @param proxy
	 */
	protected RoxanneTokenExecutionFeedbackListener(RosBridgePlatformProxy proxy) {
		super(proxy);
	}

	/**
	 * 
	 */
	@Override
	protected PlatformFeedback doHandleMessage(JsonNode data, String stringRep) 
			throws PlatformCommunicationException 
	{
	
		// get message unpacker
		MessageUnpacker<TokenExecutionFeedback> unpacker = 
				new MessageUnpacker<TokenExecutionFeedback>(TokenExecutionFeedback.class);
		// get message content
		TokenExecutionFeedback content = unpacker.unpackRosMessage(data);
		
		// retrieve issuing command
		PlatformCommand cmd = this.proxy.getDispatchedCommand(content.getTokenId());
		// check command
		if (cmd == null) {
			throw new PlatformCommunicationException("Received feedback about a non-dispatched command:\n\t- cmdId: " + content.getTokenId());
		}
		
		// create platform feedback
		PlatformFeedback feedback = new PlatformFeedback(
				msgIdCounter.getAndIncrement(), cmd, content.getFeedbackType());
		// get feedback message
		return feedback;
	}
}

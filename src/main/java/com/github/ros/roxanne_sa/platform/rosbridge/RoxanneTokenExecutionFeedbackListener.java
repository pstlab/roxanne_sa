package com.github.ros.roxanne_sa.platform.rosbridge;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.ros.roxanne_sa.control.lang.PlatformCommand;
import com.github.ros.roxanne_sa.control.lang.PlatformFeedback;
import com.github.ros.roxanne_sa.platform.lang.ex.PlatformCommunicationException;
import com.github.ros.roxanne_sa.platform.msgs.TokenExecutionFeedback;

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
		PlatformCommand cmd = this.proxy.getDispatchedCommand(content.getCmdId());
		// check command
		if (cmd == null) {
			throw new PlatformCommunicationException("Received feedback about a non-dispatched command:\n\t- cmdId: " + content.getCmdId());
		}
		
		// create platform feedback
		PlatformFeedback feedback = new PlatformFeedback(
				msgIdCounter.getAndIncrement(), cmd, content.getFeedbackType());
		// get feedback message
		return feedback;
	}
}

package com.github.ros.roxanne_sa.platform.rosbridge.stiima;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.ros.roxanne_sa.control.lang.PlatformCommand;
import com.github.ros.roxanne_sa.control.lang.PlatformFeedback;
import com.github.ros.roxanne_sa.platform.lang.ex.PlatformCommunicationException;
import com.github.ros.roxanne_sa.platform.rosbridge.RosBridgePlatformProxy;
import com.github.ros.roxanne_sa.platform.rosbridge.RosBridgeTopicListener;

import ros.tools.MessageUnpacker;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class MotionTaskFeedbackListener extends RosBridgeTopicListener<PlatformFeedback> 
{
	/**
	 * 
	 * @param proxy
	 */
	protected MotionTaskFeedbackListener(RosBridgePlatformProxy proxy) {
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
		MessageUnpacker<MotionTaskExecutionFeedback> unpacker = 
				new MessageUnpacker<MotionTaskExecutionFeedback>(MotionTaskExecutionFeedback.class);
		// unpack message context according to the expected format
		MotionTaskExecutionFeedback content = unpacker.unpackRosMessage(data);
		
		// retrieve issuing command
		PlatformCommand cmd = this.proxy.getDispatchedCommand(content.cmd_id);
		// check command
		if (cmd == null) {
			throw new PlatformCommunicationException("Received feedback concerning a non-dispatched command:\n\t- cmdId: " + content.cmd_id);
		}
		
		// create robot action feedback
		PlatformFeedback feedback = new PlatformFeedback(
				msgIdCounter.getAndIncrement(), cmd, content.getResult());
		// get action feedback
		return feedback;
	}
}

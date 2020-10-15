package com.github.ros.roxanne_sa.platform.rosbridge.lms;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.ros.roxanne_sa.control.lang.PlatformFeedback;
import com.github.ros.roxanne_sa.platform.lang.ex.PlatformCommunicationException;
import com.github.ros.roxanne_sa.platform.msgs.TokenExecutionFeedback;
import com.github.ros.roxanne_sa.platform.rosbridge.RosBridgePlatformProxy;
import com.github.ros.roxanne_sa.platform.rosbridge.RosBridgeTopicListener;

import ros.tools.MessageUnpacker;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class TaskFeedbackListener extends RosBridgeTopicListener<PlatformFeedback> 
{
	/**
	 * 
	 * @param proxy
	 */
	protected TaskFeedbackListener(RosBridgePlatformProxy proxy) {
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
//		PlatformCommand cmd = this.proxy.getDispatchedCommand(content.getCmdId());
		// check command
//		if (cmd == null) {
//			throw new PlatformCommunicationException("Received feedback concerning a non-dispatched command:\n\t- cmdId: " + content.cmd_id);
//		}
		
		// create action feedback
//		PlatformFeedback feedback = new PlatformFeedback(cmd, content.getResult());
		// get action feedback
//		return feedback;
		
		// TODO : to check with updated messages
		return null;
	}
}

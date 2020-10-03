package com.github.ros.roxanne_sa.platform.rosbridge.stiima;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.ros.roxanne_sa.control.lang.PlatformCommand;
import com.github.ros.roxanne_sa.control.lang.PlatformFeedback;
import com.github.ros.roxanne_sa.platform.lang.ex.PlatformCommunicationException;
import com.github.ros.roxanne_sa.platform.rosbridge.ROSBridgePlatformProxy;
import com.github.ros.roxanne_sa.platform.rosbridge.ROSFeedbackListener;
import com.github.ros.roxanne_sa.platform.rosbridge.stiima.msgs.MotionTaskExecutionFeedback;

import ros.tools.MessageUnpacker;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class MotionTaskFeedbackListener extends ROSFeedbackListener 
{
	/**
	 * 
	 * @param proxy
	 */
	protected MotionTaskFeedbackListener(ROSBridgePlatformProxy proxy) {
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
		MessageUnpacker<MotionTaskExecutionFeedback> unpacker = new MessageUnpacker<MotionTaskExecutionFeedback>(MotionTaskExecutionFeedback.class);
		MotionTaskExecutionFeedback content = unpacker.unpackRosMessage(data);
		
		// retrieve issuing command
		PlatformCommand cmd = this.proxy.getDispatchedCommand(content.cmd_id);
		// check command
		if (cmd == null) {
			throw new PlatformCommunicationException("Received feedback concerning a non-dispatched command:\n\t- cmdId: " + content.cmd_id);
		}
		
		// create action feedback
		PlatformFeedback feedback = new PlatformFeedback(cmd, content.getResult());
		// get action feedback
		return feedback;
	}
}

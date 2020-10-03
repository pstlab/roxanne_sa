package com.github.ros.roxanne_sa.platform.rosbridge.lms;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.ros.roxanne_sa.control.lang.PlatformCommand;
import com.github.ros.roxanne_sa.control.lang.PlatformFeedback;
import com.github.ros.roxanne_sa.platform.lang.ex.PlatformCommunicationException;
import com.github.ros.roxanne_sa.platform.rosbridge.ROSBridgePlatformProxy;
import com.github.ros.roxanne_sa.platform.rosbridge.ROSFeedbackListener;
import com.github.ros.roxanne_sa.platform.rosbridge.roxanne.msgs.TaskExecutionFeedback;

import ros.tools.MessageUnpacker;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class TaskFeedbackListener extends ROSFeedbackListener 
{
	/**
	 * 
	 * @param proxy
	 */
	protected TaskFeedbackListener(ROSBridgePlatformProxy proxy) {
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
		MessageUnpacker<TaskExecutionFeedback> unpacker = new MessageUnpacker<TaskExecutionFeedback>(TaskExecutionFeedback.class);
		TaskExecutionFeedback content = unpacker.unpackRosMessage(data);
		
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

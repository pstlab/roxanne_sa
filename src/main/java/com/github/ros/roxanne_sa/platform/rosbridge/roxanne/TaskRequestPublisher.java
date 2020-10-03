package com.github.ros.roxanne_sa.platform.rosbridge.roxanne;

import com.github.ros.roxanne_sa.control.lang.PlatformCommand;
import com.github.ros.roxanne_sa.platform.rosbridge.ROSBridgePlatformProxy;
import com.github.ros.roxanne_sa.platform.rosbridge.ROSPublisher;
import com.github.ros.roxanne_sa.platform.rosbridge.roxanne.msgs.TaskExecutionRequest;

import ros.Publisher;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class TaskRequestPublisher extends ROSPublisher<TaskExecutionRequest> 
{
	/**
	 * 
	 * @param proxy
	 * @param publisher
	 */
	protected TaskRequestPublisher(ROSBridgePlatformProxy proxy, Publisher publisher) {
		super(proxy, publisher);
	}
	
	/**
	 * 
	 */
	@Override
	protected TaskExecutionRequest doCreateMessage(PlatformCommand cmd) 
	{
		// create task request
		TaskExecutionRequest request = new TaskExecutionRequest(
				cmd.getId(),
				cmd.getName(),
				cmd.getParamValues());
		
		// get request
		return request;
	}
}

package com.github.ros.roxanne_sa.platform.rosbridge.stiima;

import com.github.ros.roxanne_sa.ai.executive.pdb.ExecutionNode;
import com.github.ros.roxanne_sa.control.lang.PlatformCommand;
import com.github.ros.roxanne_sa.platform.PlatformProxy;
import com.github.ros.roxanne_sa.platform.rosbridge.RosBridgePlatformProxy;
import com.github.ros.roxanne_sa.platform.rosbridge.RosBridgeTopicPublisher;
import com.github.ros.roxanne_sa.platform.rosbridge.stiima.msgs.MotionTaskExecutionRequest;
import com.github.ros.roxanne_sa.platform.rosbridge.stiima.msgs.MotionTaskExecutionRequestArray;

import ros.Publisher;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class MotionTaskRequestPublisher extends RosBridgeTopicPublisher<MotionTaskExecutionRequestArray> 
{
	/**
	 * 
	 * @param proxy
	 * @param publisher
	 */
	protected MotionTaskRequestPublisher(RosBridgePlatformProxy proxy, Publisher publisher) {
		super(proxy, publisher);
	}
	
	/**
	 * 
	 */
	@Override
	protected MotionTaskExecutionRequestArray doCreateMessage(PlatformCommand cmd) 
	{
		// create motion task request array
		MotionTaskExecutionRequestArray request = new MotionTaskExecutionRequestArray(cmd.getId());
		
		// create motion task request array
		MotionTaskExecutionRequest[] tasks = new MotionTaskExecutionRequest[3];
		
		
		// check parameters
		if (cmd.getParamValues().length == 3)
		{
			// set the current requested task
			tasks[0] = new MotionTaskExecutionRequest(
					(cmd.getName() + cmd.getParamValues()[1]).toLowerCase(),
					cmd.getName(),
					cmd.getParamValues()[0],
					cmd.getParamValues()[1],
					Float.parseFloat(cmd.getParamValues()[2]),
					(cmd.getNode().getDuration()[1] - cmd.getNode().getDuration()[0]) / 2, 
					new String[] {
							
							// TODO : vector of expected simultaneous human tasks
					});
		}
		else 
		{
			// set the current requested task
			tasks[0] = new MotionTaskExecutionRequest(
					(cmd.getName() + cmd.getParamValues()[1]).toLowerCase(),
					cmd.getName(),
					"box_blue",
					cmd.getName().substring(cmd.getName().length() - 2, cmd.getName().length()),
					Float.parseFloat("1"),
					(cmd.getNode().getDuration()[1] - cmd.getNode().getDuration()[0]) / 2, 
					new String[] {
							
							// TODO : vector of expected simultaneous human tasks
					});
		}
		
		// next node on the component 
		ExecutionNode next = cmd.getNode().getNext();
		while (next != null && !this.proxy.isPlatformCommand(next)) {
			// get next command	
			next = next.getNext();
		}
		
		
		// check if a command has been found
		if (next != null && this.proxy.isPlatformCommand(next))
		{
			// extract command name
			String name = PlatformProxy.extractCommandName(next);
			// get next command parameters
			String[] params = PlatformProxy.extractCommandParameters(next);
			
			// check parameters
			if (params.length == 3)
			{
				// set next requested task
				tasks[1] = new MotionTaskExecutionRequest(
						(name + params[1]).toLowerCase(),
						name,
						params[0],
						params[1],
						Float.parseFloat(params[2]),
						(next.getDuration()[1] - next.getDuration()[0]) / 2,
						new String[] {
							// TODO : vector of expected simultaneous human tasks
						});
			}
			else {
				// set next requested task
				tasks[1] = new MotionTaskExecutionRequest(
						(name + cmd.getName().substring(cmd.getName().length() - 2, cmd.getName().length())).toLowerCase(),
						name,
						"box_blue",
						cmd.getName().substring(cmd.getName().length() - 2, cmd.getName().length()),
						Float.parseFloat("1"),
						(next.getDuration()[1] - next.getDuration()[0]) / 2,
						new String[] {
							// TODO : vector of expected simultaneous human tasks
						});
			}
	
			// next node on the component
			next = next.getNext();
			while (next != null && !this.proxy.isPlatformCommand(next)) {
				// get next node
				next = next.getNext();
			}
			// check if a command has been found
			if (next != null && this.proxy.isPlatformCommand(next))
			{
				// extract command name
				name = PlatformProxy.extractCommandName(next);
				// get next command parameters
				params = PlatformProxy.extractCommandParameters(next);
				
				// check parameters
				if (params.length == 3)
				{
					// set next requested task
					tasks[2] = new MotionTaskExecutionRequest(
							(name + params[1]).toLowerCase(), 
							name,
							params[0],
							params[1],
							Float.parseFloat(params[2]),
							(next.getDuration()[1] - next.getDuration()[0]) / 2,
							new String[] {
								// TODO : vector of expected simultaneous human tasks
							});
				}
				else
				{
					// set next requested task
					tasks[2] = new MotionTaskExecutionRequest(
							(name + cmd.getName().substring(cmd.getName().length() - 2, cmd.getName().length())).toLowerCase(),
							name,
							"box_blue",
							cmd.getName().substring(cmd.getName().length() - 2, cmd.getName().length()),
							Float.parseFloat("1"),
							(next.getDuration()[1] - next.getDuration()[0]) / 2,
							new String[] {
								// TODO : vector of expected simultaneous human tasks
							});
				}
			}
			else {
				// set empty object
				tasks[2] = new MotionTaskExecutionRequest("empty");
			}
		}		
		else {
			// set empty objects
			tasks[1] = new MotionTaskExecutionRequest("empty");
			tasks[2] = new MotionTaskExecutionRequest("empty");
		}
		
		// set request tasks
		request.tasks = tasks;
		// get the request
		return request;
	}
}

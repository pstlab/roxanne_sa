package com.github.ros.roxanne_sa.platform.rosbridge;

import com.github.ros.roxanne_sa.control.lang.PlatformCommand;
import com.github.ros.roxanne_sa.platform.msgs.Token;
import com.github.ros.roxanne_sa.platform.msgs.TokenExecution;

import ros.Publisher;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RoxanneTokenExecutionPublisher extends RosBridgeTopicPublisher<TokenExecution> 
{
	/**
	 * 
	 * @param proxy
	 * @param publisher
	 */
	protected RoxanneTokenExecutionPublisher(RosBridgePlatformProxy proxy, Publisher publisher) {
		super(proxy, publisher);
	}
	
	/**
	 * 
	 */
	@Override
	protected TokenExecution doCreateMessage(PlatformCommand cmd) 
	{
		// create token object
		Token token = new Token();
		// set values from execution node
		token.setComponent(cmd.getNode().getComponent());
		token.setPredicate(cmd.getNode().getPredicate().getSignature());
		token.setParameters(cmd.getParamValues());
		token.setStart(cmd.getNode().getStart());
		token.setEnd(cmd.getNode().getEnd());
		token.setDuration(cmd.getNode().getDuration());
		
		// create task request
		TokenExecution request = new TokenExecution(
				msgIdCounter.getAndIncrement(),
				cmd.getCommandType(),
				token);
		
		// get request
		return request;
	}
}

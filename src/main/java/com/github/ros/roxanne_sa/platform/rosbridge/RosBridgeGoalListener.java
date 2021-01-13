package com.github.ros.roxanne_sa.platform.rosbridge;

import com.fasterxml.jackson.databind.JsonNode;

import it.cnr.istc.pst.platinum.control.acting.GoalOrientedActingAgent;
import it.cnr.istc.pst.platinum.control.lang.AgentTaskDescription;
import it.cnr.istc.pst.platinum.control.lang.ex.PlatformCommunicationException;
import ros.RosListenDelegate;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class RosBridgeGoalListener extends RosBridgeTopicHandler implements RosListenDelegate 
{
	private GoalOrientedActingAgent agent;
	
	/**
	 * 
	 * @param agent
	 */
	protected RosBridgeGoalListener(GoalOrientedActingAgent agent) {
		// set agent
		this.agent = agent;
	}
	
	/**
	 * 
	 */
	@Override
	public void receive(JsonNode data, String stringRep) 
	{
		
		try
		{
			// extract task description from goal
			AgentTaskDescription task = this.doHandleMessage(data, stringRep);
			// buffer task 
			this.agent.buffer(task);
		}
		catch (Exception ex) {
			// error while extracting goal
			System.err.println("Error while extracting taks description from goal request:\n"
					+ "\t- message: " + ex.getMessage() + "\n");
		}
	}

	
	/**
	 * 
	 * @param data
	 * @param stringRep
	 * @return
	 * @throws PlatformCommunicationException
	 */
	protected abstract AgentTaskDescription doHandleMessage(JsonNode data, String stringRep) 
			throws PlatformCommunicationException;
}

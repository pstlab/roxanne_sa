package com.github.ros.roxanne_sa.platform.rosbridge;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.ros.roxanne_sa.control.acting.GoalOrientedActingAgent;
import com.github.ros.roxanne_sa.control.lang.AgentTaskDescription;
import com.github.ros.roxanne_sa.control.lang.TokenDescription;
import com.github.ros.roxanne_sa.platform.lang.ex.PlatformCommunicationException;
import com.github.ros.roxanne_sa.platform.msgs.ActingGoal;
import com.github.ros.roxanne_sa.platform.msgs.Token;

import ros.tools.MessageUnpacker;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RoxanneActingGoalListener extends RosBridgeGoalListener 
{
	/**
	 * 
	 * @param agent
	 */
	protected RoxanneActingGoalListener(GoalOrientedActingAgent agent) {
		super(agent);
	}
	
	/**
	 * 
	 */
	@Override
	protected AgentTaskDescription doHandleMessage(JsonNode data, String stringRep)
			throws PlatformCommunicationException 
	{
		// set message unpacker
		MessageUnpacker<ActingGoal> unpacker = 
				new MessageUnpacker<ActingGoal>(ActingGoal.class);
		// unpack content observation data
		ActingGoal goal = unpacker.unpackRosMessage(data);
		
		// create task description
		AgentTaskDescription task = new AgentTaskDescription();
		// set facts
		for (Token t : goal.getFacts()) 
		{
			// create token description 
			TokenDescription fact = new TokenDescription(
					t.getComponent(), 
					t.getPredicate(),
					new String[] {}, 	// TODO : add parameter specification and related binding constrains
					t.getStart(), 
					t.getEnd(), 
					t.getDuration());
			
			// add fact 
			task.addFactDescription(fact);
		}
		
		// set goals
		for (Token t : goal.getGoals()) 
		{
			// create tokne description
			TokenDescription g = new TokenDescription(
					t.getComponent(), 
					t.getPredicate(),
					new String[] {},	// TODO : add parameter specification and related biding constraints
					t.getStart(),
					t.getEnd(),
					t.getDuration());
			
			// add goal 
			task.addGoalDescription(g);
		}
		
		// get created task
		return task;
	}

}

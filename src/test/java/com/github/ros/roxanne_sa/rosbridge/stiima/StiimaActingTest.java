package com.github.ros.roxanne_sa.rosbridge.stiima;

import java.util.List;

import com.github.ros.roxanne_sa.control.acting.GoalOrientedActingAgent;
import com.github.ros.roxanne_sa.control.lang.AgentTaskDescription;
import com.github.ros.roxanne_sa.control.lang.Goal;
import com.github.ros.roxanne_sa.control.lang.TokenDescription;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class StiimaActingTest 
{
	/**
	 * 
	 * @return
	 */
	private static AgentTaskDescription createTaskDescription() 
	{
		// create task description
		AgentTaskDescription description = new AgentTaskDescription();
		// add goal
		description.addGoalDescription(new TokenDescription(
				"Goal", 
				"DoMosaic"));
		
		// get task description
		return description;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			// create goal-oriented agent
			GoalOrientedActingAgent agent = new GoalOrientedActingAgent("etc/agent_stiima.properties");
			// start agent
			System.out.println("Starting agent...");
			agent.start();
			// setup the agent
			System.out.println("Initializing agent... ");
			agent.initialize();
			
			// create task description 
			AgentTaskDescription task = createTaskDescription();
			// buffer task description
			System.out.println("Buffering a new task ... ");
			agent.buffer(task);
						
			// get managed goals
			List<Goal> goals = agent.getResults();
			for (Goal goal : goals) 
			{
				System.out.println("Completed goal " + goal +":\n"
						+ "\t- Planning: " + goal.getPlanningAttempts() + " sessions, total time: " + (goal.getTotalPlanningTime() / 1000) +" seconds\n"
						+ "\t- Execution: " + goal.getExecutionAttempts() + " sessions, total time: " + (goal.getTotalExecutionTime() / 1000) + " seconds\n"
						+ "\t- Contingency handling: " + goal.getContingencyHandlingAttempts() + " sessions, total time: " + (goal.getTotalContingencyHandlingTime() / 1000 ) + " seconds\n\n");
				
				
			}
					
			System.out.println("Terminating agent...");
			// stop agent
			agent.stop();
			System.out.println(".... finish!");
			System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		finally {
			
			// close ROS bridge
		}
	}
}

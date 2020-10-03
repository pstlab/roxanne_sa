package com.github.ros.roxanne_sa.rosbridge;

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
public class RosBridgeHRCActingTest 
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
				"Process", 
				"HRC"));
		
		// get task description
		return description;
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (args.length == 0) {
			System.out.println("Running acting agent with default configuration file \"etc/agent.properties\"");
		}
		else {
			System.out.println("Running acting agent with configuration file \"" + args[0] + "\"");
		}
		
		try
		{
			// create goal-oriented agent
			GoalOrientedActingAgent agent = null; 
			if (args.length == 0) {
				// set agent 
				agent = new GoalOrientedActingAgent();
			}
			else {
				// set agent
				agent = new GoalOrientedActingAgent(args[0]);
			}
			
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

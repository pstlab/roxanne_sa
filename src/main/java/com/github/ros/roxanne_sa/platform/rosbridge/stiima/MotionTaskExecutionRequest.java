package com.github.ros.roxanne_sa.platform.rosbridge.stiima;

import java.util.Arrays;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class MotionTaskExecutionRequest 
{
	public String cmd_id;				// id of the command
	public String cmd_name;				// name of the command
	public String cfg_start;			// starting motion configuration
	public String cfg_goal;				// goal motion configuration
	public float risk_level;			// risk level of the "involved motion area"
	public float expected_time;			// expected duration of the task	
	public String[] human_tasks;		// expected simultaneous human tasks
	
	/**
	 * 
	 * @param cmd_id
	 */
	public MotionTaskExecutionRequest(String cmd_id) {
		this.cmd_id = cmd_id;
		this.cmd_name = "";
		this.cfg_start = "";
		this.cfg_goal = "";
		this.risk_level = 0;
		this.expected_time = 0;
		this.human_tasks = new String[] {};
	}
	
	/**
	 * 
	 * @param cmd_id
	 * @param task_id
	 * @param name
	 * @param cfgStart
	 * @param cfgGoal
	 * @param risk
	 * @param duration
	 * @param humanTasks
	 */
	public MotionTaskExecutionRequest(String cmd_id, String name, String cfgStart, String cfgGoal, float risk, float duration, String[] humanTasks) {
		this.cmd_id = cmd_id;
		this.cmd_name = name;
		this.cfg_start = cfgStart;
		this.cfg_goal = cfgGoal;
		this.risk_level = risk;
		this.human_tasks = humanTasks;
		this.expected_time = duration;
	}

	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cfg_goal == null) ? 0 : cfg_goal.hashCode());
		result = prime * result + ((cfg_start == null) ? 0 : cfg_start.hashCode());
		result = prime * result + ((cmd_name == null) ? 0 : cmd_name.hashCode());
		result = prime * result + Arrays.hashCode(human_tasks);
		result = prime * result + Float.floatToIntBits(risk_level);
		return result;
	}

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MotionTaskExecutionRequest other = (MotionTaskExecutionRequest) obj;
		if (cfg_goal == null) {
			if (other.cfg_goal != null)
				return false;
		} else if (!cfg_goal.equals(other.cfg_goal))
			return false;
		if (cfg_start == null) {
			if (other.cfg_start != null)
				return false;
		} else if (!cfg_start.equals(other.cfg_start))
			return false;
		if (cmd_name == null) {
			if (other.cmd_name != null)
				return false;
		} else if (!cmd_name.equals(other.cmd_name))
			return false;
		if (!Arrays.equals(human_tasks, other.human_tasks))
			return false;
		if (Float.floatToIntBits(risk_level) != Float.floatToIntBits(other.risk_level))
			return false;
		return true;
	}

	@Override
	public String toString() {
		// JSON style description
		return "{\"id\": \"" + cmd_id +"\", \"name\": \"" + this.cmd_name + "\", \"start\": \"" + this.cfg_start + "\", \"goal\": \"" + this.cfg_goal + "\", \"risk\": " + this.risk_level + "}";
	}
}

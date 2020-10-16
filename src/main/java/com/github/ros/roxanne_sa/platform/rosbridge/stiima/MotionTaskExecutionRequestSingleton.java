package com.github.ros.roxanne_sa.platform.rosbridge.stiima;

import java.util.Arrays;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class MotionTaskExecutionRequestSingleton 
{
	public String cmd_id;				// ID of the command
	public String cmd_name;				// name of the command
	public String cfg_start;			// starting motion configuration
	public String cfg_goal;				// goal motion configuration
	public float risk_level;			// risk level of the "involved motion area"
	public String[] human_tasks;		// expected simultaneous human tasks
	
	/**
	 * 
	 */
	public MotionTaskExecutionRequestSingleton() {}
	
	/**
	 * 
	 * @param cmdId
	 * @param name
	 * @param cfgStart
	 * @param cfgGoal
	 * @param risk
	 * @param humanTasks
	 */
	public MotionTaskExecutionRequestSingleton(String cmdId, String name, String cfgStart, String cfgGoal, float risk, String[] humanTasks) {
		this.cmd_id = cmdId;
		this.cmd_name = name;
		this.cfg_start = cfgStart;
		this.cfg_goal = cfgGoal;
		this.risk_level = risk;
		this.human_tasks = humanTasks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cfg_goal == null) ? 0 : cfg_goal.hashCode());
		result = prime * result + ((cfg_start == null) ? 0 : cfg_start.hashCode());
		result = prime * result + ((cmd_id == null) ? 0 : cmd_id.hashCode());
		result = prime * result + Arrays.hashCode(human_tasks);
		result = prime * result + Float.floatToIntBits(risk_level);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MotionTaskExecutionRequestSingleton other = (MotionTaskExecutionRequestSingleton) obj;
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
		if (cmd_id == null) {
			if (other.cmd_id != null)
				return false;
		} else if (!cmd_id.equals(other.cmd_id))
			return false;
		if (!Arrays.equals(human_tasks, other.human_tasks))
			return false;
		if (Float.floatToIntBits(risk_level) != Float.floatToIntBits(other.risk_level))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "[MotionTaskExecutionRequest name : " + this.cmd_name + ", start : " + this.cfg_start + ", goal : " + this.cfg_goal + ", risk : " + this.risk_level + "]";
	}
}

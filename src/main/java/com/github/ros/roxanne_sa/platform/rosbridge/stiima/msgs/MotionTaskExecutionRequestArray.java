package com.github.ros.roxanne_sa.platform.rosbridge.stiima.msgs;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class MotionTaskExecutionRequestArray 
{
	public String cmd_id;							// ID of the command
	public MotionTaskExecutionRequest[] tasks;		// tasks to execute
	
	/**
	 * 
	 */
	public MotionTaskExecutionRequestArray() {}
	
	/**
	 * 
	 * @param cmdId
	 */
	public MotionTaskExecutionRequestArray(String cmdId) {
		this.cmd_id = cmdId;
	}

	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cmd_id == null) ? 0 : cmd_id.hashCode());
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
		MotionTaskExecutionRequestArray other = (MotionTaskExecutionRequestArray) obj;
		if (cmd_id == null) {
			if (other.cmd_id != null)
				return false;
		} else if (!cmd_id.equals(other.cmd_id))
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[MotionTaskExecutionRequestArray cmdId : " + this.cmd_id + ", tasks : {current : " + this.tasks[0] + ", next-1 : " + this.tasks[1] + ", next-2 : " + this.tasks[2] + "}]";
	}
}

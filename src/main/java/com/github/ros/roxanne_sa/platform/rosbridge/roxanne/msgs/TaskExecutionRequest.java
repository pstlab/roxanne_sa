package com.github.ros.roxanne_sa.platform.rosbridge.roxanne.msgs;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class TaskExecutionRequest 
{
	public String cmd_id;				// command id
	public String cmd_name;				// name of the command
	public String[] cmd_params;			// command parameters
	
	/**
	 * 
	 */
	public TaskExecutionRequest() {
		this.cmd_name = "";
		this.cmd_name = "";
		this.cmd_params = new String[] {};
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param params
	 */
	public TaskExecutionRequest(String id, String name, String[] params) {
		this.cmd_id = id;
		this.cmd_name = name;
		this.cmd_params = params;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cmd_id == null) ? 0 : cmd_id.hashCode());
		result = prime * result + ((cmd_name == null) ? 0 : cmd_name.hashCode());
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
		TaskExecutionRequest other = (TaskExecutionRequest) obj;
		if (cmd_id == null) {
			if (other.cmd_id != null)
				return false;
		} else if (!cmd_id.equals(other.cmd_id))
			return false;
		if (cmd_name == null) {
			if (other.cmd_name != null)
				return false;
		} else if (!cmd_name.equals(other.cmd_name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[TaskExecutionRequest id : " + this.cmd_id + ", name : " + this.cmd_name + "]";
	}
}

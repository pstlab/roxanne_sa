package com.github.ros.roxanne_sa.platform.rosbridge.stiima.msgs;

import com.github.ros.roxanne_sa.control.lang.PlatformFeedbackType;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class MotionTaskExecutionFeedback 
{
	private static final PlatformFeedbackType[] RESULTS = new PlatformFeedbackType[] {
			
			PlatformFeedbackType.FAILURE,
			
			PlatformFeedbackType.SUCCESS
	};
	
	public long cmd_id;						// ID of the command a feedback refers to
	public int result;						// an integer representing the result of an operation
	
	
	public MotionTaskExecutionFeedback() {}
	
	/**
	 * 
	 * @param cmdId
	 * @param result
	 */
	public MotionTaskExecutionFeedback(long cmdId, int result) {
		this.cmd_id = cmdId;
		this.result = result;
	}
	
	/**
	 * 
	 * @return
	 */
	public PlatformFeedbackType getResult() {
		return RESULTS[this.result];
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cmd_id ^ (cmd_id >>> 32));
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
		MotionTaskExecutionFeedback other = (MotionTaskExecutionFeedback) obj;
		if (cmd_id != other.cmd_id)
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		// JSON style description
		return "{ \"cmd_id\": " + this.cmd_id + ", \"result\": " +  this.getResult() + "}";
	}
}

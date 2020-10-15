package com.github.ros.roxanne_sa.platform.msgs;

import com.github.ros.roxanne_sa.control.lang.PlatformFeedbackType;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class TokenExecutionFeedback
{
	private static final PlatformFeedbackType[] RESULTS = new PlatformFeedbackType[] {
			
			PlatformFeedbackType.SUCCESS,		// index 0 - success		
			
			PlatformFeedbackType.FAILURE,		// index 1 - failure
			
			
	};
	
	private long cmdId;					// id of the dispatched command the feedback refers to
	private int code;					// a code about the result: {0 - successful execution, 1 - execution failure}
	
	/**
	 * 
	 */
	public TokenExecutionFeedback() {
		// rosbridge compatibility issues
	}
	
	/**
	 * 
	 * @param cmdId
	 * @param code
	 */
	public TokenExecutionFeedback(long cmdId, int code) {
		this.cmdId = cmdId;
		this.code = code;		// set result code
	}
	
	/**
	 * 
	 * @return
	 */
	public long getCmdId() {
		return cmdId;
	}

	/**
	 * 
	 * @param cmdId
	 */
	public void setCmdId(long cmdId) {
		this.cmdId = cmdId;
	}

	/**
	 * 
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * 
	 * @return
	 */
	public PlatformFeedbackType getFeedbackType() {
		// get feedback according to the execution code
		return RESULTS[this.code];
	}
	
	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cmdId ^ (cmdId >>> 32));
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
		TokenExecutionFeedback other = (TokenExecutionFeedback) obj;
		if (cmdId != other.cmdId)
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		// JSON style description
		return "{\"id\" : " + this.cmdId + ", \"code\": " + this.code + "}";
	}
}

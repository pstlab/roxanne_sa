package com.github.ros.roxanne_sa.platform.msgs;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class TokenExecution 
{
	private long commandId;			// command id
	private int commandType;		// type of command: {0 - stop exedcution, 1 - start execution}
	private Token token;			// dispatched token
	
	/**
	 * 
	 */
	public TokenExecution() {
		// rosbrdige compatibility issue
	}
	
	/**
	 * 
	 * @param id
	 * @param commandType
	 * @param token
	 */
	public TokenExecution(long id, int commandType, Token token) {
		this.commandId = id;
		this.commandType = commandType;
		this.token = token;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getCommandId() {
		return commandId;
	}

	/**
	 * 
	 * @param commandId
	 */
	public void setCommandId(long commandId) {
		this.commandId = commandId;
	}

	/**
	 * 
	 * @return
	 */
	public int getCommandType() {
		return commandType;
	}

	/**
	 * 
	 * @param commandType
	 */
	public void setCommandType(int commandType) {
		this.commandType = commandType;
	}

	/**
	 * 
	 * @return
	 */
	public Token getToken() {
		return token;
	}

	/**
	 * 
	 * @param token
	 */
	public void setToken(Token token) {
		this.token = token;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (commandId ^ (commandId >>> 32));
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
		TokenExecution other = (TokenExecution) obj;
		if (commandId != other.commandId)
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		// JSON style description
		return "{ \"cmdId\": " +  this.commandId+", \"cmdType\": " + this.commandType +", \"token\": " + this.token + "}";
	}
}

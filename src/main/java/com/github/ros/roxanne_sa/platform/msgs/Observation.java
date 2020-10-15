package com.github.ros.roxanne_sa.platform.msgs;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class Observation 
{
	private long id;
	private Token token;
	
	/**
	 * 
	 */
	public Observation() {
		// rosbridge compatibility issues
	}
	
	/**
	 * 
	 * @param id
	 * @param token
	 */
	public Observation(long id, Token token) {
		this.id = id;
		this.token = token;
	}

	/**
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
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
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Observation other = (Observation) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		// JSON style description
		return "{\"id\": " + this.id + ", \"token\": " + this.token +"}";
	}
	
}

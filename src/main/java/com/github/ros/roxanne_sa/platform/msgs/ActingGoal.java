package com.github.ros.roxanne_sa.platform.msgs;

import java.util.Arrays;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class ActingGoal 
{
	private long id;
	private Token[] goals;			// set of tasks to perform
	private Token[] facts;			// set of known facts about the current state of the environment
	
	/**
	 * 
	 */
	public ActingGoal() {
		// rosbridge compatibility issues
	}
	
	/**
	 * 
	 * @param id
	 * @param goals
	 * @param facts
	 */
	public ActingGoal(long id, Token[] goals, Token[] facts) {
		this.id = id;
		this.goals = goals;
		this.facts = facts;
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
	public Token[] getGoals() {
		return goals;
	}

	/**
	 * 
	 * @param goals
	 */
	public void setGoals(Token[] goals) {
		this.goals = goals;
	}

	/**
	 * 
	 * @return
	 */
	public Token[] getFacts() {
		return facts;
	}

	/**
	 * 
	 * @param facts
	 */
	public void setFacts(Token[] facts) {
		this.facts = facts;
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
		ActingGoal other = (ActingGoal) obj;
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
		return "{\"id\": " + this.id + ", \"goals\": " + Arrays.toString(this.goals) +", \"facts\": " + Arrays.toString(this.facts) + "}";
	}
	
}

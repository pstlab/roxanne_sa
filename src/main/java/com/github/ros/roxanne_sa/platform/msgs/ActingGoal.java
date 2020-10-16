package com.github.ros.roxanne_sa.platform.msgs;

import java.util.Arrays;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class ActingGoal 
{
	private long goalId;
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
		this.goalId = id;
		this.goals = goals;
		this.facts = facts;
	}

	/**
	 * 
	 * @return
	 */
	public long getGoalId() {
		return goalId;
	}

	/**
	 * 
	 * @param id
	 */
	public void setGoalId(long id) {
		this.goalId = id;
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
		result = prime * result + (int) (goalId ^ (goalId >>> 32));
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
		if (goalId != other.goalId)
			return false;
		return true;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		// JSON style description
		return "{\"goalId\": " + this.goalId + ", \"goals\": " + Arrays.toString(this.goals) +", \"facts\": " + Arrays.toString(this.facts) + "}";
	}
	
}

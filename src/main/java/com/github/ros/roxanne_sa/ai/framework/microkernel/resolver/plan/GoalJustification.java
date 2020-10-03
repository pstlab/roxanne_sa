package com.github.ros.roxanne_sa.ai.framework.microkernel.resolver.plan;

import com.github.ros.roxanne_sa.ai.framework.domain.component.Decision;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.flaw.FlawSolution;

/**
 * 
 * @author anacleto
 *
 */
public abstract class GoalJustification extends FlawSolution 
{
	protected Decision decision;
	protected JustificationType type; 
	
	/**
	 * 
	 * @param goal
	 * @param type
	 * @param cost
	 */
	protected GoalJustification(Goal goal, JustificationType type, double cost) {
		super(goal, cost);
		this.type = type;
		this.decision = goal.getDecision();
	}
	
	/**
	 * 
	 * @return
	 */
	public Decision getGoalDecision() {
		return decision;
	}
	
	/*
	 * 
	 */
	public JustificationType getJustificationType() {
		return type;
	}
	
	/**
	 * 
	 * @author anacleto
	 *
	 */
	enum JustificationType {
		
		/**
		 * 
		 */
		EXPANSION,
		
		/**
		 * 
		 */
		UNIFICATION
	}
}

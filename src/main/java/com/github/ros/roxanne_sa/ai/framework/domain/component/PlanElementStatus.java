package com.github.ros.roxanne_sa.ai.framework.domain.component;

/**
 * 
 * @author anacleto
 *
 */
public enum PlanElementStatus 
{
	/**
	 * A decision not yet justified, i.e. 
	 * a decision which does not belongs to the plan yet
	 */
	PENDING,
	
	/**
	 * A decision of the plan
	 */
	ACTIVE,
	
	/**
	 * 
	 */
	SILENT;
}

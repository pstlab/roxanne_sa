package com.github.ros.roxanne_sa.ai.framework.domain.component.pdb;

/**
 * 
 * @author anacleto
 *
 */
public interface PlanDataBaseObserver 
{
	/**
	 * 
	 * @param solution
	 */
	public void notify(PlanDataBaseEvent event);
}

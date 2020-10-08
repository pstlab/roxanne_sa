package com.github.ros.roxanne_sa.ai.framework.parameter.csp.event;

import com.github.ros.roxanne_sa.ai.framework.parameter.lang.constraints.ParameterConstraint;

/**
 * 
 * @author anacleto
 *
 */
public class DelConstraintParameterNotification extends ParameterNotification 
{
	private ParameterConstraint constraint;
	
	/**
	 * 
	 */
	protected DelConstraintParameterNotification() {
		super(ParameterNotificationType.DEL_CONSTRAINT);
	}
	
	/**
	 * 
	 * @param constraint
	 */
	public void setConstraint(ParameterConstraint constraint) {
		this.constraint = constraint;
	}
	
	/**
	 * 
	 * @return
	 */
	public ParameterConstraint getParameterConstraint() {
		return this.constraint;
	}
}
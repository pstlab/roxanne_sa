package com.github.ros.roxanne_sa.ai.framework.parameter.lang.constraints;

import com.github.ros.roxanne_sa.ai.framework.parameter.lang.Parameter;

/**
 * 
 * @author anacleto
 *
 */
public abstract class BinaryParameterConstraint extends ParameterConstraint 
{
	protected Parameter<?> target;
	
	/**
	 * 
	 * @param type
	 */
	protected BinaryParameterConstraint(ParameterConstraintType type) {
		super(type);
	}
	
	/**
	 * 
	 * @param param
	 */
	public void setTarget(Parameter<?> param) {
		this.target = param;
	}
			
	
	/**
	 * 
	 * @return
	 */
	public Parameter<?> getTarget() {
		return this.target;
	}
}

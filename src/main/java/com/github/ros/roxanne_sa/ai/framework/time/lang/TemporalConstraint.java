package com.github.ros.roxanne_sa.ai.framework.time.lang;

import com.github.ros.roxanne_sa.ai.framework.domain.component.Constraint;
import com.github.ros.roxanne_sa.ai.framework.time.tn.TimePointDistanceConstraint;

/**
 * 
 * @author anacleto
 *
 */
public abstract class TemporalConstraint extends Constraint 
{
	protected TemporalConstraintType type;
	protected TimePointDistanceConstraint[] propagated;			// propagated distance constraints between time points
	
	/**
	 * 
	 * @param type
	 */
	protected TemporalConstraint(TemporalConstraintType type) {
		super(type.getLabel(), type.getCategory());
		this.type = type;
		this.propagated = null;
	}
	
	
	
	/**
	 * 
	 * @return
	 */
	public TemporalConstraintType getType() {
		return type;
	}
	
	/**
	 * 
	 * @param constraints
	 */
	public void setPropagatedConstraints(TimePointDistanceConstraint[] constraints) {
		this.propagated = constraints;
	}
	
	/**
	 * 
	 * @return
	 */
	public TimePointDistanceConstraint[] getPropagatedConstraints() {
		return propagated;
	}
	
	/**
	 * 
	 */
	public void clear() {
		this.propagated = null;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[TemporalConstraint type= " + this.type.getLabel() + "]";
	}
}

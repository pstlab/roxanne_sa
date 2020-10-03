package com.github.ros.roxanne_sa.ai.framework.time.lang;

import com.github.ros.roxanne_sa.ai.framework.microkernel.ConstraintCategory;
import com.github.ros.roxanne_sa.ai.framework.time.lang.allen.AfterIntervalConstraint;
import com.github.ros.roxanne_sa.ai.framework.time.lang.allen.BeforeIntervalConstraint;
import com.github.ros.roxanne_sa.ai.framework.time.lang.allen.ContainsIntervalConstraint;
import com.github.ros.roxanne_sa.ai.framework.time.lang.allen.DuringIntervalConstraint;
import com.github.ros.roxanne_sa.ai.framework.time.lang.allen.EndsDuringIntervalConstraint;
import com.github.ros.roxanne_sa.ai.framework.time.lang.allen.EqualsIntervalConstraint;
import com.github.ros.roxanne_sa.ai.framework.time.lang.allen.MeetsIntervalConstraint;
import com.github.ros.roxanne_sa.ai.framework.time.lang.allen.MetByIntervalConstraint;
import com.github.ros.roxanne_sa.ai.framework.time.lang.allen.StartsDuringIntervalConstraint;
import com.github.ros.roxanne_sa.ai.framework.time.tn.TimePointDistanceConstraint;

/**
 * 
 * @author anacleto
 *
 */
public enum TemporalConstraintType
{
	/**
	 * BEFORE Allen's quantified temporal relation
	 */
	BEFORE(BeforeIntervalConstraint.class.getName(),
			ConstraintCategory.TEMPORAL_CONSTRAINT),
	
	/**
	 * MEETS Allen's temporal relation
	 */
	MEETS(MeetsIntervalConstraint.class.getName(),
			ConstraintCategory.TEMPORAL_CONSTRAINT),
	
	/**
	 * CONTAINS Allen's quantified temporal relation
	 */
	CONTAINS(ContainsIntervalConstraint.class.getName(),
			ConstraintCategory.TEMPORAL_CONSTRAINT),
	
	/**
	 * EQUALS Allen's temporal relation
	 */
	EQUALS(EqualsIntervalConstraint.class.getName(),
			ConstraintCategory.TEMPORAL_CONSTRAINT),
	
	/**
	 * DURING Allen's quantified temporal relation
	 */
	DURING(DuringIntervalConstraint.class.getName(),
			ConstraintCategory.TEMPORAL_CONSTRAINT),
	
	/**
	 * MET-BY Allen's quantified temporal relation
	 */
	MET_BY(MetByIntervalConstraint.class.getName(),
			ConstraintCategory.TEMPORAL_CONSTRAINT),
	
	/**
	 * AFTER Allen's quantified temporal relation
	 */
	AFTER(AfterIntervalConstraint.class.getName(),
			ConstraintCategory.TEMPORAL_CONSTRAINT),
	
	/**
	 * STARTS-DURING quantified interval constraint
	 */
	STARTS_DURING(StartsDuringIntervalConstraint.class.getName(),
			ConstraintCategory.TEMPORAL_CONSTRAINT),
	
	/**
	 * ENDS-DURING quantified interval constraint
	 */
	ENDS_DURING(EndsDuringIntervalConstraint.class.getName(),
			ConstraintCategory.TEMPORAL_CONSTRAINT),
	
	/**
	 * 
	 */
	FIX_TIME_POINT(FixTimePointConstraint.class.getName(),
			ConstraintCategory.TEMPORAL_CONSTRAINT),
	
	/**
	 * Fix the duration of a flexible interval
	 */
	FIX_INTERVAL_DURATION(FixIntervalDurationConstraint.class.getName(),
			ConstraintCategory.TEMPORAL_CONSTRAINT),
	
	/**
	 * 
	 */
	TIME_POINT_DISTANCE(TimePointDistanceConstraint.class.getName(),
			ConstraintCategory.TEMPORAL_CONSTRAINT);

	// constraint class name
	private String constraintClassName;
	private ConstraintCategory category;
	
	/**
	 * 
	 * @param className
	 * @param category
	 */
	private TemporalConstraintType(String className, ConstraintCategory category) {
		this.constraintClassName = className;
		this.category = category;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getConstraintClassName() {
		return this.constraintClassName;
	}
	
	/**
	 * 
	 * @return
	 */
	public ConstraintCategory getCategory() {
		return category;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getLabel() {
		return this.name();
	}
}

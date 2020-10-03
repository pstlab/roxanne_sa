package com.github.ros.roxanne_sa.ai.framework.time.lang.allen;

import com.github.ros.roxanne_sa.ai.framework.time.TemporalInterval;
import com.github.ros.roxanne_sa.ai.framework.time.lang.BinaryTemporalConstraint;
import com.github.ros.roxanne_sa.ai.framework.time.lang.TemporalConstraintType;

/**
 * 
 * @author anacleto
 *
 */
public final class AfterIntervalConstraint extends BinaryTemporalConstraint<TemporalInterval, TemporalInterval> 
{
	private long lb;
	private long ub;
	
	/**
	 * 
	 */
	protected AfterIntervalConstraint() {
		super(TemporalConstraintType.AFTER);
	}
	
	/**
	 * 
	 * @param lb
	 */
	public void setLowerBound(long lb) {
		this.lb = lb;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getLowerBound() {
		return lb;
	}
	
	
	/**
	 * 
	 * @param ub
	 */
	public void setUpperBound(long ub) {
		this.ub = ub;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getUpperBound() {
		return ub;
	}
}

package com.github.ros.roxanne_sa.ai.framework.time.lang.allen;

import com.github.ros.roxanne_sa.ai.framework.time.TemporalInterval;
import com.github.ros.roxanne_sa.ai.framework.time.lang.BinaryTemporalConstraint;
import com.github.ros.roxanne_sa.ai.framework.time.lang.TemporalConstraintType;

/**
 * 
 * @author alessandroumbrico
 *
 */
public final class MeetsIntervalConstraint extends BinaryTemporalConstraint<TemporalInterval, TemporalInterval> 
{
	private long lb;
	private long ub;
	
	/**
	 * 
	 */
	protected MeetsIntervalConstraint() {
		super(TemporalConstraintType.MEETS);
		this.lb = 0;
		this.ub = 0;
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
	 * @return
	 */
	public long getUpperBound() {
		return ub;
	}
}

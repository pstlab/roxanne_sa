package com.github.ros.roxanne_sa.ai.framework.time.lang.allen;

import com.github.ros.roxanne_sa.ai.framework.time.TemporalInterval;
import com.github.ros.roxanne_sa.ai.framework.time.lang.BinaryTemporalConstraint;
import com.github.ros.roxanne_sa.ai.framework.time.lang.TemporalConstraintType;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class EqualsIntervalConstraint extends BinaryTemporalConstraint<TemporalInterval, TemporalInterval>
{
	private long[] startTimeBounds;
	private long[] endTimeBounds;
	
	/**
	 * 
	 */
	protected EqualsIntervalConstraint() {
		super(TemporalConstraintType.EQUALS);
		this.startTimeBounds = new long[] {0, 0};
		this.endTimeBounds = new long[] {0, 0};
	}
	
	/**
	 * 
	 * @return
	 */
	public long[] getStartTimeBounds() {
		return startTimeBounds;
	}
	
	/**
	 * 
	 * @return
	 */
	public long[] getEndTimeBounds() {
		return endTimeBounds;
	}
}

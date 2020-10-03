package com.github.ros.roxanne_sa.ai.framework.time.lang;

import com.github.ros.roxanne_sa.ai.framework.time.tn.TimePoint;

/**
 * 
 * @author anacleto
 *
 */
public class FixTimePointConstraint extends UnaryTemporalConstraint<TimePoint> 
{
	private long time;
	
	/**
	 * 
	 */
	protected FixTimePointConstraint() {
		super(TemporalConstraintType.FIX_TIME_POINT);
	}
	
	/**
	 * 
	 */
	public void setTime(long time) {
		this.time = time;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getTime() {
		return time;
	}
}

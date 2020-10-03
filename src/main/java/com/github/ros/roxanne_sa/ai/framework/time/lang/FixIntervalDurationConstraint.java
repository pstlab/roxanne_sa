package com.github.ros.roxanne_sa.ai.framework.time.lang;

import com.github.ros.roxanne_sa.ai.framework.time.TemporalInterval;

/**
 * 
 * @author anacleto
 *
 */
public class FixIntervalDurationConstraint extends UnaryTemporalConstraint<TemporalInterval>
{
	private long duration;
	
	/**
	 * 
	 */
	protected FixIntervalDurationConstraint() {
		super(TemporalConstraintType.FIX_INTERVAL_DURATION);
	}
	
	/**
	 * 
	 * @param duration
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getDuration() {
		return duration;
	}
}

package com.github.ros.roxanne_sa.ai.framework.time.tn.lang.query;

import com.github.ros.roxanne_sa.ai.framework.microkernel.query.TemporalQueryType;
import com.github.ros.roxanne_sa.ai.framework.time.tn.TimePoint;

/**
 * 
 * @author anacleto
 *
 */
public final class TimePointDistanceFromOriginQuery extends TimePointQuery {
	
	private TimePoint tp;
	private long[] distance;		// distance
	
	/**
	 * 
	 */
	protected TimePointDistanceFromOriginQuery() {
		super(TemporalQueryType.TP_DISTANCE_FROM_ORIGIN);
		this.distance = new long[2];	
	}
	
	/**
	 * 
	 * @param tp
	 */
	public void setTimePoint(TimePoint tp) {
		this.tp = tp;
	}

	/**
	 * 
	 * @return
	 */
	public TimePoint getTimePoint() {
		return this.tp;
	}
	
	/**
	 * 
	 * @return
	 */
	public long[] getDistance() {
		return distance;
	}
	
	/**
	 * 
	 * @param distance
	 */
	public void setDistance(long[] distance) {
		this.distance = distance;
	}
}

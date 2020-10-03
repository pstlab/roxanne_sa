package com.github.ros.roxanne_sa.ai.framework.time.tn.lang.query;

import com.github.ros.roxanne_sa.ai.framework.microkernel.query.TemporalQueryType;
import com.github.ros.roxanne_sa.ai.framework.time.tn.TimePoint;

/**
 * 
 * @author anacleto
 *
 */
public final class TimePointScheduleQuery extends TimePointQuery {

	protected TimePoint point;
	
	/**
	 * 
	 */
	protected TimePointScheduleQuery() {
		super(TemporalQueryType.TP_SCHEDULE);
	}
	
	/**
	 * 
	 * @param point
	 */
	public void setTimePoint(TimePoint point) {
		this.point = point;
	}
	
	/**
	 * 
	 * @return
	 */
	public TimePoint getTimePoint() {
		return point;
	}
//	
//	/**
//	 * 
//	 * @param lb
//	 */
//	public void setLb(long lb) {
//		this.point.setLowerBound(lb);
//	}
//	
//	/**
//	 * 
//	 * @param ub
//	 */
//	public void setUb(long ub) {
//		this.point.setUpperBound(ub);
//	}
}

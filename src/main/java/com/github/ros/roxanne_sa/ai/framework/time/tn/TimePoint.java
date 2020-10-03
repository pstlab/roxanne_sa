package com.github.ros.roxanne_sa.ai.framework.time.tn;

/**
 * 
 * @author anacleto
 *
 */
public class TimePoint extends TemporalData
{
	private long domLb;
	private long domUb;
	
	// computed values
	private long lb;
	private long ub;
	private double execTime;
	
	/**
	 * 
	 * @param id
	 * @param lb
	 * @param ub
	 */
	protected TimePoint(int id, long lb, long ub) {
		super(id);
		this.domLb = lb;
		this.domUb = ub;
		// initialize data
		this.lb = this.domLb;
		this.ub = this.domUb;
	}
	
	/**
	 * 
	 * @param lb
	 */
	protected void setDomLb(long lb) {
		this.domLb = lb;
	}
	
	/**
	 * 
	 * @param ub
	 */
	protected void setDomUb(long ub) {
		this.domUb = ub;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getDomLb() {
		return this.domLb;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getDomUb() {
		return this.domUb;
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
	
	/**
	 * 
	 * @param execTime
	 */
	public void setExecTime(double execTime) {
		this.execTime = execTime;
	}
	
	/**
	 * 
	 * @return
	 */
	public double getExecTime() {
		return execTime;
	}
	
	/**
	 * 
	 * @return
	 */
	public TimePoint clone() {
		TimePoint clone = new TimePoint(this.id, this.domLb, this.domUb);
		clone.setLowerBound(this.lb);
		clone.setUpperBound(this.ub);
		clone.setExecTime(this.execTime);
		return clone;
	}
	
	/**
	 * 
	 */
	@Override
	public int compareTo(TemporalData o) {
		// get time point 
		TimePoint p = (TimePoint) o;
		return this.lb < p.lb ? -1 : this.lb > p.lb ? 1 : 0;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[TimePoint id=" + this.id + ", lb= " + this.lb + ", ub= " + this.ub + "]";
	}
}

package com.github.ros.roxanne_sa.ai.framework.protocol.lang.relation;

import com.github.ros.roxanne_sa.ai.framework.protocol.lang.TokenProtocolDescriptor;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class OverlappedByRelationProtocolDescriptor extends RelationProtocolDescriptor 
{
	private long horizon;
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @param horizon
	 */
	protected OverlappedByRelationProtocolDescriptor(TokenProtocolDescriptor from, TokenProtocolDescriptor to, long horizon) {
		super("overlapped_by", from, to);
		this.horizon = horizon;
	}
	
	/**
	 * 
	 */
	@Override
	public String export() {
		return this.from.getTimeline().getName() + " " + this.from.getId() + " "
				+ "start_before_end" + " [" + (this.first[0] > this.horizon ? "infty" : this.first[0]) + "," + (this.first[1] > this.horizon ? "infty" : this.first[1]) + "] "
				+ this.to.getTimeline().getName() + " " + this.to.getId() + "\n"
				+ "\t\t"
				+ this.to.getTimeline().getName() + " " + this.to.getId() + " "
				+ "end_before_end" + " [0, infty] "
				+this.from.getTimeline().getName() + " " + this.from.getId();
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return this.export();
	}
}

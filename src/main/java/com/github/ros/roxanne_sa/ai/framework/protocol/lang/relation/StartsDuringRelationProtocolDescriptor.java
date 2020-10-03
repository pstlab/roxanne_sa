package com.github.ros.roxanne_sa.ai.framework.protocol.lang.relation;

import com.github.ros.roxanne_sa.ai.framework.protocol.lang.TokenProtocolDescriptor;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class StartsDuringRelationProtocolDescriptor extends RelationProtocolDescriptor 
{
	private long horizon;
	
	/**
	 * 
	 * @param from
	 * @param to
	 * @param horizon
	 */
	public StartsDuringRelationProtocolDescriptor(TokenProtocolDescriptor from, TokenProtocolDescriptor to, long horizon) {
		super("starts_during", from, to);
		this.horizon = horizon;
	}
	
	/**
	 * 
	 */
	@Override
	public String export() {
		return this.to.getTimeline().getName() + " " + this.to.getId() + " "
				+ "start_before_start" + " [" + (this.first[0] > this.horizon ? "infty" : this.first[0]) + "," + (this.first[1] > this.horizon ? "infty" : this.first[1]) + "] "
				+ this.from.getTimeline().getName() + " " + this.from.getId() + "\n"
				+ "\t\t"
				+ this.from.getTimeline().getName() + " " + this.from.getId() + " "
				+ "start_before_end" + " [" + (this.second[0] > this.horizon ? "infty" : this.second[0]) + "," + (this.second[1] > this.horizon ? "infty" : this.second[1]) + "] "
				+ this.to.getTimeline().getName() + " " + this.to.getId();
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return this.export();
	}
}

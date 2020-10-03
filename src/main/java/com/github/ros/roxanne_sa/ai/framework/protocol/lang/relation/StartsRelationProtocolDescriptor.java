package com.github.ros.roxanne_sa.ai.framework.protocol.lang.relation;

import com.github.ros.roxanne_sa.ai.framework.protocol.lang.TokenProtocolDescriptor;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class StartsRelationProtocolDescriptor extends RelationProtocolDescriptor 
{
	/**
	 * 
	 * @param from
	 * @param to
	 */
	protected StartsRelationProtocolDescriptor(TokenProtocolDescriptor from, TokenProtocolDescriptor to) {
		super("starts", from, to);
	}
	
	/**
	 * 
	 */
	@Override
	public String export() {
		return this.from.getTimeline().getName() + " " + this.from.getId() + " "
				+ this.type + "  " + this.to.getTimeline().getName() + " " 
				+ this.to.getId();
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return this.export();
	}
}

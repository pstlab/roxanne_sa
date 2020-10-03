package com.github.ros.roxanne_sa.ai.framework.protocol.lang;

import com.github.ros.roxanne_sa.ai.executive.pdb.ExecutionNodeStatus;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class UnallocatedTokenDescription extends TokenProtocolDescriptor {
	
	/**
	 * 
	 * @param id
	 * @param timeline
	 */
	protected UnallocatedTokenDescription(int id, TimelineProtocolDescriptor timeline) {
		super(id, timeline, "unallocated",ExecutionNodeStatus.EXECUTED);
	}
	
	/**
	 * 
	 */
	@Override
	public String export() {
		return this.getPredicate() + " " + this.getId() + " { "
				+ "[" + this.getEndTimeBounds()[0] + "," + this.getEndTimeBounds()[1] + "] "
				+ "[" + this.getDurationBounds()[0] + "," + this.getDurationBounds()[1] + "] }";
	}
}

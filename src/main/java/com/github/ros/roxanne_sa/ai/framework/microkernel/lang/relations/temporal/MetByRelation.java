package com.github.ros.roxanne_sa.ai.framework.microkernel.lang.relations.temporal;

import com.github.ros.roxanne_sa.ai.framework.domain.component.Decision;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.relations.RelationType;
import com.github.ros.roxanne_sa.ai.framework.time.lang.TemporalConstraintFactory;
import com.github.ros.roxanne_sa.ai.framework.time.lang.TemporalConstraintType;
import com.github.ros.roxanne_sa.ai.framework.time.lang.allen.MetByIntervalConstraint;

/**
 * 
 * @author anacleto
 *
 */
public class MetByRelation extends TemporalRelation 
{
	private TemporalConstraintFactory factory;
	private long[] bound;
	
	/**
	 * 
	 * @param id
	 * @param reference
	 * @param target
	 */
	protected MetByRelation(int id, Decision reference, Decision target) {
		super(id, RelationType.MET_BY, reference, target);
		this.bound = new long[] {0, 0};
		// get factory
		this.factory = new TemporalConstraintFactory();
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public long[][] getBounds() {
		return new long[][] {{0, 0}};
	}
	
	/**
	 * 
	 * @return
	 */
	public long getDistanceLowerBound() {
		return this.bound[0];
	}
	
	/**
	 * 
	 */
	public long getDistasnceUpperBound() {
		return this.bound[1];
	}
	
	/**
	 * 
	 */
	@Override
	public void setBounds(long[][] bounds) {
		this.bound = new long[] {0, 0};
	}
	
	/**
	 * 
	 */
	@Override
	public TemporalConstraintType getConstraintType() {
		return TemporalConstraintType.MET_BY;
	}
	
	/**
	 * 
	 */
	@Override
	public MetByIntervalConstraint create() {
		// create constraint
		MetByIntervalConstraint c = this.factory.create(
				TemporalConstraintType.MET_BY);

		// set intervals
		c.setReference(this.reference.getToken().getInterval());
		c.setTarget(this.target.getToken().getInterval());
		
		// set constraint
		this.constraint = c;
		// get constraint
		return c;
	}
}

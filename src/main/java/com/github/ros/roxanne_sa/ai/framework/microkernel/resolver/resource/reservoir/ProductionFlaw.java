package com.github.ros.roxanne_sa.ai.framework.microkernel.resolver.resource.reservoir;

import com.github.ros.roxanne_sa.ai.framework.domain.component.Decision;
import com.github.ros.roxanne_sa.ai.framework.domain.component.resource.reservoir.ProductionResourceEvent;
import com.github.ros.roxanne_sa.ai.framework.domain.component.resource.reservoir.ReservoirResource;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.flaw.Flaw;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.flaw.FlawType;

/**
 * 
 * @author anacleto
 *
 */
public abstract class ProductionFlaw extends Flaw 
{
	protected ProductionResourceEvent production;			// production event
	protected double delta;
	
	/**
	 * 
	 * @param id
	 * @param resource
	 * @param production
	 * @param delta
	 */
	protected ProductionFlaw(int id, ReservoirResource resource, ProductionResourceEvent production, double delta) {
		super(id, resource, FlawType.RESOURCE_PRODUCTION_UPDATE);
		this.production = production;
		this.delta = delta;
	}
	
	/**
	 * 
	 * @return
	 */
	public Decision getProduction() {
		return this.production.getDecision();
	}
	
	/**
	 * 
	 * @return
	 */
	public double getProducedAmount() {
		return production.getAmount();
	}
	
	/**
	 * 
	 * @return
	 */
	public double getDelta() {
		return delta;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[ProductionFlaw produced= " + this.production.getAmount() + " delta= " + delta + "]";
	}
}

package com.github.ros.roxanne_sa.ai.framework.microkernel.resolver.resource.reservoir;

import com.github.ros.roxanne_sa.ai.framework.domain.component.resource.reservoir.ProductionResourceEvent;
import com.github.ros.roxanne_sa.ai.framework.domain.component.resource.reservoir.ReservoirResource;

/**
 * 
 * @author anacleto
 *
 */
public class ProductionLeak extends ProductionFlaw 
{
	/**
	 * 
	 * @param id
	 * @param resource
	 * @param event
	 * @param delta
	 */
	protected ProductionLeak(int id, ReservoirResource resource, ProductionResourceEvent event, double delta) {
		super(id, resource, event, delta);
	}
}

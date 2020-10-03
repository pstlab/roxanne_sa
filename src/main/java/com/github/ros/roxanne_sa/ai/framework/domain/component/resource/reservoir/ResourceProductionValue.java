package com.github.ros.roxanne_sa.ai.framework.domain.component.resource.reservoir;

import com.github.ros.roxanne_sa.ai.framework.domain.component.ComponentValueType;

/**
 * 
 * @author anacleto
 *
 */
public class ResourceProductionValue extends ResourceUsageValue
{
	/**
	 * 
	 * @param label
	 * @param duration
	 * @param controllable
	 * @param resource
	 */
	protected ResourceProductionValue(String label, long[] duration, boolean controllable, ReservoirResource resource) {
		super(ComponentValueType.RESOURCE_PRODUCTION, label,  duration, controllable, resource);
	}
}
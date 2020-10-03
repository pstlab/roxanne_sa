package com.github.ros.roxanne_sa.ai.framework.domain.component.resource.reservoir;

import com.github.ros.roxanne_sa.ai.framework.domain.component.ComponentValue;
import com.github.ros.roxanne_sa.ai.framework.domain.component.ComponentValueType;

/**
 * 
 * @author anacleto
 *
 */
public abstract class ResourceUsageValue extends ComponentValue 
{
	/**
	 * 
	 * @param type
	 * @param label
	 * @param duration
	 * @param controllable
	 * @param component
	 */
	protected ResourceUsageValue(ComponentValueType type, String label, long[] duration, boolean controllable, ReservoirResource component) {
		super(label, type, duration, controllable, component);
	}
}


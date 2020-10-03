package com.github.ros.roxanne_sa.ai.framework.domain.component.resource.reservoir;

import com.github.ros.roxanne_sa.ai.framework.domain.component.Decision;
import com.github.ros.roxanne_sa.ai.framework.domain.component.resource.ResourceEvent;
import com.github.ros.roxanne_sa.ai.framework.domain.component.resource.ResourceEventType;
import com.github.ros.roxanne_sa.ai.framework.time.tn.TimePoint;

/**
 * 
 * @author anacleto
 *
 */
public class ProductionResourceEvent extends ResourceEvent<TimePoint>
{
	/**
	 * 
	 * @param activity
	 * @param amount
	 */
	protected ProductionResourceEvent(Decision activity, int amount) {
		super(ResourceEventType.PRODUCTION, activity, amount, activity.getToken().getInterval().getEndTime());
	}
}

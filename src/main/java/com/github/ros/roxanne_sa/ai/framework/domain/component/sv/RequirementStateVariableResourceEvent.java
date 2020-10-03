package com.github.ros.roxanne_sa.ai.framework.domain.component.sv;

import com.github.ros.roxanne_sa.ai.framework.domain.component.Decision;
import com.github.ros.roxanne_sa.ai.framework.domain.component.resource.discrete.RequirementResourceEvent;

/**
 * 
 * @author anacleto
 *
 */
public class RequirementStateVariableResourceEvent extends RequirementResourceEvent 
{
	/**
	 * 
	 * @param activity
	 */
	protected RequirementStateVariableResourceEvent(Decision activity) {
		super(activity, 1);
	}
}

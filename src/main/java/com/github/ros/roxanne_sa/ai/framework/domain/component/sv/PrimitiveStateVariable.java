package com.github.ros.roxanne_sa.ai.framework.domain.component.sv;

import com.github.ros.roxanne_sa.ai.framework.domain.component.DomainComponentType;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.framework.DomainComponentConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.resolver.ResolverType;

/**
 * 
 */
public final class PrimitiveStateVariable extends StateVariable
{
	/**
	 * 
	 * @param name
	 */
	@DomainComponentConfiguration(resolvers = {
			// planning resolver
			ResolverType.PLAN_REFINEMENT,
			// scheduling resolver
			ResolverType.TIMELINE_SCHEDULING_RESOLVER,
			// time-line gap resolver
			ResolverType.TIMELINE_BEHAVIOR_PLANNING_RESOLVER,
	})
	protected PrimitiveStateVariable(String name) {
		super(name, DomainComponentType.SV_PRIMITIVE);
	}
}

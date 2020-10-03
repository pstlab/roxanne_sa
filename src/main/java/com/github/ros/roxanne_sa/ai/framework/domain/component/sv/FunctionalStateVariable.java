package com.github.ros.roxanne_sa.ai.framework.domain.component.sv;

import com.github.ros.roxanne_sa.ai.framework.domain.component.DomainComponentType;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.framework.DomainComponentConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.resolver.ResolverType;

/**
 * 
 * @author anacleto
 *
 */
public class FunctionalStateVariable extends StateVariable 
{
	/**
	 * 
	 * @param name
	 */
	@DomainComponentConfiguration(resolvers = {
			// planning resolver
			ResolverType.PLAN_REFINEMENT
	})
	protected FunctionalStateVariable(String name) {
		super(name, DomainComponentType.SV_FUNCTIONAL);
	}
}

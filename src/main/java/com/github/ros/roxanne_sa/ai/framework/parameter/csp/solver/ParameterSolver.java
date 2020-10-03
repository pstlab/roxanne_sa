package com.github.ros.roxanne_sa.ai.framework.parameter.csp.solver;

import com.github.ros.roxanne_sa.ai.framework.microkernel.FrameworkObject;
import com.github.ros.roxanne_sa.ai.framework.parameter.csp.event.ParameterNotification;
import com.github.ros.roxanne_sa.ai.framework.parameter.csp.event.ParameterNotificationObserver;
import com.github.ros.roxanne_sa.ai.framework.parameter.lang.Parameter;

/**
 * 
 * @author anacleto
 *
 */
public abstract class ParameterSolver extends FrameworkObject implements ParameterNotificationObserver 
{
	/**
	 * 
	 * @param type
	 */
	protected ParameterSolver() {
		super();
	}
	
	/**
	 * 
	 * @return
	 */
	public abstract boolean isConsistent();
	
	/**
	 * 
	 */
	public abstract void computeSolution();
	
	/**
	 * 
	 * @param param
	 */
	public abstract void computeValues(Parameter<?> param);
	
	/**
	 * 
	 */
	@Override
	public abstract void update(ParameterNotification info);
}

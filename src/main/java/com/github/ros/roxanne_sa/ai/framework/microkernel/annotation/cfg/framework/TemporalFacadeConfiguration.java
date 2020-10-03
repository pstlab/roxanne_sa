package com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.ros.roxanne_sa.ai.framework.time.solver.TemporalSolverType;
import com.github.ros.roxanne_sa.ai.framework.time.tn.TemporalNetworkType;

/**
 * 
 * @author anacleto
 *
 */
@Target({
	ElementType.TYPE,
	ElementType.CONSTRUCTOR
})
@Retention(RetentionPolicy.RUNTIME)
public @interface TemporalFacadeConfiguration {

	/**
	 * 
	 * @return
	 */
	TemporalNetworkType network();
	
	/**
	 * 
	 * @return
	 */
	TemporalSolverType solver();
}

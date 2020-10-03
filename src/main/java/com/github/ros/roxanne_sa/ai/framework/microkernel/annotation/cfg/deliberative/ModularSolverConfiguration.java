package com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.ros.roxanne_sa.ai.deliberative.solver.ModularSolver;

/**
 * 
 * @author anacleto
 *
 */
@Target({
	ElementType.FIELD,
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ModularSolverConfiguration {

	// reference to solver algorithm
	Class<? extends ModularSolver> solver();
}

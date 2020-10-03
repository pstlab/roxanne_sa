package com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.ros.roxanne_sa.ai.deliberative.heuristic.FlawSelectionHeuristic;

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
public @interface FlawSelectionHeuristicsConfiguration {

	// flaw selection heuristics type
	Class<? extends FlawSelectionHeuristic> heuristics();
}

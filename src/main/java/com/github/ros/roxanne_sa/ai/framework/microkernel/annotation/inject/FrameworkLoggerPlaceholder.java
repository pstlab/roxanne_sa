package com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.ros.roxanne_sa.ai.framework.utils.log.FrameworkLoggingLevel;

/**
 * 
 * @author anacleto
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FrameworkLoggerPlaceholder {

	FrameworkLoggingLevel level() default FrameworkLoggingLevel.DEBUG;
}

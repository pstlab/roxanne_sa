package com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.executive;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.ros.roxanne_sa.ai.executive.dispatcher.Dispatcher;

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
public @interface DispatcherConfiguration {

	/**
	 * 
	 * @return
	 */
	Class<? extends Dispatcher> dispatcher();
}

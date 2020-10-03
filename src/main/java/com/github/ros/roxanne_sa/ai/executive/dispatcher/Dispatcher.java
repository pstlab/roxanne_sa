package com.github.ros.roxanne_sa.ai.executive.dispatcher;

import com.github.ros.roxanne_sa.ai.executive.Executive;
import com.github.ros.roxanne_sa.ai.executive.lang.ex.ExecutionException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.FrameworkObject;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject.executive.ExecutivePlaceholder;
import com.github.ros.roxanne_sa.platform.lang.ex.PlatformException;

/**
 * 
 * @author anacleto
 *
 */
public abstract class Dispatcher<T extends Executive> extends FrameworkObject 
{
	@ExecutivePlaceholder
	protected T executive;
	
	/**
	 * 
	 */
	public void clear() {
		// nothing to do
	}
	
	/**
	 * The method handle the current tick of the executor's clock
	 * 
	 * @param tick
	 * @throws ExecutionException
	 * @throws PlatformException
	 */
	public abstract void handleTick(long tick) 
			throws ExecutionException, PlatformException; 
}

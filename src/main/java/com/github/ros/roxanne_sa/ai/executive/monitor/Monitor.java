package com.github.ros.roxanne_sa.ai.executive.monitor;

import java.util.ArrayList;
import java.util.List;

import com.github.ros.roxanne_sa.ai.executive.Executive;
import com.github.ros.roxanne_sa.ai.executive.lang.ExecutionFeedback;
import com.github.ros.roxanne_sa.ai.executive.lang.ex.ExecutionException;
import com.github.ros.roxanne_sa.ai.executive.lang.failure.ExecutionFailureCause;
import com.github.ros.roxanne_sa.ai.framework.microkernel.FrameworkObject;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject.executive.ExecutivePlaceholder;
import com.github.ros.roxanne_sa.platform.lang.ex.PlatformException;

/**
 * 
 * @author anacleto
 *
 */
public abstract class Monitor<T extends Executive> extends FrameworkObject
{
	@ExecutivePlaceholder
	protected T executive;
	
	protected List<ExecutionFeedback> observations;
	
	/**
	 * 
	 */
	protected Monitor() {
		super();
		// set list of observations
		this.observations = new ArrayList<ExecutionFeedback>();
	}
	
	/**
	 * 
	 * @param feedback
	 */
	public void addExecutionFeedback(ExecutionFeedback feedback) {
		// protected access to the list of observations
		synchronized (this.observations) {
			// add received feedback
			this.observations.add(feedback);
			this.observations.notifyAll();
		}
	}

	/**
	 * 
	 */
	public void clear() {
		// clear internal structures
		synchronized (this.observations) {
			this.observations.clear();
		}
	}

	/**
	 * 
	 * @return
	 */
	protected boolean hasObservations()
	{
		// content flag
		boolean hasObservations = true;
		// protect access to the list of observations
		synchronized (this.observations) {
			// check if empty
			hasObservations = !this.observations.isEmpty();
		}
		
		// get result
		return hasObservations;
	}
	
	/**
	 * 
	 * @return
	 */
	protected ExecutionFeedback next() {
		// next execution feedback
		ExecutionFeedback feedback = null;
		// protect access to the list of observations
		synchronized (this.observations) {
			// remove the first element of the queue
			feedback = this.observations.remove(0);
		}
		
		// get next feedback to handle
		return feedback;
	}
	
	/**
	 * 
	 * @param tick
	 * @throws ExecutionException
	 * @throws PlatformException
	 */
	public abstract void handleTick(long tick) 
			throws ExecutionException, PlatformException;
	
	/**
	 * 
	 * @param tick
	 * @param cause
	 * @throws PlatformException
	 */
	public abstract void handleExecutionFailure(long tick, ExecutionFailureCause cause) 
			throws PlatformException;
}

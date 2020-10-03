package com.github.ros.roxanne_sa.ai.framework.microkernel.lang.problem;

import com.github.ros.roxanne_sa.ai.framework.domain.component.ComponentValue;

/**
 * 
 * @author anacleto
 *
 */
public class ProblemGoal extends ProblemFluent
{
	/**
	 * 
	 * @param value
	 * @param labels
	 * @param start
	 * @param end
	 * @param duration
	 */
	protected ProblemGoal(ComponentValue value, String[] labels, 
			long[] start, long[] end, long[] duration) {
		super(ProblemFluentType.GOAL, value, labels, start, end, duration);
	}
}

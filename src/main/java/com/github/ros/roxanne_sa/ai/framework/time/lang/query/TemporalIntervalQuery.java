package com.github.ros.roxanne_sa.ai.framework.time.lang.query;

import com.github.ros.roxanne_sa.ai.framework.microkernel.query.TemporalQuery;
import com.github.ros.roxanne_sa.ai.framework.microkernel.query.TemporalQueryType;

/**
 * 
 * @author anacleto
 *
 */
public abstract class TemporalIntervalQuery extends TemporalQuery {

	/**
	 * 
	 * @param type
	 */
	protected TemporalIntervalQuery(TemporalQueryType type) {
		super(type);
	}
}

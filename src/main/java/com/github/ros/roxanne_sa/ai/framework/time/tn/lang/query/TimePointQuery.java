package com.github.ros.roxanne_sa.ai.framework.time.tn.lang.query;

import com.github.ros.roxanne_sa.ai.framework.microkernel.query.TemporalQuery;
import com.github.ros.roxanne_sa.ai.framework.microkernel.query.TemporalQueryType;

/**
 * 
 * @author anacleto
 *
 */
public abstract class TimePointQuery extends TemporalQuery {

	/**
	 * 
	 * @param type
	 */
	protected TimePointQuery(TemporalQueryType type) {
		super(type);
	}
}

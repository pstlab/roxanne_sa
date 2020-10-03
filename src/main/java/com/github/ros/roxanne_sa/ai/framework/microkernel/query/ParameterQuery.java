package com.github.ros.roxanne_sa.ai.framework.microkernel.query;

/**
 * 
 * @author anacleto
 *
 */
public abstract class ParameterQuery extends Query 
{
	protected ParameterQueryType type;
	
	/**
	 * 
	 * @param type
	 */
	protected ParameterQuery(ParameterQueryType type) {
		super();
		this.type = type;
	}

	/**
	 * 
	 * @return
	 */
	public ParameterQueryType getType() {
		return type;
	}
}

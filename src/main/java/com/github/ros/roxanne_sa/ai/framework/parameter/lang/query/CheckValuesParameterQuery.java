package com.github.ros.roxanne_sa.ai.framework.parameter.lang.query;

import com.github.ros.roxanne_sa.ai.framework.microkernel.query.ParameterQuery;
import com.github.ros.roxanne_sa.ai.framework.microkernel.query.ParameterQueryType;
import com.github.ros.roxanne_sa.ai.framework.parameter.lang.Parameter;

/**
 * 
 * @author anacleto
 *
 */
public class CheckValuesParameterQuery extends ParameterQuery 
{
	private Parameter<?> param;
	
	/**
	 * 
	 */
	protected CheckValuesParameterQuery() {
		super(ParameterQueryType.CHECK_PARAMETER_VALUES);
	}
	
	/**
	 * 
	 * @param param
	 */
	public void setParameter(Parameter<?> param) {
		this.param = param;
	}
	
	/**
	 * 
	 * @return
	 */
	public Parameter<?> getParameter() {
		return param;
	}
}

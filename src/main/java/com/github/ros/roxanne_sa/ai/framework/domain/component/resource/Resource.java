package com.github.ros.roxanne_sa.ai.framework.domain.component.resource;

import com.github.ros.roxanne_sa.ai.framework.domain.component.Decision;
import com.github.ros.roxanne_sa.ai.framework.domain.component.DomainComponent;
import com.github.ros.roxanne_sa.ai.framework.domain.component.DomainComponentType;
import com.github.ros.roxanne_sa.ai.framework.microkernel.query.ParameterQueryType;
import com.github.ros.roxanne_sa.ai.framework.parameter.lang.NumericParameter;
import com.github.ros.roxanne_sa.ai.framework.parameter.lang.query.CheckValuesParameterQuery;

/**
 * 
 * @author anacleto
 *
 */
public abstract class Resource extends DomainComponent
{	
	protected int min;							// minimum resource capacity
	protected int max;							// maximum resource capacity
	protected int initial;						// initial resource capacity
	
	/**
	 * 
	 * @param name
	 * @param type
	 */
	protected Resource(String name, DomainComponentType type) {
		super(name, type);
	}
	
	/**
	 * 
	 * @return
	 */
	public long getMinCapacity() {
		return this.min;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getMaxCapacity() {
		return this.max;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getInitialLevel() {
		return this.initial;
	}
	

	/**
	 * 
	 * @param min
	 */
	public void setMinCapacity(int min) {
		this.min = min;
	}

	/**
	 * 
	 * @param max
	 */
	public void setMaxCapacity(int max) { 
		this.max = max;
	}
	
	/**
	 * 
	 * @param initial
	 */
	public void setInitialCapacity(int initial) {
		this.initial = initial;
	}
	
//	/**
//	 * 
//	 */
//	@Override
//	public void checkPseudoControllability() 
//			throws PseudoControllabilityCheckException {
//		// nothing to do
//	}

	/**
	 * 
	 * @param activity
	 * @return
	 */
	protected int getRequiredAmountOfResource(Decision activity)
	{
		// get value of resource
		NumericParameter param = (NumericParameter) activity.getParameterByIndex(0);
		// prepare query
		CheckValuesParameterQuery query = this.pdb.createQuery(ParameterQueryType.CHECK_PARAMETER_VALUES);
		// set parameter
		query.setParameter(param);
		// process query
		this.pdb.process(query);
		// get computed parameter
		return param.getLowerBound();
	}
}

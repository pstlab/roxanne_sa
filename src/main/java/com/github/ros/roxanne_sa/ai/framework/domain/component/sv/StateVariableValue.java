package com.github.ros.roxanne_sa.ai.framework.domain.component.sv;

import com.github.ros.roxanne_sa.ai.framework.domain.component.ComponentValue;
import com.github.ros.roxanne_sa.ai.framework.domain.component.ComponentValueType;

/**
 * 
 * @author anacleto
 *
 */
public class StateVariableValue extends ComponentValue
{
	/**
	 * 
	 * @param value
	 * @param duration
	 * @param controllable
	 * @param sv
	 */
	protected StateVariableValue(String value, long[] duration, boolean controllable, StateVariable sv) {
		super(value, ComponentValueType.STATE_VARIABLE_VALUE, duration, controllable, sv);
	}
}

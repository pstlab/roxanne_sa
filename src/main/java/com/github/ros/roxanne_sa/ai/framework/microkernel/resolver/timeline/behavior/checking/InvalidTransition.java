package com.github.ros.roxanne_sa.ai.framework.microkernel.resolver.timeline.behavior.checking;

import com.github.ros.roxanne_sa.ai.framework.domain.component.Decision;
import com.github.ros.roxanne_sa.ai.framework.domain.component.sv.StateVariable;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.flaw.Flaw;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.flaw.FlawType;

/**
 * 
 * @author anacleto
 *
 */
public class InvalidTransition extends Flaw {
	
	private Decision left;
	private Decision right;

	/**
	 * 
	 * @param sv
	 * @param left
	 * @param right
	 */
	protected InvalidTransition(int id, StateVariable sv, Decision left, Decision right) {
		super(id, sv, FlawType.TIMELINE_BEHAVIOR_CHECKING);
		this.left = left;
		this.right = right;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isSolvable() {
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public Decision getLeftDecision() {
		return left;
	}
	
	/**
	 * 
	 * @return
	 */
	public Decision getRightDecision() {
		return right;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[InvalidTransition from= " + this.left + " to= " + this.right +"]";
	}
}

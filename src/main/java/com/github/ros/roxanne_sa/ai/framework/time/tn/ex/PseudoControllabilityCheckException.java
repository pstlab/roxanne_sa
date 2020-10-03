package com.github.ros.roxanne_sa.ai.framework.time.tn.ex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.ros.roxanne_sa.ai.framework.domain.component.Decision;
import com.github.ros.roxanne_sa.ai.framework.domain.component.DomainComponent;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.ConsistencyCheckException;

/**
 * 
 * @author anacleto
 *
 */
public class PseudoControllabilityCheckException extends ConsistencyCheckException {
	private static final long serialVersionUID = 1L;

	private Map<DomainComponent, List<Decision>> issues;
	
	/**
	 * 
	 * @param msg
	 */
	public PseudoControllabilityCheckException(String msg) {
		super(msg);
		this.issues = new HashMap<>();
	}
	
	/**
	 * 
	 * @param value
	 */
	public void addIssue(Decision dec) {
		if (!issues.containsKey(dec.getComponent())) {
			this.issues.put(dec.getComponent(), new ArrayList<Decision>());
		}
		// add issue
		this.issues.get(dec.getComponent()).add(dec);
	}
	
	/**
	 * 
	 * @param issues
	 */
	public void setPseudoControllabilityIssues(Map<DomainComponent, List<Decision>> issues) {
		this.issues = issues;
	}

	/**
	 * 
	 * @return
	 */
	public Map<DomainComponent, List<Decision>> getPseudoControllabilityIssues() {
		return new HashMap<>(this.issues);
	}
}

package com.github.ros.roxanne_sa.ai.framework.time.solver;

import com.github.ros.roxanne_sa.ai.framework.time.solver.apsp.APSPTemporalSolver;

/**
 * 
 * @author anacleto
 *
 */
public enum TemporalSolverType {

	/**
	 * All-Pair-Shortest-Path temporal reasoning engine
	 */
	APSP(APSPTemporalSolver.class.getName());
	
	private String cname;
	
	/**
	 * 
	 * @param cname
	 */
	private TemporalSolverType(String cname) {
		this.cname = cname;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getClassName() {
		return cname;
	}
}

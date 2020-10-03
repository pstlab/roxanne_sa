package com.github.ros.roxanne_sa.ai.framework.compiler;

import com.github.ros.roxanne_sa.ai.framework.compiler.ex.PDLFileMissingException;
import com.github.ros.roxanne_sa.ai.framework.domain.component.PlanDataBase;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.SynchronizationCycleException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.problem.Problem;

/**
 * 
 * @author anacleto
 *
 */
public abstract class DomainCompiler {

	protected DomainCompilerType type;
	protected String ddlFilePath;
	protected String pdlFilePath;
	
	/**
	 * 
	 * @param type
	 * @param domain
	 * @param problem
	 */
	protected DomainCompiler(DomainCompilerType type, String domain, String problem) {
		this.type = type;
		this.ddlFilePath = domain;
		this.pdlFilePath = problem;
	}

	/**
	 * 
	 * @param type
	 * @param domain
	 */
	protected DomainCompiler(DomainCompilerType type, String domain) {
		this.type = type;
		this.ddlFilePath = domain;
		this.pdlFilePath = null;
	}
	
	/**
	 * 
	 * @return
	 */
	public DomainCompilerType getType() {
		return type;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getDdlFilePath() {
		return ddlFilePath;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPdlFilePath() {
		return pdlFilePath;
	}
	
	/**
	 * 
	 * @return
	 * @throws SynchronizationCycleException
	 */
	public abstract PlanDataBase compileDomain() 
			throws SynchronizationCycleException;
	
	/**
	 * 
	 * @param pdb
	 * @return
	 * @throws PDLFileMissingException
	 */
	public abstract Problem compileProblem(PlanDataBase pdb) 
			throws PDLFileMissingException;
}

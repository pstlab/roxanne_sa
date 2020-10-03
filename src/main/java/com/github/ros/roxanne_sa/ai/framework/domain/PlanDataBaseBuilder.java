package com.github.ros.roxanne_sa.ai.framework.domain;

import java.lang.reflect.Constructor;

import com.github.ros.roxanne_sa.ai.framework.compiler.DomainCompilerFactory;
import com.github.ros.roxanne_sa.ai.framework.compiler.DomainCompilerType;
import com.github.ros.roxanne_sa.ai.framework.compiler.ddl.v3.DDLv3Compiler;
import com.github.ros.roxanne_sa.ai.framework.domain.component.DomainComponentType;
import com.github.ros.roxanne_sa.ai.framework.domain.component.PlanDataBase;
import com.github.ros.roxanne_sa.ai.framework.domain.component.pdb.PlanDataBaseComponent;
import com.github.ros.roxanne_sa.ai.framework.domain.knowledge.DomainKnowledge;
import com.github.ros.roxanne_sa.ai.framework.domain.knowledge.DomainKnowledgeType;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.framework.DomainKnowledgeConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.framework.ParameterFacadeConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.framework.TemporalFacadeConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject.framework.DomainKnowledgePlaceholder;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject.framework.PlanDataBasePlaceholder;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.lifecycle.PostConstruct;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.ProblemInitializationException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.SynchronizationCycleException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.problem.Problem;
import com.github.ros.roxanne_sa.ai.framework.parameter.ParameterFacade;
import com.github.ros.roxanne_sa.ai.framework.parameter.ParameterFacadeBuilder;
import com.github.ros.roxanne_sa.ai.framework.time.TemporalFacade;
import com.github.ros.roxanne_sa.ai.framework.time.TemporalFacadeBuilder;
import com.github.ros.roxanne_sa.ai.framework.utils.reflection.FrameworkReflectionUtils;

/**
 * 
 * @author anacleto
 *
 */
public class PlanDataBaseBuilder 
{
	// domain compiler type
	private static final DomainCompilerType DEFAULT_COMPILER = DomainCompilerType.DDLv3;
	
	
	/**
	 * 
	 * @param ddl
	 * @param pdl
	 * @return
	 * @throws SynchronizationCycleException
	 * @throws ProblemInitializationException
	 */
	public synchronized static PlanDataBase createAndSet(String ddl, String pdl) 
			throws SynchronizationCycleException, ProblemInitializationException
	{
		// get compiler factory
		DomainCompilerFactory factory = DomainCompilerFactory.getInstance();
		// create compiler
		DDLv3Compiler compiler = factory.create(DEFAULT_COMPILER, ddl, pdl);
		
		// compile domain
		PlanDataBase pdb = compiler.compileDomain();
		// compile problem
		Problem problem = compiler.compileProblem(pdb);
		// setup problem
		pdb.setup(problem);
		// get resulting plan database
		return pdb;
	}
	
	/**
	 * 
	 * @param ddl
	 * @return
	 * @throws SynchronizationCycleException
	 */
	public synchronized static PlanDataBase createAndSet(String ddl) 
			throws SynchronizationCycleException
	{
		// get compiler factory
		DomainCompilerFactory factory = DomainCompilerFactory.getInstance();
		// create compiler
		DDLv3Compiler compiler = factory.create(DEFAULT_COMPILER, ddl);
		
		// compile domain
		PlanDataBase pdb = compiler.compileDomain();
		// get resulting plan database
		return pdb;
	}
	
	
	/**
	 * 
	 * @param name
	 * @param origin
	 * @param horizon
	 * @return
	 */
	public synchronized static PlanDataBase createAndSet(String name, long origin, long horizon)
	{
//		try
//		{
//			// get framework logger configuration
//			FrameworkLoggerConfiguration lAnnot = FrameworkReflectionUtils.doFindnAnnotation(PlanDataBaseComponent.class, FrameworkLoggerConfiguration.class);
//			// create logger
//			FrameworkLogger logger = doCreateFrameworkLogger(lAnnot.level());
//			
//			// inject logger reference
//			FrameworkReflectionUtils.doInjectStaticReferenceThroughAnnotation(PlanDataBaseComponent.class, FrameworkLoggerPlaceholder.class, logger);
//		}
//		catch (Exception ex) {
//			throw new RuntimeException("Error while injecting logger into the framework:\n- message: " + ex.getMessage() + "\n");
//		}
		
		// get temporal facade configuration
		TemporalFacadeConfiguration tAnnot = FrameworkReflectionUtils.doFindnAnnotation(PlanDataBaseComponent.class, TemporalFacadeConfiguration.class);
		// create temporal facade
		TemporalFacade tf = TemporalFacadeBuilder.createAndSet(tAnnot, origin, horizon);
		
		// get parameter facade configuration
		ParameterFacadeConfiguration pAnnot = FrameworkReflectionUtils.doFindnAnnotation(PlanDataBaseComponent.class, ParameterFacadeConfiguration.class);
		// create parameter facade
		ParameterFacade pf = ParameterFacadeBuilder.createAndSet(pAnnot);

		
		// create plan database component instance
		PlanDataBaseComponent comp = DomainComponentBuilder.createAndSet(name, DomainComponentType.PLAN_DATABASE, tf, pf);
		
		try
		{
			// create and set domain knowledge
			DomainKnowledgeConfiguration annot = FrameworkReflectionUtils.doFindnAnnotation(comp.getClass(), DomainKnowledgeConfiguration.class);
			DomainKnowledge knowledge = doCreateDomainKnowledge(annot.knowledge());
			// inject plan database reference into domain knowledge
			FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(knowledge, PlanDataBasePlaceholder.class, comp);
			
			try
			{
				// invoke post construct method
				FrameworkReflectionUtils.doInvokeMethodTaggedWithAnnotation(knowledge, PostConstruct.class);
			}
			catch (Exception ex) {
				throw new RuntimeException("Error while calling post construct method of domain knowledge\n- message: " + ex.getMessage() + "\n");
			}
			
			try
			{
				// inject domain knowledge into plan database
				FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(comp, DomainKnowledgePlaceholder.class, knowledge);
			}
			catch (Exception ex) {
				throw new RuntimeException("Error while injecting knowledge into the framework:\n- message: " + ex.getMessage() + "\n");
			}
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while injecting plan database into domain knowledge:\n- message: " + ex.getMessage() + "\n");
		}
		
		
		// get created domain component
		return comp;
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	private static <T extends DomainKnowledge> T doCreateDomainKnowledge(DomainKnowledgeType type) {
		// initialize knowledge instance
		T knowledge = null;
		try
		{
			// get domain knowledge class
			Class<?> clazz = Class.forName(type.getClassName());
			// get logger constructor
			@SuppressWarnings("unchecked")
			Constructor<T> c = (Constructor<T>) clazz.getDeclaredConstructor();
			// set accessible
			c.setAccessible(true);
			// create instance
			knowledge = c.newInstance();
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while creating domain knowledge from class: " + type.getClassName() + "\n- message: " + ex.getMessage() + "\n");
		}
		
		// get knowledge
		return knowledge;
	}
	
}
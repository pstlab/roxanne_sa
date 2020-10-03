package com.github.ros.roxanne_sa.ai.deliberative;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

import com.github.ros.roxanne_sa.ai.deliberative.heuristic.FlawSelectionHeuristic;
import com.github.ros.roxanne_sa.ai.deliberative.solver.ModularSolver;
import com.github.ros.roxanne_sa.ai.deliberative.solver.Solver;
import com.github.ros.roxanne_sa.ai.deliberative.strategy.SearchStrategy;
import com.github.ros.roxanne_sa.ai.framework.domain.component.PlanDataBase;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.FrameworkLoggerConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.FlawSelectionHeuristicsConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.ModularSolverConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.PlannerSolverConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.SearchStrategyConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject.FrameworkLoggerPlaceholder;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject.deliberative.FlawSelectionHeuristicPlaceholder;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject.deliberative.PlannerSolverPlaceholder;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject.deliberative.SearchStrategyPlaceholder;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.inject.framework.PlanDataBasePlaceholder;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.lifecycle.PostConstruct;
import com.github.ros.roxanne_sa.ai.framework.utils.log.FrameworkLogger;
import com.github.ros.roxanne_sa.ai.framework.utils.log.FrameworkLoggingLevel;
import com.github.ros.roxanne_sa.ai.framework.utils.reflection.FrameworkReflectionUtils;

/**
 * 
 * @author anacleto
 *
 */
public class PlannerBuilder 
{

	/**
	 * Create a default planning instance 
	 * 
	 * @param pdb
	 * @return
	 */
	public synchronized static Planner createAndSet(PlanDataBase pdb) {
		// create default planner
		return PlannerBuilder.createAndSet(Planner.class, pdb);
	}
	
	/**
	 * Create a custom planning instance
	 * 
	 * @param pClass
	 * @param pdb
	 * @return
	 */
	public synchronized static <T extends Planner> T createAndSet(Class<T> pClass, PlanDataBase pdb)
	{
		// get framework logger configuration
		FrameworkLoggerConfiguration lAnnot = FrameworkReflectionUtils.doFindnAnnotation(pClass, FrameworkLoggerConfiguration.class);
		// create logger
		FrameworkLogger logger = PlannerBuilder.doCreateFrameworkLogger(lAnnot.level());
		try
		{
			// inject static reference
			FrameworkReflectionUtils.doInjectStaticReferenceThroughAnnotation(pClass, FrameworkLoggerPlaceholder.class, logger);
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while injecting static logger reference:\n- message: " + ex.getMessage() + "\n");
		}
		
		
		// get heuristics configuration
		FlawSelectionHeuristicsConfiguration hAnnot = FrameworkReflectionUtils.
				doFindnAnnotation(pClass, FlawSelectionHeuristicsConfiguration.class);
		// create flaw selection heuristic
		FlawSelectionHeuristic heuristic = PlannerBuilder.doCreateHueristic(hAnnot.heuristics().getName());
		
		try
		{
			// inject plan database reference
			FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(heuristic, PlanDataBasePlaceholder.class, pdb);
		} 
		catch (Exception ex) {
			throw new RuntimeException("Error while injecting plan database reference into heuristics:\n- message: " + ex.getMessage() + "\n");
		}
		
		try
		{
			// call heuristics post construct method
			FrameworkReflectionUtils.doInvokeMethodTaggedWithAnnotation(heuristic, PostConstruct.class);
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while invoking post construct method on heuristics:\n- message: " + ex.getMessage() + "\n");
		}
		
		// get search strategy configuration
		SearchStrategyConfiguration ssAnnot = FrameworkReflectionUtils.doFindnAnnotation(pClass, SearchStrategyConfiguration.class);
		// create search strategy
		SearchStrategy strategy = PlannerBuilder.doCreateSearchStrategy(ssAnnot.strategy().getName());
		
		try
		{
			// inject plan database reference 
			FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(strategy, PlanDataBasePlaceholder.class, pdb);
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while injecting plan database referene into search strategy:\n- message: " + ex.getMessage() + "\n");
		}
		
		try
		{
			// call strategy post construct method
			FrameworkReflectionUtils.doInvokeMethodTaggedWithAnnotation(strategy, PostConstruct.class);
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while invoking post construct method on search strategy:\n- message: " + ex.getMessage() + "\n");
		}
		
		
		// get planner solver configuration
		PlannerSolverConfiguration psAnnot = FrameworkReflectionUtils.doFindnAnnotation(pClass, PlannerSolverConfiguration.class);
		// create planning solver 
		Solver solver = PlannerBuilder.doCreateSolver(psAnnot.solver().getName(), psAnnot.timeout());
		
		try
		{
			// inject plan database reference 
			FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(solver, PlanDataBasePlaceholder.class, pdb);
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while injecting plan database referene into planner solver:\n- message: " + ex.getMessage() + "\n");
		}
		
		try
		{
			// inject plan database reference 
			FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(solver, SearchStrategyPlaceholder.class, strategy);
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while injecting search strategy referene into planner solver:\n- message: " + ex.getMessage() + "\n");
		}
		
		try
		{
			// inject plan database reference 
			FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(solver, FlawSelectionHeuristicPlaceholder.class, heuristic);
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while injecting flaw selection heuristics referene into planner solver:\n- message: " + ex.getMessage() + "\n");
		}
		
		
		
		/*
		 * Create internal solvers in case of modular solvers
		 */
		
		
		// get solver fields annotated with modular solvers (if any)
		List<Field> fields = FrameworkReflectionUtils.doFindFieldsAnnotatedBy(solver.getClass(), ModularSolverConfiguration.class);
		for (Field field : fields)
		{
			// get modular solver annotation
			ModularSolverConfiguration msa = field.getAnnotation(ModularSolverConfiguration.class);	
			// get solver class
			Class<? extends Solver> ms = msa.solver();
			
			// get solver heuristics configuration
			FlawSelectionHeuristicsConfiguration msha = FrameworkReflectionUtils.doFindnAnnotation(ms, FlawSelectionHeuristicsConfiguration.class);
			// create heuristics 
			FlawSelectionHeuristic msh = PlannerBuilder.doCreateHueristic(msha.heuristics().getName());
			
			
			try
			{
				// inject plan database reference
				FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(msh, PlanDataBasePlaceholder.class, pdb);
			} 
			catch (Exception ex) {
				throw new RuntimeException("Error while injecting plan database reference into heuristics:\n- message: " + ex.getMessage() + "\n");
			}
			
			
			try
			{
				// call heuristics post construct method
				FrameworkReflectionUtils.doInvokeMethodTaggedWithAnnotation(msh, PostConstruct.class);
			}
			catch (Exception ex) {
				throw new RuntimeException("Error while invoking post construct method on heuristics:\n- message: " + ex.getMessage() + "\n");
			}
			
			// get solver strategy configuration
			SearchStrategyConfiguration mssa = FrameworkReflectionUtils.doFindnAnnotation(ms, SearchStrategyConfiguration.class);
			// create search strategy
			SearchStrategy mss = PlannerBuilder.doCreateSearchStrategy(mssa.strategy().getName());
			
			try
			{
				// inject plan database reference
				FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(mss, PlanDataBasePlaceholder.class, pdb);
			} 
			catch (Exception ex) {
				throw new RuntimeException("Error while injecting plan database reference into heuristics:\n- message: " + ex.getMessage() + "\n");
			}
			
			try
			{
				// call strategy post construct method
				FrameworkReflectionUtils.doInvokeMethodTaggedWithAnnotation(mss, PostConstruct.class);
			}
			catch (Exception ex) {
				throw new RuntimeException("Error while invoking post construct method on heuristics:\n- message: " + ex.getMessage() + "\n");
			}
			
			
			
			// create modular solver
			ModularSolver s = PlannerBuilder.doCreateSolver(ms.getName(), psAnnot.timeout());
			
			try
			{
				// inject plan database reference 
				FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(s, PlanDataBasePlaceholder.class, pdb);
			}
			catch (Exception ex) {
				throw new RuntimeException("Error while injecting plan database referene into planner solver:\n- message: " + ex.getMessage() + "\n");
			}
			
			try
			{
				// inject strategy reference 
				FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(s, SearchStrategyPlaceholder.class, mss);
			}
			catch (Exception ex) {
				throw new RuntimeException("Error while injecting search strategy referene into planner solver:\n- message: " + ex.getMessage() + "\n");
			}
			
			try
			{
				// inject heuristics reference 
				FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(s, FlawSelectionHeuristicPlaceholder.class, msh);
			}
			catch (Exception ex) {
				throw new RuntimeException("Error while injecting flaw selection heuristics referene into planner solver:\n- message: " + ex.getMessage() + "\n");
			}
			
			
			try
			{
				// call (modular) solver post construct
				FrameworkReflectionUtils.doInvokeMethodTaggedWithAnnotation(s, PostConstruct.class);
			}
			catch (Exception ex) {
				throw new RuntimeException("Error while invoking post construct method on planner solver:\n- message: " + ex.getMessage() + "\n");
			}
			
			
			
			
			try
			{
				// inject created modular solver to the "parent" solver
				field.setAccessible(true);
				field.set(solver, s);
			}
			catch (Exception ex) {
				throw new RuntimeException("Error while injecting flaw selection heuristics referene into planner solver:\n- message: " + ex.getMessage() + "\n");
			}
			
			
			
		}
		
		
		
		
		
		
		try
		{
			// call solver post construct method
			FrameworkReflectionUtils.doInvokeMethodTaggedWithAnnotation(solver, PostConstruct.class);
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while invoking post construct method on planner solver:\n- message: " + ex.getMessage() + "\n");
		}
		
		
		
		
		
		
		
		
		
		// create planner
		T planner = PlannerBuilder.doCreatePlanner(pClass.getName());
		
		try
		{
			// inject plan database reference 
			FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(planner, PlanDataBasePlaceholder.class, pdb);
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while injecting plan database referene into planner:\n- message: " + ex.getMessage() + "\n");
		}
		
		try
		{
			// inject solver reference 
			FrameworkReflectionUtils.doInjectReferenceThroughAnnotation(planner, PlannerSolverPlaceholder.class, solver);
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while injecting planner solver referene into planner:\n- message: " + ex.getMessage() + "\n");
		}
		
		try
		{
			// call planner post construct method
			FrameworkReflectionUtils.doInvokeMethodTaggedWithAnnotation(planner, PostConstruct.class);
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while invoking post construct method on planner:\n- message: " + ex.getMessage() + "\n");
		}
		
		// get planning instance
		return planner;
	}
	
	/**
	 * 
	 * @param cName
	 * @return
	 */
	private synchronized static <T extends Planner> T doCreatePlanner(String cName)
	{
		// create instance
		T planner = null;
		try
		{
			// get class
			Class<?> pClass = Class.forName(cName);
			// get constructor
			@SuppressWarnings("unchecked")
			Constructor<T> c = (Constructor<T>) pClass.getDeclaredConstructor();
			// set accessible
			c.setAccessible(true);
			// create instance
			planner = c.newInstance();
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while creating planner from class: " + cName + "\n");
		}
		
		// get created planner
		return planner;
	}
	
	/**
	 * 
	 * @param cName
	 * @return
	 */
	private synchronized static <T extends SearchStrategy> T doCreateSearchStrategy(String cName)
	{
		// create instance
		T strategy = null;
		try
		{
			// get class
			Class<?> ssClass = Class.forName(cName);
			// get constructor
			@SuppressWarnings("unchecked")
			Constructor<T> c = (Constructor<T>) ssClass.getDeclaredConstructor();
			// set accessible
			c.setAccessible(true);
			// create instance
			strategy = c.newInstance();
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while creating search strategy from class: " + cName + "\n");
		}
		
		// get created search strategy
		return strategy;
	}
	
	
	/**
	 * 
	 * @param cName
	 * @param timeout
	 * @return
	 */
	private synchronized static <T extends Solver> T doCreateSolver(String cName, long timeout)
	{
		// create instance
		T solver = null;
		try
		{
			// get class
			Class<?> psClass = Class.forName(cName);
			// get constructor
			@SuppressWarnings("unchecked")
			Constructor<T> c = (Constructor<T>) psClass.getDeclaredConstructor(Long.TYPE);
			// set accessible
			c.setAccessible(true);
			// create instance
			solver = c.newInstance(timeout);
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while creating planner solver from class: " + cName + "\n");
		}
		
		// get created planner solver
		return solver;
	}
	
	/**
	 * 
	 * @param cName
	 * @return
	 */
	private synchronized static <T extends FlawSelectionHeuristic> T doCreateHueristic(String cName)
	{
		// create instance
		T heuristics = null;
		try
		{
			// get class
			Class<?> hClass = Class.forName(cName);
			// get constructor
			@SuppressWarnings("unchecked")
			Constructor<T> c = (Constructor<T>) hClass.getDeclaredConstructor();
			// set accessible
			c.setAccessible(true);
			// create instance
			heuristics = c.newInstance();
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while creating flaw selection heuristic from class: " + cName + "\n");
		}
		
		// get created heuristics
		return heuristics;
	}
	
	
	/**
	 * 
	 * @param level
	 * @return
	 */
	private static FrameworkLogger doCreateFrameworkLogger(FrameworkLoggingLevel level)
	{
		// set logging instance
		FrameworkLogger logger = null;
		try
		{
			// get logger constructor
			Constructor<FrameworkLogger> c = FrameworkLogger.class.getDeclaredConstructor(FrameworkLoggingLevel.class);
			// set accessible
			c.setAccessible(true);
			// create instance
			logger = c.newInstance(level);
		}
		catch (Exception ex) {
			throw new RuntimeException("Error while creating framework logger from class: " + logger.getClass().getName() + "\n- message: " + ex.getMessage() + "\n");
		}
		
		// get logger
		return logger;
	}
}

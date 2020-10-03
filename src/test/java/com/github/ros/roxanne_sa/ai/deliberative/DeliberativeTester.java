package com.github.ros.roxanne_sa.ai.deliberative;

import java.io.BufferedWriter;
import java.io.FileWriter;

import com.github.ros.roxanne_sa.ai.framework.domain.PlanDataBaseBuilder;
import com.github.ros.roxanne_sa.ai.framework.domain.component.PlanDataBase;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.NoSolutionFoundException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.ProblemInitializationException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.plan.SolutionPlan;

/**
 * 
 * @author anacleto
 *
 */
public class DeliberativeTester 
{
//	private static final String DDL = "domains/sharework/stiima/hrc_mosaic_v1.ddl";
//	private static final String PDL = "domains/sharework/stiima/hrc_mosaic.pdl";
	
//	private static final String DDL = "domains/hrc_mosaic_gen.ddl";
//	private static final String PDL = "domains/hrc_mosaic_gen.pdl";
	
	private static final String DDL = "domains/satellite.ddl";
	private static final String PDL = "domains/satellite.pdl";
	private static final String OUT = "plans";
	
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{ 
		// get DDL and PDL files
		String ddl = args.length >= 2 && args[0] != null ? args[0] : DDL;
		String pdl = args.length >= 2 && args[1] != null ? args[1] : PDL;
		
		try 
		{
			// build the plan database
			PlanDataBase pdb = PlanDataBaseBuilder.createAndSet(ddl, pdl);
			// set a planning instance of the plan database
			Planner planner = PlannerBuilder.createAndSet(PlannerTest.class, pdb);

			// start planning
			SolutionPlan plan = planner.plan();
			// solution found
			System.out.println("----------------------------------\nSolution found after " + plan.getSolvingTime() + " msecs\n"
					+ "Solution plan:\n" + plan + "\n----------------------------------\n");

			// print plan to file 
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUT + "/plan.txt"))) { 
				// export plan encoding 
				String enconding = plan.export().toString();
				// write exported file
				writer.write(enconding);
				// print the resulting plan
				System.out.println("Output plan file: \"" + OUT + "/pan.txt"  + "\"");
			}
		}
		catch (NoSolutionFoundException ex) {
			// no solution found
			System.err.println(ex.getMessage());
		}
		catch (ProblemInitializationException ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace(System.err);
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
			ex.printStackTrace(System.err);
		}
	}
}

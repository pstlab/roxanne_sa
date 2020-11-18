package com.github.ros.roxanne_sa.ai.deliberative.solver;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.github.ros.roxanne_sa.ai.deliberative.heuristic.pipeline.PipelineFlawSelectionHeuristic;
import com.github.ros.roxanne_sa.ai.deliberative.strategy.CostDepthSearchStrategy;
import com.github.ros.roxanne_sa.ai.deliberative.strategy.ex.EmptyFringeException;
import com.github.ros.roxanne_sa.ai.framework.domain.component.PlanElementStatus;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.FlawSelectionHeuristicsConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.annotation.cfg.deliberative.SearchStrategyConfiguration;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.ConsistencyCheckException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.NoFlawFoundException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.NoSolutionFoundException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.ex.PlanRefinementException;
import com.github.ros.roxanne_sa.ai.framework.microkernel.lang.flaw.Flaw;
import com.github.ros.roxanne_sa.ai.framework.microkernel.resolver.ex.UnsolvableFlawException;
import com.github.ros.roxanne_sa.ai.framework.utils.properties.FilePropertyReader;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



/**
 * 
 * @author anacleto
 *
 */
@FlawSelectionHeuristicsConfiguration(
		heuristics = PipelineFlawSelectionHeuristic.class
)
@SearchStrategyConfiguration(
		strategy = CostDepthSearchStrategy.class
)
public class PseudoControllabilityAwareSolver extends Solver 
{
	/**
	 * 
	 * @param timeout
	 */
	protected PseudoControllabilityAwareSolver(long timeout) {
		super("PseudoControllabilityAwareSolver", timeout);
	}
	
	/**
	 * 
	 */
	@Override
	public SearchSpaceNode solve() 
			throws NoSolutionFoundException 
	{
		// set client connection
		MongoClient client = null;
		// prepare collection
		MongoCollection<Document> collection = null;
		// get deliberative property file
		FilePropertyReader properties = new FilePropertyReader(
				FRAMEWORK_HOME + FilePropertyReader.DEFAULT_DELIBERATIVE_PROPERTY);
		// get mongodb
		String mongodb = properties.getProperty("mongodb").trim();
		// check if exists
		if (mongodb != null && !mongodb.equals("")) 
		{
			// create a collection to the DB
			client = new MongoClient();
			// get db 
			MongoDatabase db = client.getDatabase(mongodb);
			// get collection
			collection = db.getCollection("search_data");
			// remove all data from the collection
			collection.drop();
		}
		
		
		// set solving start time
		long start = System.currentTimeMillis();
		// set solving step counter
		this.stepCounter = 0;
		// last extracted node
		SearchSpaceNode last = null, node = null;
		// search condition
		boolean search = true;
		// search a solution
		while (search) 
		{
			try 
			{
				
				// update step counter
				this.stepCounter++;
				// get time passed from the start 
				long now = System.currentTimeMillis() - start;
				// check timeout
				if (this.timeout > 0 && now > this.timeout) 
				{
					// no solution found stop search
					search = false;
					// set solving time
					this.time = System.currentTimeMillis() - start;
					// backtrack from the last propagated node
					this.backtrack(last);
					// timeout exception
					throw new NoSolutionFoundException("Timeout: no solution found after " + this.time + " msecs and " + this.stepCounter + " solving steps");
				}
				
				
				// extract a node from the fringe
				node = this.fringe.dequeue();

				// info message
				String info = "Extracted node [step = " + this.stepCounter + "]:\n"
						+ "node: " + node + "\n";
				// check operators 
				if (last != null) {
					info += "Operators:\n";
					// print last node operations
					for (Operator op : node.getOperators()) {
						info += "op: " + op + "\n";
					}
				}
				// info log 
				info(info);
				
				
				// check db collection
				if (collection != null) {
					// create solving statistic record
					Document doc = new Document("step", node.getId());
					doc.append("fringe-size", this.fringe.getFringeSize());
					doc.append("node-depth", node.getDepth());
					doc.append("node-cost", node.getCost());
					doc.append("node-heuristic", node.getPlanningHeuristic());
					doc.append("node-min-makespan", node.getMakespan()[0]);
					doc.append("node-max-makespan", node.getMakespan()[1]);
					doc.append("node-min-duration", node.getBehaviorDuration()[0]);
					doc.append("node-max-duration", node.getBehaviorDuration()[1]);
					doc.append("node-number-of-flaws", node.getNumberOfFlaws());
					// check additional metric
					if (node.getDomainSpecificMetric() != null) {
						// add additional metric
						doc.append("node-domain-specific-metric", node.getDomainSpecificMetric().toString());
					}
					
					// insert data into the collection
					collection.insertOne(doc);
				}
				
				// propagate extracted node
				this.contextSwitch(last, node);
				// updated last propagated node
				last = node;
				// check consistency of the resulting partial plan
				this.pdb.verify();
				
				// context switch done
				info("Context switch successfully done [step = " + this.stepCounter + "]:\n"
						+ "Plan: " + last.getPartialPlan() + "\n");
				
				// print information concerning current partial plan	
				debug("Detailed plan after propagation: "  + node.getGenerator() + "\n"
							+ "\tplan:\n"
							+ "\t\tdecisions= " + this.pdb.getPlan().getDecisions() + "\n"
							+ "\t\trelations= " + this.pdb.getPlan().getRelations() + "\n\n"
							+ "\tpending plan (agenda):\n"
							+ "\t\tdecisions= " + this.pdb.getPlan(PlanElementStatus.PENDING).getDecisions() + "\n"
							+ "\t\trelations= " + this.pdb.getPlan(PlanElementStatus.PENDING).getRelations() + "\n\n"
							+ "\tsilent plan:\n"
							+ "\t\tdecisions= " + this.pdb.getPlan(PlanElementStatus.SILENT).getDecisions() + "\n"
							+ "\t\trelations= " + this.pdb.getPlan(PlanElementStatus.SILENT).getRelations() + "\n\n");
				
 				// choose the best flaws to solve
				List<Flaw> flaws = new ArrayList<>(this.heuristic.choose());
				// create a branch for each "equivalent" flaw to solve next
				for (Flaw flaw : flaws)
				{
					// expand the search space with the available solutions of the flaw
					for (SearchSpaceNode child : this.expand(node, flaw)) {
						// add the node to the fringe
						this.fringe.enqueue(child);
						// expand the search space
						info("Search tree expansion:\n"
								+ "node: " + child + "\n"
								+ "generator: " + child.getGenerator() + "\n");
					}
				}
			}
			catch (PlanRefinementException ex) {
				// refinement error
				warning("Refinement error [step = " + this.stepCounter + "]:\n"
						+ "message: " + ex.getMessage() + "\n"
						+ "Plan:\n" + last.getPartialPlan() + "\n");
			}
			catch (UnsolvableFlawException ex) {
				// refinement error
				warning("Unsolvable flaw found  [step = " + this.stepCounter + "]:\n"
						+ "message: " + ex.getMessage() + "\n"
						+ "Plan:\n" + last.getPartialPlan() + "\n");
			}
			catch (ConsistencyCheckException ex) 
			{
				// context switch failure
				warning("Context switch failure [step = " + this.stepCounter + "]:\n"
						+ "message: " + ex.getMessage() + "\n"
						+ "Plan:\n" + last.getPartialPlan() + "\n");
			}
			catch (NoFlawFoundException ex)
			{
				// solution found stop search
				search = false;
				// set solving time
				this.time = System.currentTimeMillis() - start;
				// pseudo-controllable solution found
				info("Pseudo-controllable solution found after " + (this.time / 1000) + " (secs) and " + this.stepCounter + " solving steps\n");
			}
			catch (EmptyFringeException ex) 
			{
				// no solution found stop search
				search = false;
				// set solving time
				this.time = System.currentTimeMillis() - start;
				// backtrack from the last propagated node
				this.backtrack(last);
				// throw exception
				throw new NoSolutionFoundException("No pseudo-controllable solution found after " + (this.time / 1000) + " (secs) and " + this.stepCounter + " solving steps\n");
			}
			// close connection
			finally 
			{
				// check if stop
				if (!search) 
				{
					// clear search strategy
					this.fringe.clear();
					// close DB connection if necessary 
					if (client != null) {
						client.close();
					}
				}
			}
			
		} // end while
		
		
		// get last expanded node
		return last;
	}
}	

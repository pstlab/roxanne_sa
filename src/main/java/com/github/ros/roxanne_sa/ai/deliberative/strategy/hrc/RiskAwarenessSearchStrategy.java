package com.github.ros.roxanne_sa.ai.deliberative.strategy.hrc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.github.ros.roxanne_sa.ai.deliberative.solver.DecisionVariable;
import com.github.ros.roxanne_sa.ai.deliberative.solver.SearchSpaceNode;
import com.github.ros.roxanne_sa.ai.deliberative.strategy.SearchStrategy;
import com.github.ros.roxanne_sa.ai.framework.domain.component.DomainComponent;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RiskAwarenessSearchStrategy extends SearchStrategy 
{
	private MongoClient client;
	
	/**
	 * 
	 */
	protected RiskAwarenessSearchStrategy() {
		super("RiskAwarenessSearchStrategy");
		this.client = null;
	}
	/**
	 * 
	 */
	@Override
	public void enqueue(SearchSpaceNode node) 
	{
		
		
		// get planning distance
		double pDistance = this.computePlanningHeuristics(node);
		// set planning distance as heuristics
		node.setHeuristic(pDistance);
		
		// compute load balancing 
		double balancing = this.loadBalancing(node);
		// compute dynamic plan risk 
		double pRisk = this.computeDynamicRisk(node);
		// set additional metric
		node.setDomainSpecificMetric(new PlanMetrics(balancing, pRisk));
		
		// add the node to the priority queue
		this.fringe.offer(node);
	}
	
	/**
	 * 
	 */
	@Override
	public int compare(SearchSpaceNode o1, SearchSpaceNode o2) 
	{
		// get balancing heuristics
		double h1 = o1.getHeuristic();
		double h2 = o2.getHeuristic();

		// get balancing
		double b1 = ((PlanMetrics) o1.getDomainSpecificMetric()).balancing;
		double b2 = ((PlanMetrics) o2.getDomainSpecificMetric()).balancing;
		
		// get risks
		double r1 = ((PlanMetrics) o1.getDomainSpecificMetric()).risk;
		double r2 = ((PlanMetrics) o2.getDomainSpecificMetric()).risk;
		
		// multiobjective comparison
		return b1 < b2 && r1 < r2 ? -1 : b1 > b2 && r1 > r2 ? 1 :
			// check heuristic distance to solutions
			o1.getDepth() > o2.getDepth() ? -1 : o1.getDepth() < o2.getDepth() ? 1 :
				h1 < h2 ? -1 : h1 > h2 ? 1 :
					0;
		
	}
	
	
	/**
	 * 
	 */
	@Override
	public void clear() {
		super.clear();
		
		// clear connection if necessary
		if (this.client != null) {
			// close connection
			this.client.close();
			this.client = null;
		}
	}
	
	
	/**
	 * 
	 * @param collection
	 * @return
	 */
	private double computeDynamicRisk(SearchSpaceNode node) 
	{
		// set plan risk
		double risk = 0;
		// get collection
		MongoCollection<Document> dataset = this.getDataset();
		
		// get partila plan 
		Map<DomainComponent, List<DecisionVariable>> plan = node.getPartialPlan();
		
		// get robot component
		DomainComponent rComp = this.pdb.getComponentByName("Robot");
		// get human component
		DomainComponent hComp = this.pdb.getComponentByName("Human");
		// chceck variable
		if (rComp != null && hComp != null) 
		{
			// get robot tasks from the plan 
			List<DecisionVariable> rTasks = plan.get(rComp);
			if (rTasks != null) 
			{
				for (DecisionVariable rTask : rTasks) 
				{
					// set robot task risk
					double rTaskRisk = 0;
					// list of overlapping human tasks
					List<DecisionVariable> hOverTasks = new ArrayList<>();
					// check robot task
					if (!rTask.getValue().equals("Idle")) 
					{
						// check human overlapping tasks 
						List<DecisionVariable> hTasks = plan.get(hComp);
						if (hTasks != null) 
						{
							for (DecisionVariable hTask : hTasks) {
								// check value
								if (!hTask.getValue().equals("Idle")) {
									// check robot bounds
									long[] rTaskStart = rTask.getStart();
									long[] rTaskEnd = rTask.getEnd();
									// check human bounds 
									long[] hTaskStart = hTask.getStart();
									long[] hTaskEnd = hTask.getEnd();
									
									// check overlapping conditions
									if ((rTaskStart[0] <= hTaskStart[0] && rTaskEnd[0] >= hTaskStart[0]) || 
											(rTaskStart[0] >= hTaskStart[0] && hTaskEnd[0] >= rTaskStart[0])) 
									{
										// add human task to overlapping ones
										hOverTasks.add(hTask);
									}
									
									
								}
							}
						}
					}
					
					
					// compute risks by taking into account possible overlaps
					for (DecisionVariable hTask : hOverTasks) 
					{
						String[] rSplits = rTask.getValue().split("-");
						String[] hSplits = hTask.getValue().split("-");
						
						// set robot task name
						String rTaskName = rSplits[0].replace("_", "") + "-" + (rSplits[1].contains("box") ? rSplits[2] : rSplits[1]);
						String hTaskName = hSplits[0].replace("_", "") + "-" + (hSplits[1].contains("box") ? hSplits[2] : hSplits[1]);
						
						// get dynamic risk record from the dataset
						Document record = dataset.find(Filters.and(
								Filters.eq("human-task", hTaskName), Filters.eq("robot-task", rTaskName))).first();
						
						// check record
						if (record != null) 
						{
							// get risk value
							Object riskValue = record.get("risk-dynamic");
							if (riskValue instanceof Number) {
								// get risk from the record
								rTaskRisk += ((Number) riskValue).doubleValue();
							}
						}
					}
					
					if (!hOverTasks.isEmpty()) {
						// set average risk 
						rTaskRisk = rTaskRisk / hOverTasks.size();
					}
					
					// increment plan risk 
					risk += rTaskRisk;
				}
			}
		}
		
		// get risk
		return risk;
	}
	
	
	/**
	 * 
	 * @return
	 */
	private MongoCollection<Document> getDataset() 
	{
		// check if connection should be established
		if (this.client == null) {
			// craete client
			this.client = new MongoClient();
		}
		
		// get data-base
		MongoDatabase db = this.client.getDatabase("roxanne_mosaic"); 
		// select data-set
		MongoCollection<Document> collection = db.getCollection("hrc_risks_dynamics");
		// return collection
		return collection;
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private double loadBalancing(SearchSpaceNode node) {
		// number of tasks assigned to the human
		int hsize = 0;
		// number of tasks assigned to the operator
		int rsize = 0;
		
		// get partila plan 
		Map<DomainComponent, List<DecisionVariable>> plan = node.getPartialPlan();
		if (plan != null) 
		{
			// get the make span of the human component
			DomainComponent hComp = this.pdb.getComponentByName("Human");
			// check variable
			if (hComp != null) {
				// get partial plan of the component
				if (plan.containsKey(hComp)) {
					// get decisions
					List<DecisionVariable> list = plan.get(hComp);
					for (DecisionVariable var : list) {
						if (!var.getValue().toLowerCase().contains("idle")) {
							// increment H size
							hsize++;
						}
					}
				}
			}
			
		
			// get the makespan of the robot component
			DomainComponent rComp = this.pdb.getComponentByName("Robot");
			// check variable
			if (rComp != null) {
				// get partial plan of the component
				if (plan.containsKey(rComp)) {
					// get decisions
					List<DecisionVariable> list = plan.get(rComp);
					for (DecisionVariable var : list) {
						if (!var.getValue().toLowerCase().contains("idle")) {
							rsize++;
						}
					}
				}
			}
		}
		
		// check balancing as absolute value 
		return Math.abs(hsize - rsize);
	}
	
	/**
	 * 
	 * @author alessandroumbrico
	 *
	 */
	class PlanMetrics 
	{
		double balancing;
		double risk;
		
		/**
		 * 
		 * @param balancing
		 * @param risk
		 */
		protected PlanMetrics(double balancing, double risk) {
			this.balancing = balancing;
			this.risk = risk;
		}
		
		@Override
		public String toString() {
			return "[" + this.balancing +  ", " + this.risk + "]";
		}
	}
}

package com.github.ros.roxanne_sa.control.acting.rosbridge;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.github.ros.roxanne_sa.platform.rosbridge.RosBridgeGoalListener;

import it.cnr.istc.pst.platinum.ai.framework.utils.properties.FilePropertyReader;
import it.cnr.istc.pst.platinum.control.acting.GoalOrientedActingAgent;
import it.cnr.istc.pst.platinum.control.lang.Goal;
import ros.RosBridge;
import ros.SubscriptionRequestMsg;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RosBridgeActingAgent implements Runnable
{
	// set framework home
	protected static final String FRAMEWORK_HOME = System.getenv("ROXANNE_HOME") != null ?
			System.getenv("ROXANNE_HOME") + "/" : "";
	
	private String aPropFile;
	
	/**
	 * 
	 * @param configFile
	 */
	public RosBridgeActingAgent(String configFile) {
		// set configuration file 
		this.aPropFile = configFile;
	}
	
	/**
	 * 
	 */
	public RosBridgeActingAgent() {
		// set default agent property file
		this.aPropFile = FRAMEWORK_HOME + FilePropertyReader.DEFAULT_AGENT_PROPERTY;
	}
	
	/**
	 * 
	 * @param pf
	 */
	public void setAgentPropertyFile(String pf) {
		this.aPropFile = pf;
	}
	
	/**
	 * 
	 */
	@Override
	public void run() 
	{
		// acting agent
		GoalOrientedActingAgent agent = null;
		// rosbridge client
		RosBridge bridge = null;
		try
		{
			// create goal-oriented agent 
			agent = new GoalOrientedActingAgent(this.aPropFile); 
			// start agent
			System.out.println("Starting agent...");
			agent.start();
			// setup the agent
			System.out.println("Initializing agent... ");
			agent.initialize();
			
			
			// connect to rosbridge server and start waiting for task requests
			System.out.println("... setup goal-level connection to RosBridge server...");
			// get platform configuration file 
			FilePropertyReader preader = new FilePropertyReader(this.aPropFile);
			String platformFile = preader.getProperty("platform_config_file");
			// setup rosbridge client
			bridge = this.setupRosBridgeClient(platformFile, agent);
			
			
			// run until explicitly interrupted
			boolean run = true;
			while (run)
			{
				try
				{
					// wait some result from the agent
					List<Goal> goals = agent.getResults();
					for (Goal goal : goals) 
					{
						// print results about handled task requests
						System.out.println("Completed goal " + goal +":\n"
								+ "\t- Planning: " + goal.getPlanningAttempts() + " sessions, total time: " + (goal.getTotalPlanningTime() / 1000) +" seconds\n"
								+ "\t- Execution: " + goal.getExecutionAttempts() + " sessions, total time: " + (goal.getTotalExecutionTime() / 1000) + " seconds\n"
								+ "\t- Contingency handling: " + goal.getContingencyHandlingAttempts() + " sessions, total time: " + (goal.getTotalContingencyHandlingTime() / 1000 ) + " seconds\n\n");
					}
				}
				catch (InterruptedException ex) {
					// stop acting agent
					System.out.println("Acting process interrupted:\n\t- message: " + ex.getMessage() + "\n");
					// stop running 
					run = false;
				}
			}
			
		}
		catch (Exception ex) {
			// some error occurs
			System.err.println("Acting process initialization error:\n"
					+ "\t- message: " + ex.getMessage() + "\n");
		}
		finally 
		{
			// check rosbridge connection
			if (bridge != null) 
			{
				try {
					// stop acting agent
					System.out.println(".. closing connection to the RosBridge server");
					// close ROS bridge connection
					bridge.awaitClose(60, TimeUnit.SECONDS);
				}
				catch (InterruptedException ex) {
					// ignore
				}
			}
			
			// check agent
			if (agent != null) 
			{
				try {
					// stop agent
					System.out.println("Terminating agent...");
					agent.stop();
					System.out.println(".... finish!");
				}
				catch (Exception ex) {
					// ignore
				}
			}				
			
		}
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			// check optional agent property file  
			if (args.length > 0) 
			{
				// create thread
				Thread proc = new Thread(new RosBridgeActingAgent(args[0]));
				// start proc
				proc.start();
				// wait process
				proc.join();	
			}
			else 
			{
				// create thread 
				Thread proc = new Thread(new RosBridgeActingAgent());
				// start proc
				proc.start();
				// wait process
				proc.join();
			}
		}
		catch (Exception ex) {
			System.out.println("Process interrupted... \n" + ex.getMessage() + "\n");
		}
	}
	
	/**
	 * Create a RosBridge instance connecting the acting agent to ROS machine (i.e., the 
	 * RosBridge server) specified into the configuraiton file. 
	 * 
	 * The method also reads the configuration of the goal-topic in order to receive
	 * task planning requests from "external" ROS nodes.
	 * 
	 * @param cfgFile
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private RosBridge setupRosBridgeClient(String cfgFile, GoalOrientedActingAgent agent) 
			throws Exception  
	{
		// parse platform configuration from file 
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		// parse XML document
		Document document = builder.parse(new FileInputStream(cfgFile));
		// prepare XPath expression
		XPathFactory xpf = XPathFactory.newInstance();
		XPath xp = xpf.newXPath();
		
		// get information about ROS platform configuration 
		XPathExpression expression = xp.compile("//ros");
		NodeList elements = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
		
		// get ROS element
		Node ros = elements.item(0);
		// get attribute
		Attr rosHost = (Attr) ros.getAttributes().getNamedItem("host");
		
		// establish a new connection
		RosBridge bridge = new RosBridge();
		// connect to ROS host
		bridge.connect(
				rosHost.getValue().trim(),
				true);

		// get goal topics
		expression = xp.compile("//goal-topic");
		elements = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
		for (int i = 0; i < elements.getLength(); i++) 
		{
			// get node element
			Node topic = elements.item(i);
			// get topic name
			Attr topicName = (Attr) topic.getAttributes().getNamedItem("name");
			// get message type
			Attr msgType = (Attr) topic.getAttributes().getNamedItem("msg");
			// get delegate class
			Attr delegateClass = (Attr) topic.getAttributes().getNamedItem("delegate");
			System.out.println("... subscribing to goal-topic " + topicName.getValue().trim().toLowerCase() + " ...");
			
			// create observation listener
			Class<? extends RosBridgeGoalListener> clazz = (Class<? extends RosBridgeGoalListener>) Class.forName(delegateClass.getValue().trim());
			Constructor<? extends RosBridgeGoalListener> c = clazz.getDeclaredConstructor(GoalOrientedActingAgent.class);
			c.setAccessible(true);
			
			// subscribe to goal-topic
			bridge.subscribe(
					SubscriptionRequestMsg.generate(topicName.getValue().trim())
					.setType(msgType.getValue().trim())
					.setThrottleRate(1)
					.setQueueLength(1),
					c.newInstance(agent));
		}
		
		// get instantiated rosbridge 
		return bridge;
	}
}

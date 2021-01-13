package com.github.ros.roxanne_sa.platform.rosbridge;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

import it.cnr.istc.pst.platinum.ai.executive.pdb.ExecutionNode;
import it.cnr.istc.pst.platinum.control.lang.PlatformCommand;
import it.cnr.istc.pst.platinum.control.lang.ex.PlatformException;
import it.cnr.istc.pst.platinum.control.platform.PlatformProxy;
import ros.Publisher;
import ros.RosBridge;
import ros.SubscriptionRequestMsg;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class RosBridgePlatformProxy extends PlatformProxy 
{
	private RosBridge bridge;											// bridge to ROS

	private Map<String, String> command2dispatchTopic;					// map platform commands to ROS dispatch topics
	private Map<String, RosBridgeTopicPublisher<?>> topic2publisher;	// map ROS topic to publisher
	private Set<String> subscribedTopics;								// set of subscribed ROS topics
	
	
	/**
	 * 
	 */
	protected RosBridgePlatformProxy() 
	{
		super();
		// set bridge
		this.bridge = null;
		// set data structures
		this.command2dispatchTopic = new HashMap<>();
		this.subscribedTopics = new HashSet<>();
		this.topic2publisher = new HashMap<>();
		
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(String cfgFile) 
			throws PlatformException 
	{
		// check bridge
		if (this.bridge == null)
		{
			// set observation counter
			obsIdCounter.set(0);
			cmdIdCounter.set(0);
			
			// clear data structures
			this.command2dispatchTopic.clear();
			this.subscribedTopics.clear();
			this.topic2publisher.clear();
			this.dispatchedIndex.clear();
			
			try
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
				this.bridge = new RosBridge();
				// connect to ROS host
				this.bridge.connect(
						rosHost.getValue().trim(),
						true);

				
				
				// get environment topics
				expression = xp.compile("//environment-topic");
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
					
					// subscribe to topic if necessary
					if (!this.subscribedTopics.contains(topicName.getValue().trim().toLowerCase())) 
					{
						// index topic
						this.subscribedTopics.add(topicName.getValue().trim().toLowerCase());
						System.out.println("... subscribing to topic " + topicName.getValue().trim().toLowerCase() + " ...");
						
						// create observation listener
						Class<? extends RosBridgeTopicListener<?>> clazz = (Class<? extends RosBridgeTopicListener<?>>) Class.forName(delegateClass.getValue().trim());
						Constructor<? extends RosBridgeTopicListener<?>> c = clazz.getDeclaredConstructor(RosBridgePlatformProxy.class);
						c.setAccessible(true);
						
						// subscribe to topic
						this.bridge.subscribe(
								SubscriptionRequestMsg.generate(topicName.getValue().trim())
								.setType(msgType.getValue().trim())
								.setThrottleRate(1)
								.setQueueLength(1),
								c.newInstance(this));
					}
				}
				
				
				// get platform commands
				expression = xp.compile("//command");
				elements = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
				for (int i = 0; i < elements.getLength(); i++)
				{
					// get command element
					Node cmd = elements.item(i);
					
					// index command name
					Attr cmdName = (Attr) cmd.getAttributes().getNamedItem("name");
					Attr compName = (Attr) cmd.getAttributes().getNamedItem("component");
					System.out.println("... parsing cmd: " + compName.getValue() + "." + cmdName.getValue() + " <<");
					
					// get command dispatch topic info 
					expression = xp.compile("//command[@name= '" + cmdName.getValue() +  "' and @component= '" + compName.getValue() + "']/dispatch-topic");
					NodeList list = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
					// get dispatch topic 
					Node dispatchTopic = list.item(0);
					// get dispatch topic name
					Attr dispatchTopicName = (Attr) dispatchTopic.getAttributes().getNamedItem("name");
					// get message type
					Attr dispatchMessageType = (Attr) dispatchTopic.getAttributes().getNamedItem("msg");
					// get publisher class
					Attr publisherClass = (Attr) dispatchTopic.getAttributes().getNamedItem("publisher");
					
					// index dispatching topic by platform command 
					this.command2dispatchTopic.put(
							compName.getValue().trim().toLowerCase() + "." + cmdName.getValue().trim().toLowerCase(), 
							dispatchTopicName.getValue().trim().toLowerCase());
					
					// create publisher if necessary
					if (!this.topic2publisher.containsKey(dispatchTopicName.getValue().trim().toLowerCase())) 
					{
						// create publisher instance
						Class<? extends RosBridgeTopicPublisher<?>> clazz = (Class<? extends RosBridgeTopicPublisher<?>>) Class.forName(publisherClass.getValue().trim());
						Constructor<? extends RosBridgeTopicPublisher<?>> c = clazz.getDeclaredConstructor(RosBridgePlatformProxy.class, Publisher.class);
						c.setAccessible(true);
						
						// create ROS publisher
						RosBridgeTopicPublisher<?> publisher = c.newInstance(
								this, 
								new Publisher(dispatchTopicName.getValue().trim(),
										dispatchMessageType.getValue().trim(),
										this.bridge)); 
						
						// index publisher by topic 
						this.topic2publisher.put(dispatchTopicName.getValue().trim().toLowerCase(), publisher);
						System.out.println("... creating publisher to topic " + dispatchTopicName.getValue().trim().toLowerCase() + " ...");
					}
					
					
					// get command feedback topic info 
					expression = xp.compile("//command[@name= '" + cmdName.getValue() +  "' and @component= '" + compName.getValue() + "']/feedback-topic");
					list = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
					// get feedback topic
					Node feedbackTopic = list.item(0);
					// get feedback topic name
					Attr feedbackTopicName = (Attr) feedbackTopic.getAttributes().getNamedItem("name");
					// get message type
					Attr feedbackMessageType = (Attr) feedbackTopic.getAttributes().getNamedItem("msg");
					// get delegate class
					Attr delegateClass = (Attr) feedbackTopic.getAttributes().getNamedItem("delegate");
					
					
					// subscribe to topic if necessary
					if (!this.subscribedTopics.contains(feedbackTopicName.getValue().trim().toLowerCase()))
					{
						// index topic
						this.subscribedTopics.add(feedbackTopicName.getValue().trim().toLowerCase());
						System.out.println("... subscribing to topic " + feedbackTopicName.getValue().trim().toLowerCase() + " ...");
						
						
						// create feedback listener
						Class<? extends RosBridgeTopicListener<?>> clazz = (Class<? extends RosBridgeTopicListener<?>>) Class.forName(delegateClass.getValue().trim());
						Constructor<? extends RosBridgeTopicListener<?>> c = clazz.getDeclaredConstructor(RosBridgePlatformProxy.class);
						c.setAccessible(true);
						
						// subscribe to topic
						this.bridge.subscribe(
								SubscriptionRequestMsg.generate(feedbackTopicName.getValue().trim())
								.setType(feedbackMessageType.getValue().trim())
								.setThrottleRate(1)
								.setQueueLength(1),
								c.newInstance(this));
					}
				}
				
			}
			catch (Exception ex) {
				throw new PlatformException(ex.getMessage());
			}
		}
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isPlatformCommand(ExecutionNode node) 
	{
		// check if bridge set
		if (this.bridge == null) {
			throw new RuntimeException("ROS Proxy not initialized");
		}
		
		// check token name
		String cmdName = PlatformProxy.extractCommandName(node);
		// check component name
		String compName = node.getComponent();
		
		// check if there is a specific dispatching topic for this node 
		boolean toDispatch = this.command2dispatchTopic.containsKey(
				compName.trim().toLowerCase() + "." + cmdName.trim().toLowerCase());
		
		// check if there is a default dispatching command for the component
		if (!toDispatch) {
			// check component default dispatching command
			toDispatch = this.command2dispatchTopic.containsKey(compName.trim().toLowerCase() + ".*");
		}
		
		// check if there is a default dispatching command 
		if (!toDispatch) {
			// check default 
			toDispatch = this.command2dispatchTopic.containsKey("*.*");
		}
		
		// get flag
		return toDispatch;
	}

	
	/**
	 * 
	 */
	@Override
	public PlatformCommand executeNode(ExecutionNode node) 
			throws PlatformException 
	{
		// check if bridge set
		if (this.bridge == null) {
			throw new RuntimeException("ROS Proxy not initialized");
		}
		
		// extract command name
		String cmdName = PlatformProxy.extractCommandName(node);
		String compName = node.getComponent();

		// create platform command
		PlatformCommand cmd = new PlatformCommand(
				cmdIdCounter.getAndIncrement(), node, 1);
		
		// get dispatching topic
		String topic = this.command2dispatchTopic.get(
				compName.trim().toLowerCase() + "." + cmdName.trim().toLowerCase());
		
		// check default dispatching topic of the component
		if (topic == null) {
			// get default dispatching topic of the component (if any)
			topic = this.command2dispatchTopic.get(
					compName.trim().toLowerCase() + ".*");
		}
		
		// check default dispatching topic
		if (topic == null) {
			// get default dispatching topic
			topic = this.command2dispatchTopic.get("*.*");
		}
		
		// get publisher
		RosBridgeTopicPublisher<?> publisher = this.topic2publisher.get(topic);
		// publish execution request
		publisher.publish(cmd);
		
		// add command to dispatched index
		this.dispatchedIndex.put(cmd.getId(), cmd);
		// get command 
		return cmd;
	}

	/**
	 * 
	 */
	@Override
	public PlatformCommand startNode(ExecutionNode node) 
			throws PlatformException 
	{
		// check if bridge set
		if (this.bridge == null) {
			throw new RuntimeException("ROS Proxy not initialized");
		}
		
		
		// extract command information
		String cmdName = PlatformProxy.extractCommandName(node);
		String compName = node.getComponent();
		// create platform command 
		PlatformCommand cmd = new PlatformCommand(
				cmdIdCounter.getAndIncrement(), node, 1);
		
		// get dispatcher topic 
		String topic = this.command2dispatchTopic.get(
				compName.trim().toLowerCase() + "." + cmdName.trim().toLowerCase());
		
		// check default dispatching topic of the component
		if (topic == null) {
			// get default dispatching topic of the component (if any)
			topic = this.command2dispatchTopic.get(
					compName.trim().toLowerCase() + ".*");
		}
		
		// check default dispatching topic
		if (topic == null) {
			// get default dispatching topic
			topic = this.command2dispatchTopic.get("*.*");
		}
		
		// get publisher
		RosBridgeTopicPublisher<?> publisher = this.topic2publisher.get(topic);
		// publish start command
		publisher.publish(cmd);
		
		// add command to dispatched index
		this.dispatchedIndex.put(cmd.getId(), cmd);
		// get create command
		return cmd;
	}

	/**
	 * 
	 */
	@Override
	public void stopNode(ExecutionNode node) 
			throws PlatformException 
	{
		// check if bridge set
		if (this.bridge == null) {
			throw new RuntimeException("ROS Proxy not initialized");
		}
		
		// extract command information
		String cmdName = PlatformProxy.extractCommandName(node);
		String compName = node.getComponent();
		// create platform command 
		PlatformCommand cmd = new PlatformCommand(
				cmdIdCounter.getAndIncrement(), node, 0);
		
		// get dispatcher topic 
		String topic = this.command2dispatchTopic.get(
				compName.trim().toLowerCase() + "." + cmdName.trim().toLowerCase());
		
		// check default dispatching topic of the component
		if (topic == null) {
			// get default dispatching topic of the component (if any)
			topic = this.command2dispatchTopic.get(
					compName.trim().toLowerCase() + ".*");
		}
		
		// check default dispatching topic
		if (topic == null) {
			// get default dispatching topic
			topic = this.command2dispatchTopic.get("*.*");
		}
		
		
		// get publisher
		RosBridgeTopicPublisher<?> publisher = this.topic2publisher.get(topic);
		// publish stop command
		publisher.publish(cmd);
		// add command to dispatched index
		this.dispatchedIndex.put(cmd.getId(), cmd);
	}
	
	
}

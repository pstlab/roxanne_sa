package com.github.ros.roxanne_sa.platform.rosbridge.lms;

import com.github.ros.roxanne_sa.control.lang.PlatformCommand;
import com.github.ros.roxanne_sa.platform.msgs.TokenExecution;
import com.github.ros.roxanne_sa.platform.rosbridge.RosBridgePlatformProxy;
import com.github.ros.roxanne_sa.platform.rosbridge.RosBridgeTopicPublisher;

import ros.Publisher;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class TaskRequestPublisher extends RosBridgeTopicPublisher<TokenExecution> 
{
	private StaticTaskDescriptionKnowledge knowledge;		// static task knowledge
	
	/**
	 * 
	 * @param proxy
	 * @param publisher
	 */
	protected TaskRequestPublisher(RosBridgePlatformProxy proxy, Publisher publisher) {
		super(proxy, publisher);
		// set static knowledge
		this.knowledge = new StaticTaskDescriptionKnowledge();
		
		// set task H01
		this.knowledge.setTask("h_01");
		this.knowledge.setTaskId("h_01", "H_01");
		this.knowledge.setTaskTitle("h_01", "Task H01");
		this.knowledge.setTaskName("h_01", "Connecting harness front");
		this.knowledge.setTaskDesc("h_01", "Human moves to vehicle's front door assembly space and connects the harness");
		
		
		// set task H02
		this.knowledge.setTask("h_02");
		this.knowledge.setTaskId("h_02", "H_02");
		this.knowledge.setTaskTitle("h_02", "Task H02");
		this.knowledge.setTaskName("h_02", "Manual guidance front");
		this.knowledge.setTaskDesc("h_02", "Operator guides the robot in order to fine-tune the front's door position");
		
		
		// set task H03
		this.knowledge.setTask("h_03");
		this.knowledge.setTaskId("h_03", "H_03");
		this.knowledge.setTaskTitle("h_03", "Task H03");
		this.knowledge.setTaskName("h_03", "Pre-setting nuts");
		this.knowledge.setTaskDesc("h_03", "Operator takes nuts and pre-sets them by hand at front door");
		
		// set task H04
		this.knowledge.setTask("h_04");
		this.knowledge.setTaskId("h_04", "H_04");
		this.knowledge.setTaskTitle("h_04", "Task H04");
		this.knowledge.setTaskName("h_04", "Tightening bolt");
		this.knowledge.setTaskDesc("h_04", "Operator takes electric wrench and tights bolt at front door with it");
		
		// set task H05
		this.knowledge.setTask("h_05");
		this.knowledge.setTaskId("h_05", "H_05");
		this.knowledge.setTaskName("h_05", "Tightening nuts");
		this.knowledge.setTaskDesc("h_05", "Operator tights nuts at front door sing HCT machine");
		
		// set task H06
		this.knowledge.setTask("h_06");
		this.knowledge.setTaskId("h_06", "H_06");
		this.knowledge.setTaskTitle("h_06", "Task H06");
		this.knowledge.setTaskName("h_06", "Moving to bed area");
		this.knowledge.setTaskDesc("h_06", "Human moves to vehicle's bed area to perform operations");
		
		
		// set task H07
		this.knowledge.setTask("h_07");
		this.knowledge.setTaskId("h_07", "H_07");
		this.knowledge.setTaskTitle("h_07", "Task H07");
		this.knowledge.setTaskName("h_07", "Connecting harness rear");
		this.knowledge.setTaskDesc("h_07", "Human moves to vehicle's back door assembly space and connects the harness");
		
		// set task H08
		this.knowledge.setTask("h_08");
		this.knowledge.setTaskId("h_08", "H_08");
		this.knowledge.setTaskTitle("h_08", "Task H08");
		this.knowledge.setTaskName("h_08", "Manual guidance rear");
		this.knowledge.setTaskDesc("h_08", "Operator guides the robot in order to fine-tune the rear's door position");
		
		
		// set task H09
		this.knowledge.setTask("h_09");
		this.knowledge.setTaskId("h_09", "H_09");
		this.knowledge.setTaskTitle("h_09", "Task H09");
		this.knowledge.setTaskName("h_09", "Pre-setting nuts");
		this.knowledge.setTaskDesc("h_09", "Operator takes nuts and pre-sets them by hand at back door");
		
		// set task H10
		this.knowledge.setTask("h_10");
		this.knowledge.setTaskId("h_10", "H_10");
		this.knowledge.setTaskTitle("h_10", "Task H10");
		this.knowledge.setTaskName("h_10", "Tightening bolt");
		this.knowledge.setTaskDesc("h_10", "Operator tights bolt at back door using the electric wrench");
		
		// set task H11
		this.knowledge.setTask("h_11");
		this.knowledge.setTaskId("h_11", "H_11");
		this.knowledge.setTaskTitle("h_11", "Task H11");
		this.knowledge.setTaskName("h_11", "Tightening nuts");
		this.knowledge.setTaskDesc("h_11", "Operator tights nuts at back door using HCT machine");
		
		
		// set task H12
		this.knowledge.setTask("h_12");
		this.knowledge.setTaskId("h_12", "H_12");
		this.knowledge.setTaskTitle("h_12", "Task H12");
		this.knowledge.setTaskName("h_12", "Moving to bed area");
		this.knowledge.setTaskDesc("h_12", "Human moves to vehicle's bed area to finalize the previously mentioned operations");
		
		
		// set task R01
		this.knowledge.setTask("r_01");
		this.knowledge.setTaskId("r_01", "R_01");
		this.knowledge.setTaskTitle("r_01", "Task R01");
		this.knowledge.setTaskName("r_01", "Front door picking");
		this.knowledge.setTaskDesc("r_01", "Robot does a demo move in the general whereabouts of the front door");
		
		
		// set task R01
		this.knowledge.setTask("r_02");
		this.knowledge.setTaskId("r_02", "R_02");
		this.knowledge.setTaskTitle("r_02", "Task R02");
		this.knowledge.setTaskName("r_02", "Read door picking");
		this.knowledge.setTaskDesc("r_02", "Robot does a demo move in the general whereabouts of the rear door");
	}
	
	/**
	 * 
	 */
	@Override
	protected TokenExecution doCreateMessage(PlatformCommand cmd) 
	{
		// set task request parameters
		String[] params = new String[7];

		// set task id 
		params[0] = this.knowledge.getTaskId(cmd.getName().toLowerCase());
		// set task name
		params[1] = this.knowledge.getTaskName(cmd.getName().toLowerCase());
		// set task title
		params[2] = this.knowledge.getTaskTitle(cmd.getName().toLowerCase());
		// set task description
		params[3] = this.knowledge.getTaskDesc(cmd.getName().toLowerCase());
		// set task mode
		params[4] = this.knowledge.getTaskMode(cmd.getName().toLowerCase());
		// set task id 
		params[5] = this.knowledge.getTaskId(cmd.getName().toLowerCase());
		// set action id 
		params[6] = "Action ID " + cmd.getId();
		
		// create task request
		TokenExecution request = new TokenExecution(
				cmd.getId(),
				cmd.getName(),
				params);
		
		// get request
		return request;
	}
}

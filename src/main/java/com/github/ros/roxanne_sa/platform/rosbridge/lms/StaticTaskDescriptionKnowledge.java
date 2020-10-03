package com.github.ros.roxanne_sa.platform.rosbridge.lms;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class StaticTaskDescriptionKnowledge 
{
	private Map<String, String> token2taskId;			// #1 e.g. R_01 
	private Map<String, String> token2Name;				// #2 e.g. "Transferring front door" 
	private Map<String, String> token2Title;			// #3 e.g. "No title"
	private Map<String, String> token2taskDesc;			// #4 e.g. "Robot is transferring front door near ... "
	private Map<String, String> token2mode;				// #5 e.g. "OperationalExceptionMode"
	private Map<String, String> token2actionId;			// #7 e.g. "Action ID 1"
	
	/**
	 * 
	 */
	protected StaticTaskDescriptionKnowledge() {
		this.token2taskId= new HashMap<>();
		this.token2Name = new HashMap<>();
		this.token2Title = new HashMap<>();
		this.token2taskDesc = new HashMap<>();
		this.token2mode = new HashMap<>();
		this.token2actionId = new HashMap<>();
	}
	
	/**
	 * 
	 * @param token
	 */
	public void setTask(String token) {
		this.token2taskId.put(token, "X");
		this.token2Name.put(token, "Task X");
		this.token2Title.put(token, "No Title");
		this.token2taskDesc.put(token, "No Description");
		this.token2mode.put(token, "OperationalExceptionMode");
		this.token2actionId.put(token, "Action ID Y");
	}
	
	/**
	 * 
	 * @param token
	 * @param taskId
	 */
	public void setTaskId(String token, String taskId) {
		this.token2taskId.put(token, taskId);
	}
	
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public String getTaskId(String token) {
		return this.token2taskId.get(token);
	}
	
	
	/**
	 * 
	 * @param token
	 * @param name
	 */
	public void setTaskName(String token, String name) {
		this.token2Name.put(token, name);
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public String getTaskName(String token) {
		return this.token2Name.get(token);
	}
	
	/**
	 * 
	 * @param token
	 * @param title
	 */
	public void setTaskTitle(String token, String title) {
		this.token2Title.put(token, title);
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public String getTaskTitle(String token) {
		return this.token2Title.get(token);
	}
	
	/**
	 * 
	 * @param token
	 * @param desc
	 */
	public void setTaskDesc(String token, String desc) {
		this.token2taskDesc.put(token, desc);
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public String getTaskDesc(String token) {
		return this.token2taskDesc.get(token);
	}
	
	/**
	 * 
	 * @param token
	 * @param mode
	 */
	public void setTokenMode(String token, String mode) {
		this.token2mode.put(token, mode);
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public String getTaskMode(String token) {
		return this.token2mode.get(token);
	}
	
	
	/**
	 * 
	 * @param token
	 * @param actionId
	 */
	public void setTaskActionId(String token, String actionId) {
		this.token2actionId.put(token, actionId);
	}
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public String getTokenActionId(String token) {
		return this.token2actionId.get(token);
	}
}

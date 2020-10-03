package com.github.ros.roxanne_sa.control.lang;

import com.github.ros.roxanne_sa.ai.executive.pdb.ExecutionNode;
import com.github.ros.roxanne_sa.platform.PlatformProxy;

/**
 * 
 * @author anacleto
 *
 */
public class PlatformCommand implements Comparable<PlatformCommand> 
{
	private ExecutionNode node;							// associated node
	private String id;									// platform command ID
	private long time;									// command issue time
	private PlatformCommandDescription description;		// command description
	private String name;								// command name 
	private String[] paramValues;						// command parameter values
	private Object data;								// additional general data
	
	/**
	 * 
	 * @param id
	 * @param node
	 */
	public PlatformCommand(String id, ExecutionNode node) {
		this.id = id; 
		this.node = node;
		// get command name from node
		this.name = PlatformProxy.extractCommandName(this.node);
		// get command parameters
		this.paramValues = PlatformProxy.extractCommandParameters(node);
		this.time = System.currentTimeMillis();
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param params
	 */
	public PlatformCommand(String id, String name, String[] params) {
		this.node = null;
		this.id = id;
		this.name = name;
		this.paramValues = params;
	}
	
	/**
	 * 
	 * @param id
	 * @param desc
	 */
	public PlatformCommand(String id, PlatformCommandDescription desc, String[] params) {
		this(id, desc.getName(), params);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 
	 * @return
	 */
	public ExecutionNode getNode() {
		return node;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 
	 * @return
	 */
	public String[] getParamValues() {
		return this.paramValues;
	}
	
	/**
	 * 
	 * @return
	 */
	public float getExecutionTime() {
		return this.description.getExecutionTime();	
	}

	/**
	 * 
	 * @param data
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	/**
	 * 
	 * @return
	 */
	public Object getData() {
		return data;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlatformCommand other = (PlatformCommand) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	/**
	 * 
	 */
	@Override
	public int compareTo(PlatformCommand o) {
		return this.time < o.time ? -1 : this.time > o.time ? 1 : 0;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[PlatformCommand id: " + this.id + ", name: " + this.name + "]";
	}
}

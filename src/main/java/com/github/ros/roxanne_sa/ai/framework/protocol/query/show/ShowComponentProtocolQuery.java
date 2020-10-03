package com.github.ros.roxanne_sa.ai.framework.protocol.query.show;

import java.util.ArrayList;
import java.util.List;

import com.github.ros.roxanne_sa.ai.framework.protocol.query.ProtocolQuery;
import com.github.ros.roxanne_sa.ai.framework.protocol.query.ProtocolQueryType;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class ShowComponentProtocolQuery extends ProtocolQuery 
{
	private List<String> components;
	
	/**
	 * 
	 */
	public ShowComponentProtocolQuery() {
		super(ProtocolQueryType.SHOW_COMPONENTS);
		this.components = new ArrayList<String>();
	}
	
	public List<String> getComponents() {
		return components;
	}
	
	public void addComponent(String comp) {
		this.components.add(comp);
	}
}

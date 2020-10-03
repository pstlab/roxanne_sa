package com.github.ros.roxanne_sa.ai.executive;

/**
 * 
 * @author anacleto
 *
 */
public interface ExecutionManager 
{
	/**
	 * 
	 * @param property
	 * @return
	 */
	public String getProperty(String property); 
	
	/**
	 * 
	 * @param tick
	 * @return
	 */
	public boolean onTick(long tick);
}

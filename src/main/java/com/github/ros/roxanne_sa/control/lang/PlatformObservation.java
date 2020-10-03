package com.github.ros.roxanne_sa.control.lang;

/**
 * 
 * 
 * @author alessandroumbrico
 *
 */
public class PlatformObservation<T extends Object> implements Comparable<PlatformObservation<? extends Object>>
{
	private String id;						// observation ID
	private long time;						// observation generation time
	private T data;							// generic data object containing observed information
	
	/**
	 * 
	 * @param id
	 * @param data
	 */
	public PlatformObservation(String id, T data) {
		this.id = id;
		this.data = data;
		this.time = System.currentTimeMillis();
		
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
	public Object getData() {
		return data;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public long getTime() {
		return time;
	}
	
	/**
	 * 
	 */
	@Override
	public int compareTo(PlatformObservation<? extends Object> o) {
		return this.time < o.time ? -1 : this.time > o.time ? 1 : 0;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[PlatformObservation id: " + this.id + ", data: " + this.data + "]";
	}
}

package com.github.ros.roxanne_sa.platform.rosbridge;

import com.github.ros.roxanne_sa.control.lang.PlatformCommand;

import ros.Publisher;

/**
 * 
 * @author alessandroumbrico
 *
 */
public abstract class ROSPublisher<T extends Object> 
{
	protected ROSBridgePlatformProxy proxy;
	protected Publisher publisher;
	
	/**
	 * 
	 * @param proxy
	 * @param publisher
	 */
	protected ROSPublisher(ROSBridgePlatformProxy proxy, Publisher publisher) {
		this.proxy = proxy;
		this.publisher = publisher;
	}
	
	/**
	 * 
	 * @param cmd
	 */
	public void publish(PlatformCommand cmd) {
		// create message object to be published
		T msg = this.doCreateMessage(cmd);
		System.out.println(">>>> Publishing msg:\n\t- cmd: " + cmd+ "\n\t- topic: " + this.publisher.getTopic() + "\n\t- msg: " + msg + "\n");
		// publish message
		this.publisher.publish(msg);
		
	}

	/**
	 * 
	 * @param cmd
	 * @return
	 */
	protected abstract T doCreateMessage(PlatformCommand cmd);
	
}

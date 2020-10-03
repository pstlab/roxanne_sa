package com.github.ros.roxanne_sa.control.lang;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class PlatformFeedback implements Comparable<PlatformFeedback> 
{
	private PlatformCommand cmd;						// the command the feedback refers to
	private PlatformFeedbackType type;					// feedback type
	private long time;									// feedback issue time
	
	/**
	 * 
	 * @param cmd
	 * @param type
	 */
	public PlatformFeedback(PlatformCommand cmd, PlatformFeedbackType type) {
		this.type = type;
		this.cmd = cmd;
		this.time = System.currentTimeMillis();
	}
	
	/**
	 * 
	 * @return
	 */
	public PlatformCommand getCmd() {
		return cmd;
	}
	
	/**
	 * 
	 * @return
	 */
	public PlatformFeedbackType getType() {
		return type;
	}
	
	/**
	 * 
	 * @return
	 */
	public long getTime() {
		return time;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cmd == null) ? 0 : cmd.hashCode());
		result = prime * result + (int) (time ^ (time >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		PlatformFeedback other = (PlatformFeedback) obj;
		if (cmd == null) {
			if (other.cmd != null)
				return false;
		} else if (!cmd.equals(other.cmd))
			return false;
		if (time != other.time)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public int compareTo(PlatformFeedback o) {
		return this.time < o.time ? -1 : this.time > o.time ? 1 : 0;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "[ActionFeedback cmd: " + this.cmd + ", type: " + this.type +"]";
	}
}

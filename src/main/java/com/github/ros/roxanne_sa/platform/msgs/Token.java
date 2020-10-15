package com.github.ros.roxanne_sa.platform.msgs;

/**
 * 
 * @author alessandroumbrico
 *
 */
public class Token 
{
	private long id;
	private String component;
	private String predicate;
	private String[] parameters;
	private long[] start;
	private long[] end;
	private long[] duration;
	
	/**
	 * 
	 */
	public Token() {
		// rosbridge compatibility issues
	}
	
	/**
	 * 
	 * @param id
	 * @param component
	 * @param predicate
	 */
	public Token(long id, String component, String predicate) {
		this.id = id;
		this.component = component;
		this.predicate = predicate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getPredicate() {
		return predicate;
	}

	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}

	public String[] getParameters() {
		return parameters;
	}

	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}

	public long[] getStart() {
		return start;
	}

	public void setStart(long[] start) {
		this.start = start;
	}

	public long[] getEnd() {
		return end;
	}

	public void setEnd(long[] end) {
		this.end = end;
	}

	public long[] getDuration() {
		return duration;
	}

	public void setDuration(long[] duration) {
		this.duration = duration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Token other = (Token) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		// JSON style description
		return "{\"id\": " + this.id + ", \"component\": \"" + this.component + "\", \"predicate\":\"" + this.predicate + "\"}";
	}
}

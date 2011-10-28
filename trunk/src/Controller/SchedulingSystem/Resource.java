package Controller.SchedulingSystem;

import java.util.Hashtable;

public class Resource {
	
	private String resId;
	private Hashtable<Object, Object> properties;
	private Relations relations;
	
	public Resource(String resId) {
		this.resId=resId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}
	
	public Object getProperty(Object key) {
		return properties.get(key);
	}

	public void setProperty(Object key, Object value) {
		this.properties.put(key, value);
	}

	public Relations getRelations() {
		return relations;
	}

	public void setRelations(Relations relations) {
		this.relations = relations;
	}

}

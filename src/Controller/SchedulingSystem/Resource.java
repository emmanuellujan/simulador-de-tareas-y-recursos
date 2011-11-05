package Controller.SchedulingSystem;

import java.util.Hashtable;

public class Resource {
	
	private String resId;
	private Hashtable<String, String> properties;
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
	
	public Object getProperty(String key) {
		return properties.get(key);
	}

	public void setProperty(String key, String value) {
		this.properties.put(key, value);
	}

	public Relations getRelations() {
		return relations;
	}

	public void setRelations(Relations relations) {
		this.relations = relations;
	}

	public Hashtable<String, String> getProperties(Hashtable<String, String> properties2) {
		return this.properties;
	}
	
	public void setProperties(Hashtable<String, String> properties) {
		this.properties=properties;	
	}

}

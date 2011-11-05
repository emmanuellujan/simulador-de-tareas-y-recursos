package Controller.SchedulingSystem;

import java.util.Hashtable;
import java.util.Vector;

public class Resource {
	
	private String resId;
	private Hashtable<String, String> properties;
	private Vector<String> relationsIds;
	private Vector<Resource> resources;
	private int resourceMaxLimit;
	
	public Resource(String resId, Hashtable<String, String> properties, Vector<String> relationsIds) {
		this.setResId(resId);
		this.setProperties(properties);
		this.setRelationsIds(relationsIds);
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

	public Vector<Resource> getResources() {
		return resources;
	}

	public void setResources(Vector<Resource> resources) {
		this.resources = resources;
	}

	public Hashtable<String, String> getProperties() {
		return properties;
	}

	public Hashtable<String, String> getProperties(Hashtable<String, String> properties2) {
		return this.properties;
	}
	
	public void setProperties(Hashtable<String, String> properties) {
		this.properties=properties;	
	}

	public int getResourceMaxLimit() {
		return resourceMaxLimit;
	}

	public void setResourceMaxLimit(int resourceMaxLimit) {
		this.resourceMaxLimit = resourceMaxLimit;
	}
	
	public void setResource(Resource newResource) {
		if (this.getResourceMaxLimit() >= (this.getResources().size() + 1))
			this.getResources().addElement(newResource);
	}

	public Vector<String> getRelationsIds() {
		return relationsIds;
	}

	public void setRelationsIds(Vector<String> relationsIds) {
		this.relationsIds = relationsIds;
	}
	
}

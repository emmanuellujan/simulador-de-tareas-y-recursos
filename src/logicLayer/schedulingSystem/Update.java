package logicLayer.schedulingSystem;

import java.util.Enumeration;
import java.util.Hashtable;

public class Update {
	private Resource resource;
	private Hashtable<String, String> properties;

	public Update(Resource resource) {
		this.setResource(resource);
		Hashtable<String, String> properties = new Hashtable<String, String>();
		this.setProperties(properties);
	}
	
	public Update() {
		this.setResource(null);
		Hashtable<String, String> properties = new Hashtable<String, String>();
		this.setProperties(properties);
	}

	public void addProperty(String key, String value) {
		properties.put(key, value);
	}

	public Hashtable<String, String> getProperties() {
		return properties;
	}

	public Resource getResource() {
		return resource;
	}

	public void setProperties(Hashtable<String, String> properties) {
		this.properties = properties;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public boolean update() {
		return this.update(null);
	}
	
	public boolean update(Resource resource) {
		if(this.getResource()!=null)
			resource = this.getResource();
		
		Hashtable<String, String> propsResource = resource.getProperties();
		if (propsResource != null) {
			Hashtable<String, String> properties = this.getProperties();
			Enumeration<String> e = properties.keys();
			String key, value;

			while (e.hasMoreElements()) {
				key = e.nextElement();
				value = properties.get(key);
				propsResource.put(key, value);
				resource.setProperties(propsResource);
				resource.incNbrOfPropChanges();
			}

			return true;
		} else
			return false;
	}
	
}

package LogicLayer.SchedulingSystem;

import java.util.Enumeration;
import java.util.Hashtable;

public class Update {
	Hashtable<String, String> properties;

	public Update() {
		Hashtable<String, String> properties = new Hashtable<String, String>();
		this.setProperties(properties);
	}

	public void addProperty(String key, String value) {
		properties.put(key, value);
	}

	public Hashtable<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Hashtable<String, String> properties) {
		this.properties = properties;
	}

	public boolean update(Resource resource) {
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
			}

			return true;
		} else
			return false;
	}

}

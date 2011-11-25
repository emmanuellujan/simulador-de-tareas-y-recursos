package Controller.SchedulingSystem;

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

	public void update(Resource resource) {
		Hashtable<String, String> properties = this.getProperties();
		Enumeration<String> e = properties.keys();
		String key, value;
		while (e.hasMoreElements()) {
			key = e.nextElement();
			value = properties.get(key);
			Hashtable<String, String> properties2 = resource.getProperties();
			properties2.put(key, value);
			resource.setProperties(properties2);
			System.out.println("AAAAAAAAAAAAAAAA:"
					+ resource.getProperties().get(key));
		}
	}

}

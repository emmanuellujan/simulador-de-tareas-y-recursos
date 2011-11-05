package Controller.SchedulingSystem;

import java.util.Hashtable;

public class Artifact extends Resource{

	public Artifact(String resId, Hashtable<String, String> properties) {
		super(resId);
		super.setProperties(properties);
	}

}

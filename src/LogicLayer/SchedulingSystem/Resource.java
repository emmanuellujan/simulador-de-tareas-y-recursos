package LogicLayer.SchedulingSystem;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

public class Resource implements Serializable {

	private static final long serialVersionUID = 1L;
	private String resId;
	private String type;
	private Hashtable<String, String> properties;
	private Vector<String> relationsIds;
	private Vector<Resource> resources;
	private int resourceMaxLimit;
	private SchedulingSystem schedulingSystem;

	public Resource(String resId, String type,
			Hashtable<String, String> properties, int maxRelations,
			Vector<String> relationsIds, SchedulingSystem schedulingSystem) {
		if (properties == null)
			properties = new Hashtable<String, String>();
		if (relationsIds == null)
			relationsIds = new Vector<String>();

		this.setResId(resId);
		this.setType(type);
		this.setProperties(properties);
		this.setResourceMaxLimit(maxRelations);
		this.setRelationsIds(relationsIds);
		Vector<Resource> resources = new Vector<Resource>();
		this.setResources(resources);
		this.setSchedulingSystem(schedulingSystem);
	}

	public void addRelation(Resource resource) {
		if (this.getResources().size() < this.getResourceMaxLimit())
			this.getResources().add(resource);
		else {
			String errorMsg = "Limit exceeded. Can't assign the resource "
					+ resource.getResId() + " to the resource "
					+ this.getResId();
			this.getSchedulingSystem().getCompLogginSystem()
					.addErrorMsg(errorMsg);
		}
	}

	public Hashtable<String, String> getProperties() {
		return properties;
	}

	public Hashtable<String, String> getProperties(
			Hashtable<String, String> properties2) {
		return this.properties;
	}

	public String getProperty(String key) {
		return properties.get(key);
	}

	public Vector<String> getRelationsIds() {
		return relationsIds;
	}

	public String getResId() {
		return resId;
	}

	public int getResourceMaxLimit() {
		return resourceMaxLimit;
	}

	public Vector<Resource> getResources() {
		return resources;
	}

	public SchedulingSystem getSchedulingSystem() {
		return schedulingSystem;
	}

	public String getType() {
		return type;
	}

	public void print() {
		System.out.println("ResourceId:" + this.getResId());

		System.out.println("Properties:");
		Hashtable<String, String> properties = this.getProperties();
		if (properties != null) {
			Enumeration<String> e = properties.keys();
			String key;
			while (e.hasMoreElements()) {
				key = e.nextElement();
				System.out.println("\tKey:" + key + ", Value: "
						+ properties.get(key));
			}
		}

		System.out.println("Relations:");
		Vector<Resource> resources = this.getResources();
		int n = resources.size();
		for (int i = 0; i < n; i++)
			System.out.println("\t" + resources.elementAt(i).getResId());

		System.out.println("Relations Max Limit:" + this.getResourceMaxLimit());

	}

	public void reset() {

	}

	public void setProperties(Hashtable<String, String> properties) {
		this.properties = properties;
	}

	public void setProperty(String key, String value) {
		this.properties.put(key, value);
	}

	public void setRelationsIds(Vector<String> relationsIds) {
		this.relationsIds = relationsIds;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public void setResource(Resource newResource) {
		if (this.getResourceMaxLimit() >= (this.getResources().size() + 1))
			this.getResources().addElement(newResource);
	}

	public void setResourceMaxLimit(int resourceMaxLimit) {
		this.resourceMaxLimit = resourceMaxLimit;
	}

	public void setResources(Vector<Resource> resources) {
		this.resources = resources;
	}

	public void setSchedulingSystem(SchedulingSystem schedulingSystem) {
		this.schedulingSystem = schedulingSystem;
	}

	public void setType(String type) {
		this.type = type;
	}

}

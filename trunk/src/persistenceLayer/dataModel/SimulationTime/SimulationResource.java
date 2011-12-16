package persistenceLayer.dataModel.SimulationTime;

import java.util.Hashtable;
import java.util.Vector;

import logicLayer.schedulingSystem.Resource;

public class SimulationResource {
	private String resId;
	
	private Hashtable<String, String> properties;
	private Vector<String> relationsIds;
	private int resourceMaxLimit;
	
	
	SimulationResource(Resource resource){
		String resId = resource.getResId();
		
		Hashtable<String, String> properties = (Hashtable<String, String>) resource.getProperties().clone(); 
		Vector<String> relationsIds = (Vector<String>) resource.getRelationsIds().clone(); 
		int resourceMaxLimit = resource.getResourceMaxLimit();
				
		this.setResId(resId);
		this.setProperties(properties);
		this.setRelationsIds(relationsIds);
		this.setResourceMaxLimit(resourceMaxLimit);
	}
	
	public String getResId() {
		return resId;
	}
	public void setResId(String resId) {
		this.resId = resId;
	}

	public Hashtable<String, String> getProperties() {
		return properties;
	}

	public void setProperties(Hashtable<String, String> properties) {
		this.properties = properties;
	}

	public Vector<String> getRelationsIds() {
		return relationsIds;
	}

	public void setRelationsIds(Vector<String> relationsIds) {
		this.relationsIds = relationsIds;
	}

	public int getResourceMaxLimit() {
		return resourceMaxLimit;
	}

	public void setResourceMaxLimit(int resourceMaxLimit) {
		this.resourceMaxLimit = resourceMaxLimit;
	}

}

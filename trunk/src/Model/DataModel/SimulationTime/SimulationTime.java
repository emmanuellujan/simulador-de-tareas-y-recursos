package Model.DataModel.SimulationTime;
import java.util.Vector;

import Controller.SchedulingSystem.Resource;


public class SimulationTime {
	private int currentTime;
	private Vector<SimulationResource> resources;
	
	public SimulationTime(int time, Vector<Resource> resourcesList){
		Vector<SimulationResource> resources = new Vector<SimulationResource>(); 
		Resource resource;
		SimulationResource sResource;
		int n = resourcesList.size();
		for(int i=0;i<n;i++){
			resource = resourcesList.get(i); 
			sResource = new SimulationResource(resource);
			resources.add(sResource);
		}
		this.setCurrentTime(time);
		this.setResources(resources);
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public Vector<SimulationResource> getResources() {
		return resources;
	}

	public void setResources(Vector<SimulationResource> resources) {
		this.resources = resources;
	}
}

package persistenceLayer.dataModel.SimulationTime;

import java.util.Vector;

import logicLayer.schedulingSystem.Actor;
import logicLayer.schedulingSystem.Resource;

public class SimulationTime {
	private int currentTime;
	private Vector<SimulationActor> actors;
	private Vector<SimulationResource> resources;

	public SimulationTime(int time, Vector<Resource> allResourcesList) {
		Vector<SimulationActor> actors = new Vector<SimulationActor>();
		Vector<SimulationResource> resources = new Vector<SimulationResource>();
		Resource resource = null;
		SimulationResource sSimResource;
		int n = allResourcesList.size();
		for (int i = 0; i < n; i++) {
			resource = allResourcesList.get(i);
			if (resource.getClass().getName().contains("Actor")) {
				sSimResource = new SimulationActor((Actor) resource);
				actors.add((SimulationActor) sSimResource);
			} else {
				sSimResource = new SimulationResource(resource);
				resources.add(sSimResource);
			}
		}
		this.setCurrentTime(time);
		this.setActors(actors);
		this.setResources(resources);
	}

	public Vector<SimulationActor> getActors() {
		return actors;
	}

	public Vector<SimulationResource> getAllResources() {
		Vector<SimulationResource> allResources = new Vector<SimulationResource>();
		allResources.addAll(this.getActors());
		allResources.addAll(this.getResources());
		return allResources;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public Vector<SimulationResource> getResources() {
		return resources;
	}

	public void setActors(Vector<SimulationActor> actors) {
		this.actors = actors;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public void setResources(Vector<SimulationResource> resources) {
		this.resources = resources;
	}

}

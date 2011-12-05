package persistenceLayer.dataModel.SimulationTime;

import java.util.Vector;

import logicLayer.schedulingSystem.Actor;

public class SimulationTime {
	private int currentTime;
	private Vector<SimulationResource> actors;

	public SimulationTime(int time, Vector<Actor> actorsList) {
		Vector<SimulationResource> actors = new Vector<SimulationResource>();
		Actor actor;
		SimulationResource sResource;
		int n = actorsList.size();
		for (int i = 0; i < n; i++) {
			actor = actorsList.get(i);
			sResource = new SimulationResource(actor);
			actors.add(sResource);
		}
		this.setCurrentTime(time);
		this.setActors(actors);
	}

	public Vector<SimulationResource> getActors() {
		return actors;
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setActors(Vector<SimulationResource> actors) {
		this.actors = actors;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}
}

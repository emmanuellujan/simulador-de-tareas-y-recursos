package Model.DataModel.SimulationTime;
import java.util.Vector;

import Controller.SchedulingSystem.Actor;


public class SimulationTime {
	private int currentTime;
	private Vector<SimulationResource> actors;
	
	public SimulationTime(int time, Vector<Actor> actorsList){
		Vector<SimulationResource> actors = new Vector<SimulationResource>(); 
		Actor actor;
		SimulationResource sResource;
		int n = actorsList.size();
		for(int i=0;i<n;i++){
			actor = actorsList.get(i); 
			sResource = new SimulationResource(actor);
			actors.add(sResource);
		}
		this.setCurrentTime(time);
		this.setActors(actors);
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public Vector<SimulationResource> getActors() {
		return actors;
	}

	public void setActors(Vector<SimulationResource> actors) {
		this.actors = actors;
	}
}

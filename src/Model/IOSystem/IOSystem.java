package Model.IOSystem;

import java.util.Vector;

import Model.DataModel.Configurator.Configurator;
import Controller.SchedulingSystem.Resource;
import Controller.SchedulingSystem.SchedulingSystem;
import Controller.SchedulingSystem.Actor;
import Controller.SchedulingSystem.Task;


public abstract class IOSystem {

	private Configurator configurator;
	private SchedulingSystem schedulingSystem;
	
	private int deadline;
	private Vector<Actor> actorsList;
	private Vector<Resource> resourcesList;
	private Vector<Resource> allResourcesList;
	private Vector<Task> tasksList;

	public IOSystem(Configurator configurator,
			SchedulingSystem schedulingSystem) {
		
		int deadline = 0;
		Vector<Actor> actorsList = new Vector<Actor>(); 
		Vector<Resource> resourcesList = new Vector<Resource>();
		Vector<Resource> allResourcesList = new Vector<Resource>();
		Vector<Task> tasksList = new Vector<Task>();
	
		this.setConfigurator(configurator);
		this.setSchedulingSystem(schedulingSystem);
		this.setDeadline(deadline);
		this.setActorsList(actorsList);
		this.setResourcesList(resourcesList);
		this.setAllResourcesList(allResourcesList);
		this.setTasksList(tasksList);
		
	}
	
	public abstract void saveAll();
	public abstract void loadAll();
	public abstract void updateRelations();
	
	public Configurator getConfigurator() {
		return configurator;
	}

	public void setConfigurator(Configurator configurator) {
		this.configurator = configurator;
	}

	public SchedulingSystem getSchedulingSystem() {
		return schedulingSystem;
	}

	public void setSchedulingSystem(SchedulingSystem schedulingSystem) {
		this.schedulingSystem = schedulingSystem;
	}

	public Vector<Actor> getActorsList() {
		return this.actorsList;
	}

	public void setActorsList(Vector<Actor> actors) {
		this.actorsList = actors;
	}

	public Vector<Resource> getResourcesList() {
		return this.resourcesList;
	}

	public void setResourcesList(Vector<Resource> resourcesList) {
		this.resourcesList = resourcesList;
	}

	public Vector<Resource> getAllResourcesList() {
		return this.allResourcesList;
	}

	public void setAllResourcesList(Vector<Resource> allResourcesList) {
		this.allResourcesList = allResourcesList;
	}

	public Vector<Task> getTasksList() {
		return tasksList;
	}

	public void setTasksList(Vector<Task> tasksList) {
		this.tasksList = tasksList;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}
	
	public int getDeadline() {
		return this.deadline;
	}	
}

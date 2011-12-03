package Model.IOSystem;

import java.util.Vector;

import Controller.SchedulingSystem.Actor;
import Controller.SchedulingSystem.Resource;
import Controller.SchedulingSystem.SchedulingSystem;
import Controller.SchedulingSystem.Task;
import Model.DataModel.Configurator.Configurator;

public abstract class IOSystem {

	private Configurator configurator;
	private SchedulingSystem schedulingSystem;

	private int deadline;
	private Vector<Actor> actorsList;
	private Vector<Resource> resourcesList;
	private Vector<Resource> allResourcesList;
	private Vector<Task> tasksList;

	public IOSystem(Configurator configurator, SchedulingSystem schedulingSystem) {

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

	public Vector<Actor> getActorsList() {
		return this.actorsList;
	}

	public Vector<Resource> getAllResourcesList() {
		return this.allResourcesList;
	}

	public Configurator getConfigurator() {
		return configurator;
	}

	public int getDeadline() {
		return this.deadline;
	}

	public Vector<Resource> getResourcesList() {
		return this.resourcesList;
	}

	public SchedulingSystem getSchedulingSystem() {
		return schedulingSystem;
	}

	public Vector<Task> getTasksList() {
		return tasksList;
	}

	public abstract void loadAll();

	public abstract void saveAll();

	public void setActorsList(Vector<Actor> actors) {
		this.actorsList = actors;
	}

	public void setAllResourcesList(Vector<Resource> allResourcesList) {
		this.allResourcesList = allResourcesList;
	}

	public void setConfigurator(Configurator configurator) {
		this.configurator = configurator;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public void setResourcesList(Vector<Resource> resourcesList) {
		this.resourcesList = resourcesList;
	}

	public void setSchedulingSystem(SchedulingSystem schedulingSystem) {
		this.schedulingSystem = schedulingSystem;
	}

	public void setTasksList(Vector<Task> tasksList) {
		this.tasksList = tasksList;
	}

	public abstract void updateRelations();
}

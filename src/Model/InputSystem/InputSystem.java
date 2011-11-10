package Model.InputSystem;

import java.util.Vector;

import Model.DataModel.Configurator.Configurator;
import Controller.SchedulingSystem.Resource;
import Controller.SchedulingSystem.SchedulingSystem;
import Controller.SchedulingSystem.Actor;
import Controller.SchedulingSystem.Task;


public abstract class InputSystem {

	private Configurator configurator;
	private SchedulingSystem schedulingSystem;
	
	private Vector<Actor> actorsList;
	private Vector<Resource> resourcesList;

	public InputSystem(Configurator configurator,
			SchedulingSystem schedulingSystem) {
		this.setConfigurator(configurator);
		this.setSchedulingSystem(schedulingSystem);
	}

	public abstract Vector<Task> loadNewsList();

	public abstract Vector<Actor> loadActorsList();
	
	public abstract Vector<Resource> loadResourcesList();
	
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
		return actorsList;
	}

	public void setActorsList(Vector<Actor> actors) {
		this.actorsList = actors;
	}

	public Vector<Resource> getResourcesList() {
		return resourcesList;
	}

	public void setResourcesList(Vector<Resource> resourcesList) {
		this.resourcesList = resourcesList;
	}

}

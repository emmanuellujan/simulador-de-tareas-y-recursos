package Model.InputSystem;
import java.util.Vector;

import Model.DataModel.Configurator.Configurator;
import Controller.SchedulingSystem.SchedulingSystem;
import Controller.SchedulingSystem.Resource;
import Controller.SchedulingSystem.Task;


public abstract class InputSystem {
	
	private Configurator configurator;
	private SchedulingSystem schedulingSystem;

	public InputSystem(Configurator configurator, SchedulingSystem schedulingSystem) {
		this.setConfigurator(configurator);
		this.setSchedulingSystem(schedulingSystem);
	}

	public abstract Vector<Task> loadNewsList();

	public abstract Vector<Resource> loadResourcesList();
	
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

}

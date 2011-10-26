package Model.InputSystem;
import java.util.Vector;

import Model.DataModel.Configurator.Configurator;
import Controller.SchedulingSystem.SchedulingSystem;
import Controller.SchedulingSystem.Device;
import Controller.SchedulingSystem.Process;


public abstract class InputSystem {
	
	private Configurator configurator;
	private SchedulingSystem schedulingSystem;

	public InputSystem(Configurator configurator, SchedulingSystem schedulingSystem) {
		this.setConfigurator(configurator);
		this.setSchedulingSystem(schedulingSystem);
	}

	public abstract Vector<Process> loadNewsList();

	public abstract Vector<Device> loadDevicesList();
	
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

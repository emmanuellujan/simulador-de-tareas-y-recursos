package Model.LogginSystem;
import java.util.Vector;

import Model.DataModel.Configurator.Configurator;
import Model.DataModel.SimulationTime.SimulationTime;
import Controller.SchedulingSystem.Device;

public abstract class LogginSystem {
	private Configurator configurator;
	private Vector<SimulationTime> simulationTimes;

	public LogginSystem(Configurator configurator,Vector<SimulationTime> simulationTimes) {
		this.setConfigurator(configurator);
		this.setSimulationTimes(simulationTimes);
	}
	
	public void log(int time, Vector<Device> devicesList){
		SimulationTime simulationTime = new SimulationTime(time, devicesList);
		this.addSimulationTime(simulationTime);
	}
	
	public abstract void writeLog();
	
	public Configurator getConfigurator() {
		return configurator;
	}

	public void setConfigurator(Configurator configurator) {
		this.configurator = configurator;
	}

	public Vector<SimulationTime> getSimulationTimes() {
		return simulationTimes;
	}

	public void setSimulationTimes(Vector<SimulationTime> simulationTimes) {
		this.simulationTimes = simulationTimes;
	}
	
	public void addSimulationTime(SimulationTime simulationTime){
		this.simulationTimes.add(simulationTime);
	}
}

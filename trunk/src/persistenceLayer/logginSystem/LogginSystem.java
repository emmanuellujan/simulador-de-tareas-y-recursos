package persistenceLayer.logginSystem;

import java.util.Vector;

import logicLayer.schedulingSystem.Actor;
import persistenceLayer.dataModel.Configurator.Configurator;
import persistenceLayer.dataModel.SimulationTime.SimulationTime;

public abstract class LogginSystem {
	private Configurator configurator;
	private Vector<String> errorMsgs;
	private Vector<SimulationTime> simulationTimes;

	public LogginSystem(Configurator configurator) {
		this.setConfigurator(configurator);
		Vector<SimulationTime> simulationTimes = new Vector<SimulationTime>();
		this.setSimulationTimes(simulationTimes);
		Vector<String> errorMsgs = new Vector<String>();
		this.setErrorMsgs(errorMsgs);
	}

	public LogginSystem(Configurator configurator,
			Vector<SimulationTime> simulationTimes) {
		this.setConfigurator(configurator);
		this.setSimulationTimes(simulationTimes);
	}

	public void addErrorMsg(String errorMsg) {
		this.errorMsgs.add(errorMsg);
	}

	public void addSimulationTime(SimulationTime simulationTime) {
		this.simulationTimes.add(simulationTime);
	}

	public Configurator getConfigurator() {
		return configurator;
	}

	public Vector<String> getErrorMsgs() {
		return errorMsgs;
	}

	public Vector<SimulationTime> getSimulationTimes() {
		return simulationTimes;
	}

	public void log(int time, Vector<Actor> devicesList) {
		SimulationTime simulationTime = new SimulationTime(time, devicesList);
		this.addSimulationTime(simulationTime);
	}

	public String renderErrors() {
		String errors = "";
		Vector<String> errorsMsgs = this.getErrorMsgs();
		int n = errorsMsgs.size();
		if (n > 0) {
			errors += "Errors:\n\n";
			for (int i = 0; i < n; i++) {
				errors += i + ". " + errorsMsgs.elementAt(i) + "\n";
			}
		}
		return errors;
	}

	public void setConfigurator(Configurator configurator) {
		this.configurator = configurator;
	}

	public void setErrorMsgs(Vector<String> errorMsgs) {
		this.errorMsgs = errorMsgs;
	}

	public void setSimulationTimes(Vector<SimulationTime> simulationTimes) {
		this.simulationTimes = simulationTimes;
	}

	public abstract void writeLog();

}

package persistenceLayer.logginSystem;

import java.util.Vector;

import logicLayer.schedulingSystem.Resource;
import logicLayer.schedulingSystem.Task;
import persistenceLayer.dataModel.Configurator.Configurator;
import persistenceLayer.dataModel.SimulationTime.SimulationTime;

public abstract class LogginSystem {
	private Configurator configurator;
	private Vector<String> errorMsgs;
	private Vector<SimulationTime> simulationTimes;
	private Vector<Task> successfulFinishedTasks;
	private Vector<Task> failedFinishedTasks;
	private int nbrExecContTasks;

	public LogginSystem(Configurator configurator) {
		this.setConfigurator(configurator);
		Vector<SimulationTime> simulationTimes = new Vector<SimulationTime>();
		this.setSimulationTimes(simulationTimes);
		Vector<String> errorMsgs = new Vector<String>();
		this.setErrorMsgs(errorMsgs);
		Vector<Task> successfulFinishedTasks = new Vector<Task>();
		Vector<Task> failedFinishedTasks = new Vector<Task>();
		this.setSuccessfulFinishedTasks(successfulFinishedTasks);
		this.setFailedFinishedTasks(failedFinishedTasks);
		this.setNbrExecContTasks(0);
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

	public Vector<Task> getFailedFinishedTasks() {
		return failedFinishedTasks;
	}

	public Vector<SimulationTime> getSimulationTimes() {
		return simulationTimes;
	}

	public Vector<Task> getSuccessfulFinishedTasks() {
		return successfulFinishedTasks;
	}

	public void log(int time, Vector<Resource> resourcesList) {
		SimulationTime simulationTime = new SimulationTime(time, resourcesList);
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

	public void setFailedFinishedTasks(Vector<Task> failedFinishedTasks) {
		this.failedFinishedTasks = failedFinishedTasks;
	}

	public void setSimulationTimes(Vector<SimulationTime> simulationTimes) {
		this.simulationTimes = simulationTimes;
	}

	public void setSuccessfulFinishedTasks(Vector<Task> successfulFinishedTasks) {
		this.successfulFinishedTasks = successfulFinishedTasks;
	}

	public abstract void writeLog();
	
	public void incNbrExecContTasks() {
		this.nbrExecContTasks++;
	}

	public int getNbrExecContTasks() {
		return nbrExecContTasks;
	}

	public void setNbrExecContTasks(int nbrExecContTasks) {
		this.nbrExecContTasks = nbrExecContTasks;
	}

}

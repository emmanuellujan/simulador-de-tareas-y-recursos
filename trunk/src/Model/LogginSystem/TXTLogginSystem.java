package Model.LogginSystem;

import java.util.Vector;

import Model.DataModel.Configurator.Configurator;
import Model.DataModel.SimulationTime.SimulationTime;
import Model.DataModel.SimulationTime.SimulationResource;

public class TXTLogginSystem extends FileLogginSystem {

	public TXTLogginSystem(Configurator configurator,
			Vector<SimulationTime> simulationTimes) {
		super(configurator, simulationTimes);
		String outputFileName = this.getConfigurator().getOutputDir()
				+ this.getConfigurator().getProjectName() + "_log.txt";
		this.setOutputFileName(outputFileName);
	}

	public void writeLog() {
		Vector<SimulationTime> simulationTimes = this.getSimulationTimes();
		String xmlLog = "";

		int currTime;
		int n = simulationTimes.size();
		for (int i = 0; i < n; i++) {
			currTime = simulationTimes.get(i).getCurrentTime();
			Vector<SimulationResource> actors = simulationTimes.get(i)
					.getActors();
			xmlLog += "Time: " + currTime + "\n";
			xmlLog += this.logActors(actors);
			xmlLog += "\n";
		}

		this.writeFile(xmlLog);
	}

	private String logActors(Vector<SimulationResource> actors) {
		String xmlLog = "";
		int n = actors.size();
		for (int i = 0; i < n; i++)
			xmlLog += this.logResource(actors.get(i)) + "\n";
		return xmlLog;
	}

	public String logResource(SimulationResource resource) {
		String resId = resource.getResId();
		String taskId = resource.getTaskId();
		String currentAction = resource.getCurrentAction();
		int time = resource.getTime();
		int limitTime = resource.getLimitTime();

		String xmlLog = "";

		xmlLog += "\tResourceId: " + resId + "\n";
		xmlLog += "\t\tCurrent Action: " + currentAction + "\n";
		xmlLog += "\t\tActive Task: " + taskId + "\n";
		xmlLog += "\t\tCurrent Time: " + (time - 1) + "\n";
		xmlLog += "\t\tLimit Time: " + limitTime + "\n";

		Vector<String> intList = resource.getInterruptionList();
		int m = intList.size();
		xmlLog += "\t\tInterruption List: ";
		for (int i = 0; i < m - 1; i++)
			xmlLog += intList.get(i) + ", ";
		if (m - 1 >= 0)
			xmlLog += intList.get(m - 1);
		xmlLog += "\n";

		Vector<String> readyList = resource.getReadyList();
		m = readyList.size();
		xmlLog += "\t\tReady List:";
		for (int i = 0; i < m - 1; i++)
			xmlLog += readyList.get(i) + ", ";
		if (m - 1 >= 0)
			xmlLog += readyList.get(m - 1);
		xmlLog += "\n";

		return xmlLog;
	}
}

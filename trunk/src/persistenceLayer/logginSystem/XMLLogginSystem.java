package persistenceLayer.logginSystem;

import java.util.Vector;

import persistenceLayer.dataModel.Configurator.Configurator;
import persistenceLayer.dataModel.SimulationTime.SimulationActor;
import persistenceLayer.dataModel.SimulationTime.SimulationTime;

public class XMLLogginSystem extends FileLogginSystem {

	public XMLLogginSystem(Configurator configurator,
			Vector<SimulationTime> simulationTimes) {
		super(configurator, simulationTimes, "xml");
	}

	private String logActors(Vector<SimulationActor> actors) {
		String xmlLog = "\t\t<actors>\n";
		int n = actors.size();
		for (int i = 0; i < n; i++)
			xmlLog += this.logResource(actors.get(i));
		xmlLog += "\t\t</actors>\n";

		return xmlLog;
	}

	public String logResource(SimulationActor resource) {
		String resId = resource.getResId();
		String taskId = resource.getTaskId();
		String currentAction = resource.getCurrentAction();
		int time = resource.getTime();
		int limitTime = resource.getLimitTime();

		String xmlLog = "";
		xmlLog += "\t\t\t<resource>\n";
		xmlLog += "\t\t\t\t<resourceId>" + resId + "</resourceId>\n";
		xmlLog += "\t\t\t\t<currentAction>" + currentAction
				+ "</currentAction>\n";
		xmlLog += "\t\t\t\t<activeTask>" + taskId + "</activeTask>\n";
		xmlLog += "\t\t\t\t<currentTime>" + (time - 1) + "</currentTime>\n";
		xmlLog += "\t\t\t\t<limitTime>" + limitTime + "</limitTime>\n";

		Vector<String> intList = resource.getInterruptionList();
		int m = intList.size();
		xmlLog += "\t\t\t\t<interruptionList>";
		for (int i = 0; i < m - 1; i++)
			xmlLog += intList.get(i) + ", ";
		if (m - 1 >= 0)
			xmlLog += intList.get(m - 1);
		xmlLog += "</interruptionList>\n";

		Vector<String> readyList = resource.getReadyList();
		m = readyList.size();
		xmlLog += "\t\t\t\t<readyList>";
		for (int i = 0; i < m - 1; i++)
			xmlLog += readyList.get(i) + ", ";
		if (m - 1 >= 0)
			xmlLog += readyList.get(m - 1);
		xmlLog += "</readyList>\n";
		xmlLog += "\t\t\t</resource>\n";

		return xmlLog;
	}

	public String getLog(){
		Vector<SimulationTime> simulationTimes = this.getSimulationTimes();
		String xmlLog = "";
		xmlLog = "<simulation>\n";

		int currTime;
		int n = simulationTimes.size();
		for (int i = 0; i < n; i++) {
			currTime = simulationTimes.get(i).getCurrentTime();
			Vector<SimulationActor> actors = simulationTimes.get(i)
					.getActors();
			xmlLog += "\t<time>\n";
			xmlLog += "\t\t<currentTime>" + currTime + "</currentTime>\n";
			xmlLog += this.logActors(actors);
			xmlLog += "\t</time>\n";
		}
		xmlLog += "";
		xmlLog += "</simulation>\n";
		return xmlLog;
	}
	
}
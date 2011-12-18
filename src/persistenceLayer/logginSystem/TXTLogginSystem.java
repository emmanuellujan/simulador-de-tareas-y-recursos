package persistenceLayer.logginSystem;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import persistenceLayer.dataModel.Configurator.Configurator;
import persistenceLayer.dataModel.SimulationTime.SimulationActor;
import persistenceLayer.dataModel.SimulationTime.SimulationResource;
import persistenceLayer.dataModel.SimulationTime.SimulationTime;

public class TXTLogginSystem extends FileLogginSystem {

	public TXTLogginSystem(Configurator configurator,
			Vector<SimulationTime> simulationTimes) {
		super(configurator, simulationTimes, "txt");
	}

	@Override
	public String getLog() {
		Vector<SimulationTime> simulationTimes = this.getSimulationTimes();
		String txtLog = "";

		int currTime;
		int n = simulationTimes.size();
		for (int i = 0; i < n; i++) {
			currTime = simulationTimes.get(i).getCurrentTime();
			Vector<SimulationResource> resources = simulationTimes.get(i)
					.getAllResources();
			txtLog += "Time: " + currTime + "\n";
			txtLog += this.logResources(resources);
			txtLog += "\n";
		}
		return txtLog;
	}

	public String logActor(SimulationActor resource) {
		String taskId = resource.getTaskId();
		String currentAction = resource.getCurrentAction();
		int time = resource.getTime();
		int limitTime = resource.getLimitTime();

		String txtLog = "";

		txtLog += this.logResource(resource);

		txtLog += "\t\tCurrent Action: " + currentAction + "\n";
		txtLog += "\t\tActive Task: " + taskId + "\n";
		txtLog += "\t\tCurrent Time: " + (time - 1) + "\n";
		txtLog += "\t\tLimit Time: " + limitTime + "\n";

		Vector<String> intList = resource.getInterruptionList();
		int m = intList.size();
		txtLog += "\t\tInterruption List: ";
		for (int i = 0; i < m - 1; i++)
			txtLog += intList.get(i) + ", ";
		if (m - 1 >= 0)
			txtLog += intList.get(m - 1);
		txtLog += "\n";

		Vector<String> readyList = resource.getReadyList();
		m = readyList.size();
		txtLog += "\t\tReady List:";
		for (int i = 0; i < m - 1; i++)
			txtLog += readyList.get(i) + ", ";
		if (m - 1 >= 0)
			txtLog += readyList.get(m - 1);
		txtLog += "\n";

		return txtLog;
	}

	private String logResource(SimulationResource resource) {

		String txtLog = "";

		txtLog += "\tResourceId: " + resource.getResId() + "\n";

		txtLog += "\t\tRelationships Limit: " + resource.getResourceMaxLimit()
				+ "\n";

		txtLog += "\t\tRelationships: ";
		Vector<String> relationsIds = resource.getRelationsIds();
		int n = relationsIds.size();
		for (int i = 0; i < n - 1; i++)
			txtLog += relationsIds.get(i) + ", ";
		if (n - 1 >= 0)
			txtLog += relationsIds.get(n - 1);
		txtLog += "\n";

		txtLog += "\t\tNbr. of properties changes: "
				+ resource.getNbrOfPropChanges() + "\n";

		txtLog += "\t\tProperties:\n";
		Hashtable<String, String> properties = resource.getProperties();
		Enumeration<String> e = properties.keys();
		String key;
		while (e.hasMoreElements()) {
			key = e.nextElement();
			txtLog += "\t\t\t" + key + ": " + properties.get(key) + "\n";
		}

		return txtLog;
	}

	private String logResources(Vector<SimulationResource> allResourcesList) {
		String txtLog = "";
		SimulationResource resource = null;
		int n = allResourcesList.size();
		for (int i = 0; i < n; i++) {
			resource = allResourcesList.get(i);
			if (resource.getClass().getName().contains("Actor"))
				txtLog += this.logActor((SimulationActor) resource) + "\n";
			else
				txtLog += this.logResource(resource) + "\n";
		}
		return txtLog;
	}

}

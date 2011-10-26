package Model.LogginSystem;
import java.util.Vector;

import Model.DataModel.Configurator.Configurator;
import Model.DataModel.SimulationTime.SimulationTime;
import Model.DataModel.SimulationTime.SimulationDevice;


public class XMLLogginSystem extends FileLogginSystem{

	public XMLLogginSystem(Configurator configurator,Vector<SimulationTime> simulationTimes) {
		super(configurator,simulationTimes);
		String outputFileName = this.getConfigurator().getIoDirectory() + this.getConfigurator().getOutputFile() + ".xml";
		this.setOutputFileName(outputFileName);
	}

	public void writeLog(){
		Vector<SimulationTime> simulationTimes = this.getSimulationTimes();
		String xmlLog = "";
		xmlLog = "<simulation>\n";
		
		int currTime; 
		int n = simulationTimes.size();
		for(int i=0;i<n;i++){
			currTime = simulationTimes.get(i).getCurrentTime();
			Vector<SimulationDevice> devices = simulationTimes.get(i).getDevices();
			xmlLog += "\t<time>\n";
			xmlLog += "\t\t<currentTime>"+currTime+"</currentTime>\n";
			xmlLog += this.logDevices(devices);
			xmlLog += "\t</time>\n";
		}
		xmlLog += "";
		xmlLog += "</simulation>\n";

		this.writeFile(xmlLog);
	}

	private String logDevices(Vector<SimulationDevice> devices) {
		String xmlLog = "\t\t<devices>\n";
		int n = devices.size();
		for(int i=0;i<n;i++)
			xmlLog += this.logDevice(devices.get(i));
		xmlLog += "\t\t</devices>\n";
		
		return xmlLog;
	}

	public String logDevice(SimulationDevice device){
		String devId = device.getDevId();
		String processId = device.getProcessId();
		String currentAction = device.getCurrentAction();
		int time = device.getTime();
		int limitTime = device.getLimitTime();

		String xmlLog = "";
		xmlLog += "\t\t\t<device>\n";
		xmlLog += "\t\t\t\t<deviceId>" + devId + "</deviceId>\n";
		xmlLog += "\t\t\t\t<currentAction>" + currentAction + "</currentAction>\n";
		xmlLog += "\t\t\t\t<activeProcess>" + processId + "</activeProcess>\n";
		xmlLog += "\t\t\t\t<currentTime>" + (time-1) + "</currentTime>\n";
		xmlLog += "\t\t\t\t<limitTime>" + limitTime + "</limitTime>\n";

		Vector<String> intList = device.getInterruptionList();
		int m = intList.size();
		xmlLog += "\t\t\t\t<interruptionList>";
		for(int i=0;i<m-1;i++)
			xmlLog += intList.get(i)+", ";
		if(m-1>=0)
			xmlLog += intList.get(m-1);
		xmlLog += "</interruptionList>\n";

		Vector<String> readyList = device.getReadyList();
		m = readyList.size();
		xmlLog += "\t\t\t\t<readyList>";
		for(int i=0;i<m-1;i++)
			xmlLog += readyList.get(i)+", ";
		if(m-1>=0)
			xmlLog += readyList.get(m-1);
		xmlLog += "</readyList>\n";
		xmlLog += "\t\t\t</device>\n";

		return xmlLog;
	}
}
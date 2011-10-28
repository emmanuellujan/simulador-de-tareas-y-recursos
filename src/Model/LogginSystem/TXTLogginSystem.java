package Model.LogginSystem;
import java.util.Vector;

import Model.DataModel.Configurator.Configurator;
import Model.DataModel.SimulationTime.SimulationTime;
import Model.DataModel.SimulationTime.SimulationDevice;


public class TXTLogginSystem  extends FileLogginSystem{

	public TXTLogginSystem(Configurator configurator,Vector<SimulationTime> simulationTimes) {
		super(configurator,simulationTimes);
		String outputFileName = this.getConfigurator().getIoDirectory() + this.getConfigurator().getOutputFile() + ".txt";
		this.setOutputFileName(outputFileName);
	}

	public void writeLog(){
		Vector<SimulationTime> simulationTimes = this.getSimulationTimes();
		String xmlLog = "";
		
		int currTime; 
		int n = simulationTimes.size();
		for(int i=0;i<n;i++){
			currTime = simulationTimes.get(i).getCurrentTime();
			Vector<SimulationDevice> devices = simulationTimes.get(i).getDevices();
			xmlLog += "Time: "+currTime+"\n";
			xmlLog += this.logDevices(devices);
			xmlLog += "\n";
		}
		
		this.writeFile(xmlLog);
	}

	private String logDevices(Vector<SimulationDevice> devices) {
		String xmlLog = "";
		int n = devices.size();
		for(int i=0;i<n;i++)
			xmlLog += this.logDevice(devices.get(i)) + "\n";
		return xmlLog;
	}

	public String logDevice(SimulationDevice device){
		String devId = device.getDevId();
		String taskId = device.getTaskId();
		String currentAction = device.getCurrentAction();
		int time = device.getTime();
		int limitTime = device.getLimitTime();

		String xmlLog = "";
		
		xmlLog += "\tDeviceId: " + devId + "\n";
		xmlLog += "\t\tCurrent Action: " + currentAction + "\n";
		xmlLog += "\t\tActive Task: " + taskId + "\n";
		xmlLog += "\t\tCurrent Time: " + (time-1) + "\n";
		xmlLog += "\t\tLimit Time: " + limitTime + "\n";

		Vector<String> intList = device.getInterruptionList();
		int m = intList.size();
		xmlLog += "\t\tInterruption List: ";
		for(int i=0;i<m-1;i++)
			xmlLog += intList.get(i)+", ";
		if(m-1>=0)
			xmlLog += intList.get(m-1);
		xmlLog += "\n";

		Vector<String> readyList = device.getReadyList();
		m = readyList.size();
		xmlLog += "\t\tReady List:";
		for(int i=0;i<m-1;i++)
			xmlLog += readyList.get(i)+", ";
		if(m-1>=0)
			xmlLog += readyList.get(m-1);
		xmlLog += "\n";
			
		return xmlLog;
	}
}

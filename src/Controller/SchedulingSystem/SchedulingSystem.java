package Controller.SchedulingSystem;
import java.util.Vector;

import Model.DataModel.Configurator.Configurator;
import Model.DataModel.SimulationTime.SimulationTime;
import Model.InputSystem.InputSystem;
import Model.InputSystem.XMLInputSystem;
import Model.LogginSystem.CompLogginSystem;
import Controller.SchedulingAlgorithmSystem.FCFS;

public class SchedulingSystem {

	private InputSystem inputSystem;
	private CompLogginSystem logginSystem;
	private Vector<Process> newsList;
	private Vector<Device> devicesList;
	private Vector<Process> finishedList;
	private int numberOfProcesses;
	private Device deliverDev;

	public SchedulingSystem(){
		Vector<Process> newsList = new Vector<Process>();
		Vector<Device> devicesList = new Vector<Device>();
		Vector<Process> finishedList = new Vector<Process>();

		Configurator configurator = new Configurator();
		InputSystem inputSystem = new XMLInputSystem(configurator,this);
		
		Vector<SimulationTime> simulationTimes = new Vector<SimulationTime>();
		CompLogginSystem compLogginSystem = new CompLogginSystem(configurator,simulationTimes);
		
		newsList = inputSystem.loadNewsList();
		devicesList = inputSystem.loadDevicesList();

		String deliverDevId="deliverDev";
		FCFS saReadyList = new FCFS();
		int limitTime = -1;
		Device deliverDev = new Device(deliverDevId, saReadyList, limitTime, this);
		deliverDev.setReadyList(newsList);
				
		this.setNewsList(newsList);
		this.setDevicesList(devicesList);
		this.setFinishedList(finishedList);
		this.setNumberOfProcesses(newsList.size());
		this.setInputSystem(inputSystem);
		this.setLogginSystem(compLogginSystem);
		this.setDeliverDev(deliverDev);
	}

	public void simulate() {
		Vector<Device> devicesList = getDevicesList();
		CompLogginSystem logginSystem = this.getLogginSystem();
		Device deliverDev = this.getDeliverDev();
		devicesList.add(0, deliverDev);
		int i = 0;
		int n = devicesList.size();
		while(!scheduleFinished()){
			for(int j=0;j<n;j++)
				devicesList.get(j).exec();
			this.incTime();
			logginSystem.log(i,devicesList);
			i++;
		}
		logginSystem.writeLog();
	}

	private void incTime() {
		Vector<Device> devicesList = this.getDevicesList();
		int n = devicesList.size();
		for(int i=0;i<n;i++){
			Device device = devicesList.get(i);
			device.incTime();
		}
	}

	private boolean scheduleFinished() {
		Vector<Process> finishedList = this.getFinishedList();
		int n = finishedList.size();
		int numberOfProcesses = this.getNumberOfProcesses();
		if(numberOfProcesses==n)
			return true;
		else
			return false;
	}

	public void finishProcess(Process currProcess) {
		this.getFinishedList().add(currProcess);
	}

	public Device getDevice(String name) {
		Vector<Device> devicesList = this.getDevicesList();
		boolean finded = false;
		int i = 0;
		int n = devicesList.size();
		Device device = null;
		while(i<n && !finded){
			if(devicesList.get(i).getDevId().equals(name)){
				finded = true;
				device = devicesList.get(i);
			}else
				i++;
		}
		return device;
	}

	public Vector<Process> getNewsList() {
		return newsList;
	}

	public void setNewsList(Vector<Process> newsList) {
		this.newsList = newsList;
	}

	public Vector<Device> getDevicesList() {
		return devicesList;
	}

	public void setDevicesList(Vector<Device> devicesList) {
		this.devicesList = devicesList;
	}

	public Vector<Process> getFinishedList() {
		return finishedList;
	}

	public void setFinishedList(Vector<Process> finishedList) {
		this.finishedList = finishedList;
	}

	public InputSystem getInputSystem() {
		return inputSystem;
	}

	public void setInputSystem(InputSystem InputSystem) {
		this.inputSystem = InputSystem;
	}

	public CompLogginSystem getLogginSystem() {
		return logginSystem;
	}

	public void setLogginSystem(CompLogginSystem logginSystem) {
		this.logginSystem = logginSystem;
	}

	public int getNumberOfProcesses() {
		return numberOfProcesses;
	}

	public void setNumberOfProcesses(int numberOfProcesses) {
		this.numberOfProcesses = numberOfProcesses;
	}

	public Device getDeliverDev() {
		return deliverDev;
	}

	public void setDeliverDev(Device deliverDev) {
		this.deliverDev = deliverDev;
	}

	public static void main(String[] args){
		SchedulingSystem schedulingSystem = new SchedulingSystem();
		schedulingSystem.simulate();
	}

}

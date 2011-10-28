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
	private Vector<Task> newsList;
	private Vector<Resource> devicesList;
	private Vector<Task> finishedList;
	private int numberOfTasks;
	private Resource deliverDev;

	public SchedulingSystem(){
		Vector<Task> newsList = new Vector<Task>();
		Vector<Resource> devicesList = new Vector<Resource>();
		Vector<Task> finishedList = new Vector<Task>();

		Configurator configurator = new Configurator();
		InputSystem inputSystem = new XMLInputSystem(configurator,this);
		
		Vector<SimulationTime> simulationTimes = new Vector<SimulationTime>();
		CompLogginSystem compLogginSystem = new CompLogginSystem(configurator,simulationTimes);
		
		newsList = inputSystem.loadNewsList();
		devicesList = inputSystem.loadDevicesList();

		String deliverDevId="deliverDev";
		FCFS saReadyList = new FCFS();
		int limitTime = -1;
		Resource deliverDev = new Resource(deliverDevId, saReadyList, limitTime, this);
		deliverDev.setReadyList(newsList);
				
		this.setNewsList(newsList);
		this.setDevicesList(devicesList);
		this.setFinishedList(finishedList);
		this.setNumberOfTasks(newsList.size());
		this.setInputSystem(inputSystem);
		this.setLogginSystem(compLogginSystem);
		this.setDeliverDev(deliverDev);
	}

	public void simulate() {
		Vector<Resource> devicesList = getDevicesList();
		CompLogginSystem logginSystem = this.getLogginSystem();
		Resource deliverDev = this.getDeliverDev();
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
		Vector<Resource> devicesList = this.getDevicesList();
		int n = devicesList.size();
		for(int i=0;i<n;i++){
			Resource resource = devicesList.get(i);
			resource.incTime();
		}
	}

	private boolean scheduleFinished() {
		Vector<Task> finishedList = this.getFinishedList();
		int n = finishedList.size();
		int numberOfTasks = this.getNumberOfTasks();
		if(numberOfTasks==n)
			return true;
		else
			return false;
	}

	public void finishTask(Task currTask) {
		this.getFinishedList().add(currTask);
	}

	public Resource getDevice(String name) {
		Vector<Resource> devicesList = this.getDevicesList();
		boolean finded = false;
		int i = 0;
		int n = devicesList.size();
		Resource resource = null;
		while(i<n && !finded){
			if(devicesList.get(i).getDevId().equals(name)){
				finded = true;
				resource = devicesList.get(i);
			}else
				i++;
		}
		return resource;
	}

	public Vector<Task> getNewsList() {
		return newsList;
	}

	public void setNewsList(Vector<Task> newsList) {
		this.newsList = newsList;
	}

	public Vector<Resource> getDevicesList() {
		return devicesList;
	}

	public void setDevicesList(Vector<Resource> devicesList) {
		this.devicesList = devicesList;
	}

	public Vector<Task> getFinishedList() {
		return finishedList;
	}

	public void setFinishedList(Vector<Task> finishedList) {
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

	public int getNumberOfTasks() {
		return numberOfTasks;
	}

	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}

	public Resource getDeliverDev() {
		return deliverDev;
	}

	public void setDeliverDev(Resource deliverDev) {
		this.deliverDev = deliverDev;
	}

	public static void main(String[] args){
		SchedulingSystem schedulingSystem = new SchedulingSystem();
		schedulingSystem.simulate();
	}

}

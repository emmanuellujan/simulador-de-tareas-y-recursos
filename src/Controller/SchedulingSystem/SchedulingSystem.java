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
	private Vector<Resource> resourcesList;
	private Vector<Task> finishedList;
	private int numberOfTasks;
	private Resource deliverRes;

	public SchedulingSystem(){
		Vector<Task> newsList = new Vector<Task>();
		Vector<Resource> resourcesList = new Vector<Resource>();
		Vector<Task> finishedList = new Vector<Task>();

		Configurator configurator = new Configurator();
		InputSystem inputSystem = new XMLInputSystem(configurator,this);
		
		Vector<SimulationTime> simulationTimes = new Vector<SimulationTime>();
		CompLogginSystem compLogginSystem = new CompLogginSystem(configurator,simulationTimes);
		
		newsList = inputSystem.loadNewsList();
		resourcesList = inputSystem.loadResourcesList();

		String deliverResId="deliverRes";
		FCFS saReadyList = new FCFS();
		int limitTime = -1;
		Resource deliverRes = new Resource(deliverResId, saReadyList, limitTime, this);
		deliverRes.setReadyList(newsList);
				
		this.setNewsList(newsList);
		this.setResourcesList(resourcesList);
		this.setFinishedList(finishedList);
		this.setNumberOfTasks(newsList.size());
		this.setInputSystem(inputSystem);
		this.setLogginSystem(compLogginSystem);
		this.setDeliverRes(deliverRes);
	}

	public void simulate() {
		Vector<Resource> resourcesList = getResourcesList();
		CompLogginSystem logginSystem = this.getLogginSystem();
		Resource deliverRes = this.getDeliverRes();
		resourcesList.add(0, deliverRes);
		int i = 0;
		int n = resourcesList.size();
		while(!scheduleFinished()){
			for(int j=0;j<n;j++)
				resourcesList.get(j).exec();
			this.incTime();
			logginSystem.log(i,resourcesList);
			i++;
		}
		logginSystem.writeLog();
	}

	private void incTime() {
		Vector<Resource> resourcesList = this.getResourcesList();
		int n = resourcesList.size();
		for(int i=0;i<n;i++){
			Resource resource = resourcesList.get(i);
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

	public Resource getResource(String name) {
		Vector<Resource> resourcesList = this.getResourcesList();
		boolean finded = false;
		int i = 0;
		int n = resourcesList.size();
		Resource resource = null;
		while(i<n && !finded){
			if(resourcesList.get(i).getResId().equals(name)){
				finded = true;
				resource = resourcesList.get(i);
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

	public Vector<Resource> getResourcesList() {
		return resourcesList;
	}

	public void setResourcesList(Vector<Resource> resourcesList) {
		this.resourcesList = resourcesList;
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

	public Resource getDeliverRes() {
		return deliverRes;
	}

	public void setDeliverRes(Resource deliverRes) {
		this.deliverRes = deliverRes;
	}

	public static void main(String[] args){
		SchedulingSystem schedulingSystem = new SchedulingSystem();
		schedulingSystem.simulate();
	}

}

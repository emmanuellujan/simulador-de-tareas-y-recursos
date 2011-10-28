package Model.DataModel.SimulationTime;

import java.util.Vector;

import Controller.SchedulingSystem.Resource;
import Controller.SchedulingSystem.Task;


public class SimulationDevice {
	private String devId;
	private String currentAction;
	private String taskId;
	private int time;
	private int limitTime;
	private Vector<String> interruptionList;
	private Vector<String> readyList;
	
	public SimulationDevice(Resource resource){
		String devId = resource.getDevId();
		String currentAction = resource.getCurrAction();
		String taskId;
		if(resource.getCurrTask()!=null)
			taskId = resource.getCurrTask().getTaskId();
		else
			taskId = "None";
		int time = resource.getTime();
		int limitTime = resource.getLimitTime();		
		
		Vector<Task> intList = resource.getIntList();
		int m = intList.size();
		Vector<String> sInterruptionList = new Vector<String>();
		for(int i=0;i<m;i++)
			sInterruptionList.add(intList.get(i).getTaskId());

		Vector<Task> readyList = resource.getReadyList();
		m = readyList.size();
		Vector<String> sReadyList = new Vector<String>();
		for(int i=0;i<m;i++)
			sReadyList.add(readyList.get(i).getTaskId());
		
		this.setDevId(devId);
		this.setCurrentAction(currentAction);
		this.setTaskId(taskId);
		this.setTime(time);
		this.setLimitTime(limitTime);
		this.setInterruptionList(sInterruptionList);
		this.setReadyList(sReadyList);
	}
	
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public String getCurrentAction() {
		return currentAction;
	}
	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getLimitTime() {
		return limitTime;
	}
	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}
	public Vector<String> getInterruptionList() {
		return interruptionList;
	}
	public void setInterruptionList(Vector<String> interruptionList) {
		this.interruptionList = interruptionList;
	}
	public Vector<String> getReadyList() {
		return readyList;
	}
	public void setReadyList(Vector<String> readyList) {
		this.readyList = readyList;
	}
}

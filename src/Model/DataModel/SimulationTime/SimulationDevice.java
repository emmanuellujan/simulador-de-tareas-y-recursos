package Model.DataModel.SimulationTime;

import java.util.Vector;

import Controller.SchedulingSystem.Device;
import Controller.SchedulingSystem.Process;


public class SimulationDevice {
	private String devId;
	private String currentAction;
	private String processId;
	private int time;
	private int limitTime;
	private Vector<String> interruptionList;
	private Vector<String> readyList;
	
	public SimulationDevice(Device device){
		String devId = device.getDevId();
		String currentAction = device.getCurrAction();
		String processId;
		if(device.getCurrProcess()!=null)
			processId = device.getCurrProcess().getProcessId();
		else
			processId = "None";
		int time = device.getTime();
		int limitTime = device.getLimitTime();		
		
		Vector<Process> intList = device.getIntList();
		int m = intList.size();
		Vector<String> sInterruptionList = new Vector<String>();
		for(int i=0;i<m;i++)
			sInterruptionList.add(intList.get(i).getProcessId());

		Vector<Process> readyList = device.getReadyList();
		m = readyList.size();
		Vector<String> sReadyList = new Vector<String>();
		for(int i=0;i<m;i++)
			sReadyList.add(readyList.get(i).getProcessId());
		
		this.setDevId(devId);
		this.setCurrentAction(currentAction);
		this.setProcessId(processId);
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
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
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

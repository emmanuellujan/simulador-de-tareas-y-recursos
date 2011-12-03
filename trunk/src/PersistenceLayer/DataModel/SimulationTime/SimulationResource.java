package PersistenceLayer.DataModel.SimulationTime;

import java.util.Vector;
import LogicLayer.SchedulingSystem.Actor;
import LogicLayer.SchedulingSystem.Task;

public class SimulationResource {
	private String resId;
	private String currentAction;
	private String taskId;
	private int time;
	private int limitTime;
	private Vector<String> interruptionList;
	private Vector<String> readyList;

	public SimulationResource(Actor actor) {
		String resId = actor.getResId();
		String currentAction = actor.getCurrAction();
		String taskId;
		if (actor.getCurrTask() != null)
			taskId = actor.getCurrTask().getTaskId();
		else
			taskId = "None";
		int time = actor.getTime();
		int limitTime = actor.getLimitTime();

		Vector<Task> intList = actor.getIntList();
		int m = intList.size();
		Vector<String> sInterruptionList = new Vector<String>();
		for (int i = 0; i < m; i++)
			sInterruptionList.add(intList.get(i).getTaskId());

		Vector<Task> readyList = actor.getReadyList();
		m = readyList.size();
		Vector<String> sReadyList = new Vector<String>();
		for (int i = 0; i < m; i++)
			sReadyList.add(readyList.get(i).getTaskId());

		this.setResId(resId);
		this.setCurrentAction(currentAction);
		this.setTaskId(taskId);
		this.setTime(time);
		this.setLimitTime(limitTime);
		this.setInterruptionList(sInterruptionList);
		this.setReadyList(sReadyList);
	}

	public String getCurrentAction() {
		return currentAction;
	}

	public Vector<String> getInterruptionList() {
		return interruptionList;
	}

	public int getLimitTime() {
		return limitTime;
	}

	public Vector<String> getReadyList() {
		return readyList;
	}

	public String getResId() {
		return resId;
	}

	public String getTaskId() {
		return taskId;
	}

	public int getTime() {
		return time;
	}

	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}

	public void setInterruptionList(Vector<String> interruptionList) {
		this.interruptionList = interruptionList;
	}

	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}

	public void setReadyList(Vector<String> readyList) {
		this.readyList = readyList;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setTime(int time) {
		this.time = time;
	}
}

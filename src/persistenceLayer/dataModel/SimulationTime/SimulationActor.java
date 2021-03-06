package persistenceLayer.dataModel.SimulationTime;

import java.util.Vector;

import logicLayer.schedulingSystem.Actor;
import logicLayer.schedulingSystem.Task;

public class SimulationActor extends SimulationResource {

	private String currentAction;
	private String taskId;
	private int time;
	private int limitTime;
	private Vector<String> interruptionList;
	private Vector<String> readyList;

	public SimulationActor(Actor actor) {
		super(actor);

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

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setTime(int time) {
		this.time = time;
	}

}

package logicLayer.schedulingAlgorithmSystem;

import java.util.Vector;

import logicLayer.schedulingSystem.Task;


public abstract class SchedulingAlgorithm {

	public String id;

	public SchedulingAlgorithm(String id) {
		this.setId(id);
	}

	public String getId() {
		return id;
	}

	public abstract Task schedule(Vector<Task> tasks);

	public void setId(String id) {
		this.id = id;
	}
}

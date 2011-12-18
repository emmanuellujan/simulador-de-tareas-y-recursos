package logicLayer.schedulingAlgorithmSystem;

import java.util.Vector;

import logicLayer.schedulingSystem.Task;

public class FCFS extends SchedulingAlgorithm {

	public FCFS() {
		super("FCFS");
	}

	@Override
	public Task schedule(Vector<Task> tasks) {
		if (tasks.size() > 0)
			return tasks.get(0);
		else
			return null;
	}

}

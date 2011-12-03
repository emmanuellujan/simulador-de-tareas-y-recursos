package LogicLayer.SchedulingAlgorithmSystem;

import LogicLayer.SchedulingSystem.Task;
import java.util.Vector;

public class FCFS extends SchedulingAlgorithm {

	public FCFS() {
		super("FCFS");
	}

	public Task schedule(Vector<Task> tasks) {
		if (tasks.size() > 0)
			return tasks.get(0);
		else
			return null;
	}

}

package Controller.SchedulingAlgorithmSystem;

import java.util.Vector;
import  Controller.SchedulingSystem.Task;

public class FCFS extends SchedulingAlgorithm {
	
	public FCFS(){}
	
	public Task schedule(Vector<Task> tasks){
		if(tasks.size()>0)
			return tasks.get(0);
		else
			return null;
	}

}

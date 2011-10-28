package Controller.SchedulingAlgorithmSystem;
import java.util.Vector;
import  Controller.SchedulingSystem.Task;

public class PrioritiesSA extends SchedulingAlgorithm {
	
	public PrioritiesSA(){}

	public Task schedule(Vector<Task> tasks) {
		Task task = null;
		int n = tasks.size();
		int maxPriority = -1;
		int currPriority = -1;
		for(int i=0;i<n;i++){
			currPriority = tasks.get(i).getPriority();
			if(maxPriority < currPriority){
				maxPriority = currPriority;
				task = tasks.get(i);
			}
		}
		return task;
	}
	
}

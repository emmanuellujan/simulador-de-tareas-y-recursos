package Controller.SchedulingAlgorithmSystem;
import java.util.Vector;
import  Controller.SchedulingSystem.Process;

public class PrioritiesSA extends SchedulingAlgorithm {
	
	public PrioritiesSA(){}

	public Process schedule(Vector<Process> processes) {
		Process process = null;
		int n = processes.size();
		int maxPriority = -1;
		int currPriority = -1;
		for(int i=0;i<n;i++){
			currPriority = processes.get(i).getPriority();
			if(maxPriority < currPriority){
				maxPriority = currPriority;
				process = processes.get(i);
			}
		}
		return process;
	}
	
}

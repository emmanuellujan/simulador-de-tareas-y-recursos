package Controller.SchedulingAlgorithmSystem;

import java.util.Vector;
import  Controller.SchedulingSystem.Process;

public class FCFS extends SchedulingAlgorithm {
	
	public FCFS(){}
	
	public Process schedule(Vector<Process> processes){
		if(processes.size()>0)
			return processes.get(0);
		else
			return null;
	}

}

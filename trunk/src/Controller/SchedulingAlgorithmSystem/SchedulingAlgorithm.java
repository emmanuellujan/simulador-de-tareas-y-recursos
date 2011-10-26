package Controller.SchedulingAlgorithmSystem;
import java.util.Vector;
import  Controller.SchedulingSystem.Process;

public abstract class SchedulingAlgorithm {

	public abstract Process schedule(Vector<Process> processes);

}

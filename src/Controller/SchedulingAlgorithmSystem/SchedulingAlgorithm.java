package Controller.SchedulingAlgorithmSystem;
import java.util.Vector;
import  Controller.SchedulingSystem.Task;

public abstract class SchedulingAlgorithm {

	public abstract Task schedule(Vector<Task> tasks);

}

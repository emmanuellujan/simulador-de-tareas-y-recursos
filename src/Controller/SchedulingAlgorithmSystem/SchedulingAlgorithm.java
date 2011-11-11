package Controller.SchedulingAlgorithmSystem;
import java.util.Vector;
import  Controller.SchedulingSystem.Task;

public abstract class SchedulingAlgorithm {

	public String id;
	
	public SchedulingAlgorithm(String id){
		this.setId(id);
	}
	
	public abstract Task schedule(Vector<Task> tasks);

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

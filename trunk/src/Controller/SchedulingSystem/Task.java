package Controller.SchedulingSystem;

import java.util.Vector;

import Controller.FilterSystem.Filter;

public class Task {
	private String taskId;
	private int programCounter;
	private Vector<String> workUnits; // Example: {res1, res1, res1, res0,
										// int_res0, int_res0, res2, res2, end}
	private int priority;
	private int difficult;
	private String status;// Si bien es un String, el contenido del mismo debe
							// ser 'En proceso','Finalizada', 'Nueva'. Puede
							// idearse otra forma de representacion
	
	private String contTaskId;
	private Task contingencyTask;
	
	private SchedulingSystem schedulingSystem;

	private  Filter filter; 
	private Updater updater;
	
	public Task(String taskId, int priority, Vector<String> workUnits,
			String contTaskId, Task contingencyTask, String currentStatus, int difficult,
			SchedulingSystem schedulingSystem, Filter filter, Updater updater) {
		this.setTaskId(taskId);
		this.setProgramCounter(-1);
		this.setWorkUnits(workUnits);
		this.setPriority(priority);
		this.setContTaskId(contTaskId);
		this.setContingencyTask(contingencyTask);
		this.setStatus(currentStatus);
		this.setDifficult(difficult);
		this.setSchedulingSystem(schedulingSystem);
		this.setFilter(filter);
		this.setUpdater(updater);
	}

	public String getCurrent() {
		Vector<String> workUnits = this.getWorkUnits();
		int programCounter = this.getProgramCounter();
		String current = null;
		if (programCounter > -1)
			current = workUnits.get(programCounter);
		return current;
	}

	public String getNext(Actor actor) {
		String next = null;
		if (this.getDifficult() < actor.getCapacity()) {
			Vector<String> workUnits = this.getWorkUnits();
			int n = workUnits.size();
			if (n > 0) {
				int programCounter = this.getProgramCounter();
				if (programCounter < n - 1) {
					programCounter++;
					this.setProgramCounter(programCounter);
					next = workUnits.get(programCounter);
				} else if (programCounter == n - 1)
					next = "end";
			}
		} else {
			next = "end";
			Task t = this.getContingencyTask();
			if (t != null)
				actor.addReadyList(t, actor.getResId());	
		}
		return next;
	}

	public void decProgramCounter() {
		int programCounter = this.getProgramCounter();
		programCounter--;
		this.setProgramCounter(programCounter);
	}

	public void exec() {
		// Simulated Execution. No need for code.
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public int getProgramCounter() {
		return programCounter;
	}

	public void setProgramCounter(int programCounter) {
		this.programCounter = programCounter;
	}

	public Vector<String> getWorkUnits() {
		return this.workUnits;
	}

	public void setWorkUnits(Vector<String> workUnits) {
		this.workUnits = workUnits;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getDifficult() {
		return difficult;
	}

	public void setDifficult(int difficult) {
		this.difficult = difficult;
	}

	public Task getContingencyTask() {
		return contingencyTask;
	}

	public void setContingencyTask(Task contingencyTask) {
		this.contingencyTask = contingencyTask;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String currentStatus) {
		if ((currentStatus != "Processing") && (currentStatus != "Finished")
				&& (currentStatus != "New")) {
			String errorMsg = "You attempted to insert a wrong task state "+ currentStatus +" in the task "+this.getTaskId();
			this.getSchedulingSystem().getCompLogginSystem().addErrorMsg(errorMsg);
		} else
			this.status = currentStatus;
	}

	public void setContTaskId(String contTaskId) {
		this.contTaskId = contTaskId;
	}

	public String getContTaskId() {
		return this.contTaskId;
	}

	public SchedulingSystem getSchedulingSystem() {
		return schedulingSystem;
	}

	public void setSchedulingSystem(SchedulingSystem schedulingSystem) {
		this.schedulingSystem = schedulingSystem;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}
	
	public Updater getUpdater() {
		return updater;
	}

	public void setUpdater(Updater updater) {
		this.updater = updater;
	}

	public boolean evalConditions() {
		Filter filter = this.getFilter();
		if(filter!=null){
			Vector<Resource> resources = this.getSchedulingSystem().getResourcesList();
			int n = resources.size();
			int i=0;
			boolean found = false;
			while(!found && i<n){
				found = filter.eval(resources.elementAt(i));
				i++;
			}
		}
		return true;
	}

	public void execPostProcessing() {
		Updater updater = this.getUpdater();
		if(updater!=null){
			Vector<Resource> resources = this.getSchedulingSystem().getResourcesList();
			int n = resources.size();
			for(int i = 0; i < n; i++)
				updater.update(resources.elementAt(i));
		}
	}
}

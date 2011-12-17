package logicLayer.schedulingSystem;

import java.util.Vector;

import logicLayer.filterSystem.Filter;

public class Task {
	private String taskId;
	private int programCounter;

	private Vector<String> workUnits;
	// Example: {res1, res1, res1, res0,
	// int_res0, int_res0, res2, res2, end}
	private int priority;
	private int difficult;
	private String status;
	// Example: {Processing, Finished, New}

	private String contTaskId;
	private Task contingencyTask;

	private SchedulingSystem schedulingSystem;

	private Filter filter;
	private Updater updater;

	public Task(String taskId, int priority, Vector<String> workUnits,
			String contTaskId, Task contingencyTask, String currentStatus,
			int difficult, SchedulingSystem schedulingSystem, Filter filter,
			Updater updater) {
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

	public void decProgramCounter() {
		int programCounter = this.getProgramCounter();
		programCounter--;
		this.setProgramCounter(programCounter);
	}

	public boolean evalConditions() {
		Filter filter = this.getFilter();
		if (filter != null) {
			Vector<Resource> resources = this.getSchedulingSystem()
					.getIoSystem().getAllResourcesList();

			boolean r = false;
			int n = resources.size();
			int i = 0;
			while (!r && i < n) {
				r = filter.eval(resources.elementAt(i));
				i++;
			}
			return r;
		} else
			return true;
	}

	public void exec() {
		// Simulated Execution. No need for code.
	}

	public void execPostProcessing() {
		Updater updater = this.getUpdater();
		if (updater != null) {
			Vector<Resource> resources = this.getSchedulingSystem()
					.getIoSystem().getAllResourcesList();
			int n = resources.size();
			for (int i = 0; i < n; i++)
				updater.update(resources.elementAt(i));
		}
	}

	public Task getContingencyTask() {
		return contingencyTask;
	}

	public String getContTaskId() {
		return this.contTaskId;
	}

	public String getCurrent() {
		Vector<String> workUnits = this.getWorkUnits();
		int programCounter = this.getProgramCounter();
		String current = null;
		if (programCounter > -1)
			current = workUnits.get(programCounter);
		return current;
	}

	public int getDifficult() {
		return difficult;
	}

	public Filter getFilter() {
		return filter;
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
			next = "fail";
			Task t = this.getContingencyTask();
			if (t != null){
				this.getSchedulingSystem().getLogger().incNbrExecContTasks();
				actor.addReadyList(t, actor.getResId());
			}
		}
		return next;
	}

	public int getPriority() {
		return priority;
	}

	public int getProgramCounter() {
		return programCounter;
	}

	public SchedulingSystem getSchedulingSystem() {
		return schedulingSystem;
	}

	public String getStatus() {
		return this.status;
	}

	public String getTaskId() {
		return taskId;
	}

	public Updater getUpdater() {
		return updater;
	}

	public Vector<String> getWorkUnits() {
		return this.workUnits;
	}

	public void print() {
		System.out.println("Task Id: " + this.getTaskId());
	}

	public void reset() {
		this.setProgramCounter(-1);
		Task t = this.getContingencyTask();
		if (t != null)
			t.reset();
	}

	public void setContingencyTask(Task contingencyTask) {
		this.contingencyTask = contingencyTask;
	}

	public void setContTaskId(String contTaskId) {
		this.contTaskId = contTaskId;
	}

	public void setDifficult(int difficult) {
		this.difficult = difficult;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setProgramCounter(int programCounter) {
		this.programCounter = programCounter;
	}

	public void setSchedulingSystem(SchedulingSystem schedulingSystem) {
		this.schedulingSystem = schedulingSystem;
	}

	public void setStatus(String currentStatus) {
		if ((currentStatus != "Processing") && (currentStatus != "Finished")
				&& (currentStatus != "New")) {
			String errorMsg = "You attempted to insert a wrong task state "
					+ currentStatus + " in the task " + this.getTaskId();
			this.getSchedulingSystem().getLogger().addErrorMsg(errorMsg);
		} else
			this.status = currentStatus;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public void setUpdater(Updater updater) {
		this.updater = updater;
	}

	public void setWorkUnits(Vector<String> workUnits) {
		this.workUnits = workUnits;
	}
}

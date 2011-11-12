package Controller.SchedulingSystem;

import java.util.Vector;

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

	/*
	 * //This properties can be added in future. See
	 * http://beru.univ-brest.fr/~singhoff/cheddar/ug/cheddar-r2.html private
	 * String type; private String addressSpace; private int capacity; private
	 * int jitter; private int deadLine; private int period; private int
	 * startTime; private int blockingTime; private int critically; private int
	 * activationRule; private int stackMemorySize; private int textMemorySize;
	 * private String seed; private int contextSwitchOverhead;
	 */

	public Task(String taskId, int priority, Vector<String> workUnits,
			String contTaskId, Task contingencyTask, String currentStatus, int difficult) {
		this.setTaskId(taskId);
		this.setProgramCounter(-1);
		this.setWorkUnits(workUnits);
		this.setPriority(priority);
		this.setContTaskId(contTaskId);
		this.setContingencyTask(contingencyTask);
		this.setStatus(currentStatus);
		this.setDifficult(difficult);
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
				} else if (programCounter == n - 1) {
					next = "end";
				}
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
		return workUnits;
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
			System.out.println("You attempted to insert a wrong task state.");
			return;
		} else
			this.status = currentStatus;
	}

	public void setContTaskId(String contTaskId) {
		this.contTaskId = contTaskId;
	}

	public String getContTaskId() {
		return this.contTaskId;
	}

	/*
	 * public String getType() { return type; }
	 * 
	 * public void setType(String type) { this.type = type; }
	 * 
	 * public String getAddressSpace() { return addressSpace; }
	 * 
	 * public void setAddressSpace(String addressSpace) { this.addressSpace =
	 * addressSpace; }
	 * 
	 * public int getCapacity() { return capacity; }
	 * 
	 * public void setCapacity(int capacity) { this.capacity = capacity; }
	 * 
	 * public int getJitter() { return jitter; }
	 * 
	 * public void setJitter(int jitter) { this.jitter = jitter; }
	 * 
	 * public int getDeadLine() { return deadLine; }
	 * 
	 * public void setDeadLine(int deadLine) { this.deadLine = deadLine; }
	 * 
	 * public int getPeriod() { return period; }
	 * 
	 * public void setPeriod(int period) { this.period = period; }
	 * 
	 * public int getStartTime() { return startTime; }
	 * 
	 * public void setStartTime(int startTime) { this.startTime = startTime; }
	 * 
	 * public int getBlockingTime() { return blockingTime; }
	 * 
	 * public void setBlockingTime(int blockingTime) { this.blockingTime =
	 * blockingTime; }
	 * 
	 * public int getCritically() { return critically; }
	 * 
	 * public void setCritically(int critically) { this.critically = critically;
	 * }
	 * 
	 * public int getActivationRule() { return activationRule; }
	 * 
	 * public void setActivationRule(int activationRule) { this.activationRule =
	 * activationRule; }
	 * 
	 * public int getStackMemorySize() { return stackMemorySize; }
	 * 
	 * public void setStackMemorySize(int stackMemorySize) {
	 * this.stackMemorySize = stackMemorySize; }
	 * 
	 * public int getTextMemorySize() { return textMemorySize; }
	 * 
	 * public void setTextMemorySize(int textMemorySize) { this.textMemorySize =
	 * textMemorySize; }
	 * 
	 * public String getSeed() { return seed; }
	 * 
	 * public void setSeed(String seed) { this.seed = seed; }
	 * 
	 * public int getContextSwitchOverhead() { return contextSwitchOverhead; }
	 * 
	 * public void setContextSwitchOverhead(int contextSwitchOverhead) {
	 * this.contextSwitchOverhead = contextSwitchOverhead; }
	 */

}

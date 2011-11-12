package Controller.SchedulingSystem;

import java.util.Hashtable;
import java.util.Vector;

import Controller.SchedulingAlgorithmSystem.SchedulingAlgorithm;
import Controller.SchedulingAlgorithmSystem.FCFS;

public class Actor extends Resource {

	// Capacity or efficiency
	private int capacity;

	private String currAction;
	private Task currTask;

	private Vector<Task> intList;
	private Vector<Task> syncIntList;
	private SchedulingAlgorithm saIntList;

	private Vector<Task> readyList;
	private Vector<Task> syncReadyList;
	private SchedulingAlgorithm saReadyList;

	private int time;
	private int limitTime;
	private int maxTasksNumber;

	// Resta setear el maximo de tareas cuando se crea el vector, en el vector.
	public Actor(String resId, SchedulingAlgorithm saReadyList, int limitTime,
			SchedulingSystem schedulingSystem, int capacity, int maxTaskNumber,
			Hashtable<String, String> properties, int maxRelations,
			Vector<String> relationsIds) {

		super(resId, properties, maxRelations, relationsIds, schedulingSystem);

		this.setCurrAction("Nothing");
		this.setCurrTask(null);

		this.setMaxTasksNumber(maxTaskNumber);
		this.setCapacity(capacity);

		Vector<Task> syncIntList = new Vector<Task>();
		this.setSyncIntList(syncIntList);
		Vector<Task> intList = new Vector<Task>();
		this.setIntList(intList);

		FCFS saIntList = new FCFS();
		this.setSaIntList(saIntList);

		Vector<Task> syncReadyList = new Vector<Task>();
		this.setSyncReadyList(syncReadyList);
		Vector<Task> readylist = new Vector<Task>();
		this.setReadyList(readylist);
		this.setSaReadyList(saReadyList);

		this.setTime(0);
		this.setLimitTime(limitTime);
	}

	public void exec() {
		String currAction = this.getCurrAction();
		Task auxTask = null;
		Task currTask = this.getCurrTask();

		Vector<Task> intList = this.getIntList();
		SchedulingAlgorithm saIntList = this.getSaIntList();

		Vector<Task> readyList = this.getReadyList();
		SchedulingAlgorithm saReadyList = this.getSaReadyList();

		int time = this.getTime();
		int limitTime = this.getLimitTime();

		String currResId = this.getResId();

		if (currTask == null) { // Si no hay un proceso activo
			if (time == limitTime) { // Si el temporizador ha terminado:
				this.resetTime(); // Se reinicia el temporizador.
				currAction = "Time is up";
			} else if (intList.size() > 0) {// Sino, si la lista de
											// interrupciones no está vacía:
				auxTask = saIntList.schedule(intList); // Se ejecuta el
														// algoritmo de
														// planeamiento de
														// interrupciones para
														// elegir un proceso.
				intList.remove(auxTask); // Se elimina dicho proceso de la lista
											// de interrupciones.
				currTask = auxTask; // Se ejecuta dicho proceso (pasa a estado
									// activo).
				String taskId = currTask.getTaskId();
				currAction = "Select an interruption from the interruption list and put that interruption as active. The selected interruption is "
						+ taskId;
			} else if (readyList.size() > 0) { // Sino, si la lista de listos no
												// está vacía:
				auxTask = saReadyList.schedule(readyList); // Se ejecuta el
															// algoritmo de
															// planeamiento para
															// elegir un proceso
															// de la lista de
															// listos.
				readyList.remove(auxTask); // Se elimina dicho proceso de la
											// lista de listos.
				currTask = auxTask; // Se ejecuta dicho proceso (pasa a estado
									// activo).
				String taskId = currTask.getTaskId();
				currAction = "Select a task from the ready list and put that task as active. The selected task is "
						+ taskId;
			} else
				// Sino:
				currAction = "None"; // No hacer nada
		} else { // Sino hay un proceso activo
			if (time == limitTime) { // Si el temporizador ha terminado:
				currAction = "Time is up";
				String workUnit = currTask.getNext(this); // Se obtiene la
															// unidad
															// computacional
															// (que informa cuál
															// es el próximo
															// dispositivo en el
															// que se ejecutará
															// el proceso)
				if (!workUnit.contains("int")) { // Si el proceso en ejecución
													// no es una interrupción:
					this.addReadyList(currTask, currResId);// Se lo anexa a la
															// lista de listos
															// (pasa a estado
															// listo).
					String taskId = currTask.getTaskId();
					currAction += ", the active task " + taskId
							+ " pass to the ready list";
					currTask = null; // Se desaloja el proceso en ejecución.
				}
				this.resetTime(); // Se reinicia el temporizador.
			} else if (intList.size() > 0) {// Sino, si la lista de
											// interrupciones no está vacía:
				auxTask = saIntList.schedule(intList); // Se ejecuta el
														// algoritmo de
														// planeamiento de
														// interrupciones para
														// elegir un proceso.
				if (!currTask.getNext(this).contains("int")
						|| auxTask.getPriority() > currTask.getPriority()) {
					intList.remove(auxTask); // Se elimina dicho proceso de la
												// lista de interrupciones.
					// Se agrega el proceso actual a la lista de listos o a la
					// lista de interrupciones dependiendo de lo que sea
					if (currTask.getCurrent().contains("int")) {
						this.addIntList(currTask, currResId);
						currAction = "Old active task is put in the interruption list.";
					} else {
						this.addReadyList(currTask, currResId);
						currAction = "Old active task is put in the ready list.";
					}
					currTask = auxTask; // Se ejecuta dicho proceso (pasa a
										// estado activo).
					String taskId = currTask.getTaskId();
					currAction = "Select an interruption from the interruption list and put that interruption as active."
							+ " The selected interruption is "
							+ taskId
							+ ". "
							+ currAction;
				} else {
					exec2();
					currAction = this.getCurrAction();
					currTask = this.getCurrTask();
				}
			} else {
				exec2();
				currAction = this.getCurrAction();
				currTask = this.getCurrTask();
			}
		}
		this.setCurrAction(currAction);
		this.setCurrTask(currTask);
		this.setIntList(intList);
		this.setReadyList(readyList);
	}

	private void exec2() {
		String currAction = "";
		String resId = this.getResId();
		Task currTask = this.getCurrTask();
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		String workUnit = currTask.getNext(this); // Se obtiene la unidad
													// computacional (que
													// informa cuál es el
													// próximo dispositivo en el
													// que se ejecutará el
													// proceso)

		if (workUnit.equals("end")) { // Sino, si el proceso en ejecución ha
										// llegado a su fin:
			schedulingSystem.finishTask(currTask); // Terminar proceso (pasa a
													// estado finalizado).
			String taskId = currTask.getTaskId();
			currAction = "The active task " + taskId + " ends";
			currTask = null;
		} else if (workUnit.contains(resId)) { // Sino, si el proceso en
												// ejecución debe continuar
												// ejecutándose en este
												// dispositivo:
			currTask.exec(); // Ejecutar proceso.
			currAction = "Procesing active task " + currTask.getTaskId();
		} else if (workUnit.contains("int")) { // Si es una interrupción:
			currTask.decProgramCounter();
			workUnit = workUnit.replace("int_", "");
			this.addIntList(currTask, workUnit);// Se lo anexa a la lista de
												// interrupciones del
												// dispositivo indicado (el
												// proceso pasa a ser una
												// interrupción y pasa a estado
												// de espera).
			String taskId = currTask.getTaskId();
			currAction = "The task " + taskId
					+ " pass to the interruption list of the resource "
					+ workUnit;
			currTask = null; // Se desaloja el proceso en ejecución.
		} else { // Sino, si el proceso en ejecución debe continuar ejecutándose
					// en otro dispositivo:
			currTask.decProgramCounter();
			this.addReadyList(currTask, workUnit); // Se lo anexa a la lista de
													// listos del recurso
													// indicado (pasa a estado
													// de espera).
			String taskId = currTask.getTaskId();
			currAction = "The task " + taskId
					+ " pass to the ready list of the resource " + workUnit;
			currTask = null; // Se desaloja el proceso en ejecución.
		}

		this.setCurrAction(currAction);
		this.setCurrTask(currTask);
	}

	public void incTime() {
		int time = this.getTime();
		time++;
		this.setTime(time);
		this.syncLists();
	}

	private void resetTime() {
		int time = this.getTime();
		time = 0;
		this.setTime(time);
	}

	private void syncLists() {
		Vector<Task> syncIntList = this.getSyncIntList();
		Vector<Task> intList = this.getIntList();
		Vector<Task> syncReadyList = this.getSyncReadyList();
		Vector<Task> readyList = this.getReadyList();

		intList.addAll(syncIntList);
		syncIntList.removeAllElements();
		readyList.addAll(syncReadyList);
		syncReadyList.removeAllElements();

		this.setSyncIntList(syncIntList);
		this.setIntList(intList);
		this.setSyncReadyList(syncReadyList);
		this.setReadyList(readyList);
	}

	public void addIntList(Task currTask, String workUnit) {
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		Actor actor = schedulingSystem.getResource(workUnit);
		Vector<Task> syncIntList = actor.getSyncIntList();
		if (this.checkListMaxSize(this.getMaxTasksNumber(), actor.getIntList(),
				syncIntList))
			syncIntList.add(currTask);
		else {
			String errorMsg = "List size greater than allowed. Task "
					+ currTask.getTaskId() + " can't be added to "
					+ this.getResId();
			this.getSchedulingSystem().getLogginSystem().addErrorMsg(errorMsg);
		}
	}

	public void addReadyList(Task currTask, String workUnit) {
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		Actor actor = schedulingSystem.getResource(workUnit);
		Vector<Task> syncReadyList = actor.getSyncReadyList();
		if (this.checkListMaxSize(this.getMaxTasksNumber(),
				actor.getReadyList(), syncReadyList))
			syncReadyList.add(currTask);
		else {
			String errorMsg = "List size greater than allowed. Task "
					+ currTask.getTaskId() + " can't be added to "
					+ this.getResId();
			this.getSchedulingSystem().getLogginSystem().addErrorMsg(errorMsg);
		}
	}

	public String getCurrAction() {
		return currAction;
	}

	public void setCurrAction(String currAction) {
		this.currAction = currAction;
	}

	public Task getCurrTask() {
		return currTask;
	}

	public void setCurrTask(Task currTask) {
		this.currTask = currTask;
	}

	public Vector<Task> getIntList() {
		return intList;
	}

	public void setIntList(Vector<Task> intList) {
		if (checkListMaxSize(this.getMaxTasksNumber(), intList,
				this.getSyncIntList()))
			this.intList = intList;
		else {
			String errorMsg = "List size greater than allowed. Interruption list of "
					+ this.getResId() + " can't be inicialized.";
			this.getSchedulingSystem().getLogginSystem().addErrorMsg(errorMsg);
		}
	}

	public SchedulingAlgorithm getSaIntList() {
		return saIntList;
	}

	public void setSaIntList(SchedulingAlgorithm saIntList) {
		this.saIntList = saIntList;
	}

	public Vector<Task> getReadyList() {
		return readyList;
	}

	public void setReadyList(Vector<Task> readyList) {
		if (checkListMaxSize(this.getMaxTasksNumber(), readyList,
				this.getSyncIntList()))
			this.readyList = readyList;
		else {
			String errorMsg = "List size greater than allowed. Ready list of "
					+ this.getResId() + " can't be inicialized.";
			this.getSchedulingSystem().getLogginSystem().addErrorMsg(errorMsg);
		}
	}

	public SchedulingAlgorithm getSaReadyList() {
		return saReadyList;
	}

	public void setSaReadyList(SchedulingAlgorithm saReadyList) {
		this.saReadyList = saReadyList;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}

	public Vector<Task> getSyncIntList() {
		return syncIntList;
	}

	public void setSyncIntList(Vector<Task> syncIntList) {
		this.syncIntList = syncIntList;
	}

	public Vector<Task> getSyncReadyList() {
		return syncReadyList;
	}

	public void setSyncReadyList(Vector<Task> syncReadyList) {
		this.syncReadyList = syncReadyList;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean checkListMaxSize(int currentSize, Vector<Task> listX,
			Vector<Task> listY) {
		if (currentSize > (listX.size() + listY.size()))
			return true;
		else
			return false;
	}

	public void print() {
		super.print();

		System.out.println("Capacity:" + this.getCapacity());
		System.out.println("Limit Time:" + this.getLimitTime());
		System.out.println("Max Tasks Number:" + this.getMaxTasksNumber());

		System.out.println("Ready List Scheduling Algorithm:"
				+ this.getSaReadyList().getId());
		System.out.println("Ready List:");
		Vector<Task> readyList = this.getReadyList();
		int n = readyList.size();
		for (int i = 0; i < n; i++)
			System.out.println("\t" + readyList.elementAt(i).getTaskId());

		System.out.println("Interruption List Scheduling Algorithm:"
				+ this.getSaIntList().getId());
		System.out.println("Interruption List:");
		Vector<Task> intList = this.getReadyList();
		n = intList.size();
		for (int i = 0; i < n; i++)
			System.out.println("\t" + intList.elementAt(i).getTaskId());

	}

	public int getMaxTasksNumber() {
		return maxTasksNumber;
	}

	public void setMaxTasksNumber(int maxTaskNumber) {
		this.maxTasksNumber = maxTaskNumber;
	}

}

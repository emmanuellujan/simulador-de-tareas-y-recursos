package Controller.SchedulingSystem;
import java.util.Vector;

import Controller.SchedulingAlgorithmSystem.SchedulingAlgorithm;
import Controller.SchedulingAlgorithmSystem.FCFS;

public class Actor extends Resource{
	
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

	private SchedulingSystem schedulingSystem;

	public Actor(String resId, SchedulingAlgorithm saReadyList, int limitTime, SchedulingSystem schedulingSystem) {

		super(resId);
		this.setCurrAction("Nothing");
		this.setCurrTask(null);

		Vector<Task> intList = new Vector<Task>();
		this.setIntList(intList);
		Vector<Task> syncIntList = new Vector<Task>();
		this.setSyncIntList(syncIntList);
		FCFS saIntList = new FCFS();
		this.setSaIntList(saIntList);

		Vector<Task> readylist = new Vector<Task>();
		this.setReadyList(readylist);
		Vector<Task> syncReadyList = new Vector<Task>();
		this.setSyncReadyList(syncReadyList);
		this.setSaReadyList(saReadyList);

		this.setTime(0);
		this.setLimitTime(limitTime);

		this.setSchedulingSystem(schedulingSystem);
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
		
		if(currTask==null){ // Si no hay un proceso activo
			if(time==limitTime){ // Si el temporizador ha terminado:
				this.resetTime(); // Se reinicia el temporizador.
				currAction = "Time is up";
			}else if(intList.size()>0){// Sino, si la lista de interrupciones no está vacía:
				auxTask = saIntList.schedule(intList); // Se ejecuta el algoritmo de planeamiento de interrupciones para elegir un proceso.				
				intList.remove(auxTask); // Se elimina dicho proceso de la lista de interrupciones.
				currTask = auxTask; // Se ejecuta dicho proceso (pasa a estado activo).
				String taskId = currTask.getTaskId();
				currAction = "Select an interruption from the interruption list and put that interruption as active. The selected interruption is "+taskId;
			}else if(readyList.size()>0){ // Sino, si la lista de listos no está vacía:
					auxTask = saReadyList.schedule(readyList); // Se ejecuta el algoritmo de planeamiento para elegir un proceso de la lista de listos.
					readyList.remove(auxTask); // Se elimina dicho proceso de la lista de listos.
					currTask = auxTask; // Se ejecuta dicho proceso (pasa a estado activo).
					String taskId = currTask.getTaskId();
					currAction = "Select a task from the ready list and put that task as active. The selected task is "+taskId;
			}else // Sino:
					currAction = "None"; // No hacer nada
		}else{ // Sino hay un proceso activo
			if(time==limitTime){ // Si el temporizador ha terminado:
				currAction = "Time is up";
				String compUnit = currTask.getNext(this); // Se obtiene la unidad computacional (que informa cuál es el próximo dispositivo en el que se ejecutará el proceso)
				if(!compUnit.contains("int")){ // Si el proceso en ejecución no es una interrupción:
					this.addReadyList(currTask,currResId);// Se lo anexa a la lista de listos (pasa a estado listo).
					String taskId = currTask.getTaskId();
					currAction += ", the active task "+taskId+" pass to the ready list";
					currTask = null; // Se desaloja el proceso en ejecución.
				}
				this.resetTime(); // Se reinicia el temporizador.
			}else if(intList.size()>0){// Sino, si la lista de interrupciones no está vacía:
				auxTask = saIntList.schedule(intList); // Se ejecuta el algoritmo de planeamiento de interrupciones para elegir un proceso.
				if(!currTask.getNext(this).contains("int") || auxTask.getPriority()>currTask.getPriority()){
					intList.remove(auxTask); // Se elimina dicho proceso de la lista de interrupciones.
					//Se agrega el proceso actual a la lista de listos o a la lista de interrupciones dependiendo de lo que sea
					if(currTask.getCurrent().contains("int")){
						this.addIntList(currTask,currResId);
						currAction = "Old active task is put in the interruption list.";
					}else{
						this.addReadyList(currTask,currResId);
						currAction = "Old active task is put in the ready list.";
					}
					currTask = auxTask; // Se ejecuta dicho proceso (pasa a estado activo).
					String taskId = currTask.getTaskId();
					currAction = "Select an interruption from the interruption list and put that interruption as active." +
							" The selected interruption is "+taskId+". "+currAction;
				}else{
					exec2();
					currAction = this.getCurrAction();
					currTask = this.getCurrTask();
				}
			}else{
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
		String currAction="";
		String resId = this.getResId();
		Task currTask = this.getCurrTask();
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		String compUnit = currTask.getNext(this); // Se obtiene la unidad computacional (que informa cuál es el próximo dispositivo en el que se ejecutará el proceso)
		
		if(compUnit.equals("end")){ // Sino, si el proceso en ejecución ha llegado a su fin:
			schedulingSystem.finishTask(currTask); // Terminar proceso (pasa a estado finalizado).
			String taskId = currTask.getTaskId();
			currAction = "The active task "+taskId+" ends";
			currTask=null;
		}else if(compUnit.contains(resId)){ // Sino, si el proceso en ejecución debe continuar ejecutándose en este dispositivo:
			currTask.exec(); // Ejecutar proceso.
			currAction = "Procesing active task "+ currTask.getTaskId();
		}else if(compUnit.contains("int")){ // Si es una interrupción:
			currTask.decProgramCounter();
			compUnit = compUnit.replace("int_", "");
			this.addIntList(currTask,compUnit);// Se lo anexa a la lista de interrupciones del dispositivo indicado (el proceso pasa a ser una interrupción y pasa a estado de espera).
			String taskId = currTask.getTaskId();
			currAction = "The task "+taskId+" pass to the interruption list of the resource "+compUnit;
			currTask=null; // Se desaloja el proceso en ejecución.
		}else{ // Sino, si el proceso en ejecución debe continuar ejecutándose en otro dispositivo:
			currTask.decProgramCounter();
			this.addReadyList(currTask,compUnit); // Se lo anexa a la lista de listos del recurso indicado (pasa a estado de espera).
			String taskId = currTask.getTaskId();
			currAction = "The task "+taskId+" pass to the ready list of the resource "+compUnit;
			currTask=null;  // Se desaloja el proceso en ejecución.
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
		time=0;
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

	public void addIntList(Task currTask, String compUnit) {
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		Actor actor = schedulingSystem.getResource(compUnit);
		Vector<Task> syncIntList = actor.getSyncIntList();
		syncIntList.add(currTask);
	}

	public void addReadyList(Task currTask, String compUnit) {
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		Actor actor = schedulingSystem.getResource(compUnit);
		Vector<Task> syncReadyList = actor.getSyncReadyList();
		syncReadyList.add(currTask);
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
		this.intList = intList;
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
		this.readyList = readyList;
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

	public SchedulingSystem getSchedulingSystem() {
		return schedulingSystem;
	}

	public void setSchedulingSystem(SchedulingSystem schedulingSystem) {
		this.schedulingSystem = schedulingSystem;
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

}

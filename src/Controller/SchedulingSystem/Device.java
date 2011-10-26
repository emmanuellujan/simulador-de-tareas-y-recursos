package Controller.SchedulingSystem;
import java.util.Vector;

import Controller.SchedulingAlgorithmSystem.SchedulingAlgorithm;
import Controller.SchedulingAlgorithmSystem.FCFS;

public class Device {
	private String devId;
	private String currAction;
	private Process currProcess;

	private Vector<Process> intList;
	private Vector<Process> syncIntList;
	private SchedulingAlgorithm saIntList;

	private Vector<Process> readyList;
	private Vector<Process> syncReadyList;
	private SchedulingAlgorithm saReadyList;

	private int time;
	private int limitTime;

	private SchedulingSystem schedulingSystem;

	public Device(String devId, SchedulingAlgorithm saReadyList, int limitTime, SchedulingSystem schedulingSystem) {

		this.setDevId(devId);
		this.setCurrAction("Nothing");
		this.setCurrProcess(null);

		Vector<Process> intList = new Vector<Process>();
		this.setIntList(intList);
		Vector<Process> syncIntList = new Vector<Process>();
		this.setSyncIntList(syncIntList);
		FCFS saIntList = new FCFS();
		this.setSaIntList(saIntList);

		Vector<Process> readylist = new Vector<Process>();
		this.setReadyList(readylist);
		Vector<Process> syncReadyList = new Vector<Process>();
		this.setSyncReadyList(syncReadyList);
		this.setSaReadyList(saReadyList);

		this.setTime(0);
		this.setLimitTime(limitTime);

		this.setSchedulingSystem(schedulingSystem);
	}

	public void exec() {
		String currAction = this.getCurrAction();
		Process auxProcess = null;
		Process currProcess = this.getCurrProcess();

		Vector<Process> intList = this.getIntList();
		SchedulingAlgorithm saIntList = this.getSaIntList();

		Vector<Process> readyList = this.getReadyList();
		SchedulingAlgorithm saReadyList = this.getSaReadyList();

		int time = this.getTime();
		int limitTime = this.getLimitTime();

		String currDevId = this.getDevId();
		
		if(currProcess==null){ // Si no hay un proceso activo
			if(time==limitTime){ // Si el temporizador ha terminado:
				this.resetTime(); // Se reinicia el temporizador.
				currAction = "Time is up";
			}else if(intList.size()>0){// Sino, si la lista de interrupciones no está vacía:
				auxProcess = saIntList.schedule(intList); // Se ejecuta el algoritmo de planeamiento de interrupciones para elegir un proceso.				
				intList.remove(auxProcess); // Se elimina dicho proceso de la lista de interrupciones.
				currProcess = auxProcess; // Se ejecuta dicho proceso (pasa a estado activo).
				String processId = currProcess.getProcessId();
				currAction = "Select an interruption from the interruption list and put that interruption as active. The selected interruption is "+processId;
			}else if(readyList.size()>0){ // Sino, si la lista de listos no está vacía:
					auxProcess = saReadyList.schedule(readyList); // Se ejecuta el algoritmo de planeamiento para elegir un proceso de la lista de listos.
					readyList.remove(auxProcess); // Se elimina dicho proceso de la lista de listos.
					currProcess = auxProcess; // Se ejecuta dicho proceso (pasa a estado activo).
					String processId = currProcess.getProcessId();
					currAction = "Select a process from the ready list and put that process as active. The selected process is "+processId;
			}else // Sino:
					currAction = "None"; // No hacer nada
		}else{ // Sino hay un proceso activo
			if(time==limitTime){ // Si el temporizador ha terminado:
				currAction = "Time is up";
				String compUnit = currProcess.getNext(); // Se obtiene la unidad computacional (que informa cuál es el próximo dispositivo en el que se ejecutará el proceso)
				if(!compUnit.contains("int")){ // Si el proceso en ejecución no es una interrupción:
					this.addReadyList(currProcess,currDevId);// Se lo anexa a la lista de listos (pasa a estado listo).
					String processId = currProcess.getProcessId();
					currAction += ", the active process "+processId+" pass to the ready list";
					currProcess = null; // Se desaloja el proceso en ejecución.
				}
				this.resetTime(); // Se reinicia el temporizador.
			}else if(intList.size()>0){// Sino, si la lista de interrupciones no está vacía:
				auxProcess = saIntList.schedule(intList); // Se ejecuta el algoritmo de planeamiento de interrupciones para elegir un proceso.
				if(!currProcess.getNext().contains("int") || auxProcess.getPriority()>currProcess.getPriority()){
					intList.remove(auxProcess); // Se elimina dicho proceso de la lista de interrupciones.
					//Se agrega el proceso actual a la lista de listos o a la lista de interrupciones dependiendo de lo que sea
					if(currProcess.getCurrent().contains("int")){
						this.addIntList(currProcess,currDevId);
						currAction = "Old active process is put in the interruption list.";
					}else{
						this.addReadyList(currProcess,currDevId);
						currAction = "Old active process is put in the ready list.";
					}
					currProcess = auxProcess; // Se ejecuta dicho proceso (pasa a estado activo).
					String processId = currProcess.getProcessId();
					currAction = "Select an interruption from the interruption list and put that interruption as active." +
							" The selected interruption is "+processId+". "+currAction;
				}else{
					exec2();
					currAction = this.getCurrAction();
					currProcess = this.getCurrProcess();
				}
			}else{
				exec2();
				currAction = this.getCurrAction();
				currProcess = this.getCurrProcess();
			}
		}
		this.setCurrAction(currAction);
		this.setCurrProcess(currProcess);
		this.setIntList(intList);
		this.setReadyList(readyList);	
	}
	
	private void exec2() {
		String currAction="";
		String devId = this.getDevId();
		Process currProcess = this.getCurrProcess();
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		String compUnit = currProcess.getNext(); // Se obtiene la unidad computacional (que informa cuál es el próximo dispositivo en el que se ejecutará el proceso)
		
		if(compUnit.equals("end")){ // Sino, si el proceso en ejecución ha llegado a su fin:
			schedulingSystem.finishProcess(currProcess); // Terminar proceso (pasa a estado finalizado).
			String processId = currProcess.getProcessId();
			currAction = "The active process "+processId+" ends";
			currProcess=null;
		}else if(compUnit.contains(devId)){ // Sino, si el proceso en ejecución debe continuar ejecutándose en este dispositivo:
			currProcess.exec(); // Ejecutar proceso.
			currAction = "Processing active process "+ currProcess.getProcessId();
		}else if(compUnit.contains("int")){ // Si es una interrupción:
			currProcess.decProgramCounter();
			compUnit = compUnit.replace("int_", "");
			this.addIntList(currProcess,compUnit);// Se lo anexa a la lista de interrupciones del dispositivo indicado (el proceso pasa a ser una interrupción y pasa a estado de espera).
			String processId = currProcess.getProcessId();
			currAction = "The process "+processId+" pass to the interruption list of the device "+compUnit;
			currProcess=null; // Se desaloja el proceso en ejecución.
		}else{ // Sino, si el proceso en ejecución debe continuar ejecutándose en otro dispositivo:
			currProcess.decProgramCounter();
			this.addReadyList(currProcess,compUnit); // Se lo anexa a la lista de listos del recurso indicado (pasa a estado de espera).
			String processId = currProcess.getProcessId();
			currAction = "The process "+processId+" pass to the ready list of the device "+compUnit;
			currProcess=null;  // Se desaloja el proceso en ejecución.
		}

		this.setCurrAction(currAction);
		this.setCurrProcess(currProcess);
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
		Vector<Process> syncIntList = this.getSyncIntList();
		Vector<Process> intList = this.getIntList();
		Vector<Process> syncReadyList = this.getSyncReadyList();
		Vector<Process> readyList = this.getReadyList();

		intList.addAll(syncIntList);
		syncIntList.removeAllElements();
		readyList.addAll(syncReadyList);
		syncReadyList.removeAllElements();

		this.setSyncIntList(syncIntList);
		this.setIntList(intList);
		this.setSyncReadyList(syncReadyList);
		this.setReadyList(readyList);
	}

	public void addIntList(Process currProcess, String compUnit) {
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		Device device = schedulingSystem.getDevice(compUnit);
		Vector<Process> syncIntList = device.getSyncIntList();
		syncIntList.add(currProcess);
	}

	public void addReadyList(Process currProcess, String compUnit) {
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		Device device = schedulingSystem.getDevice(compUnit);
		Vector<Process> syncReadyList = device.getSyncReadyList();
		syncReadyList.add(currProcess);
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
	}

	public String getCurrAction() {
		return currAction;
	}

	public void setCurrAction(String currAction) {
		this.currAction = currAction;
	}

	public Process getCurrProcess() {
		return currProcess;
	}

	public void setCurrProcess(Process currProcess) {
		this.currProcess = currProcess;
	}

	public Vector<Process> getIntList() {
		return intList;
	}

	public void setIntList(Vector<Process> intList) {
		this.intList = intList;
	}

	public SchedulingAlgorithm getSaIntList() {
		return saIntList;
	}

	public void setSaIntList(SchedulingAlgorithm saIntList) {
		this.saIntList = saIntList;
	}

	public Vector<Process> getReadyList() {
		return readyList;
	}

	public void setReadyList(Vector<Process> readyList) {
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

	public Vector<Process> getSyncIntList() {
		return syncIntList;
	}

	public void setSyncIntList(Vector<Process> syncIntList) {
		this.syncIntList = syncIntList;
	}

	public Vector<Process> getSyncReadyList() {
		return syncReadyList;
	}

	public void setSyncReadyList(Vector<Process> syncReadyList) {
		this.syncReadyList = syncReadyList;
	}

}

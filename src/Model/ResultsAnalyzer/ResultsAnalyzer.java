package Model.ResultsAnalyzer;

import java.util.Vector;

import Controller.SchedulingSystem.SchedulingSystem;
import Model.DataModel.SimulationTime.SimulationTime;
import Model.LogginSystem.CompLogginSystem;

public class ResultsAnalyzer {
	private SchedulingSystem schedulingSystem;
	private int numberOfErrors;
	public int deadline; // desired number of cycles
	public int numberOfCycles; // actual number of cycles
	public int numberOfTasks;
	public int nbrFinishedTasks;
	public float propFinishedTasks;
	public float propFailedTasks;
	
	public int propVelocity;
	
	

	public ResultsAnalyzer(SchedulingSystem schedulingSystem){
		this.setSchedulingSystem(schedulingSystem);
	}
	
	public void analyze() {
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		CompLogginSystem logger = schedulingSystem.getCompLogginSystem();
		
		int numberOfErrors=0;
		if(logger.getErrorMsgs()!=null)
			numberOfErrors = logger.getErrorMsgs().size();
		this.setNumberOfErrors(numberOfErrors);
		
		this.setDeadline(schedulingSystem.getDeadline());
		
		Vector<SimulationTime> simulationTimes = logger.getSimulationTimes();
		this.setNumberOfCycles(simulationTimes.size());
		
		int numberOfTasks = schedulingSystem.getNumberOfTasks();
		this.setNumberOfTasks(numberOfTasks);
		
		int nbrFinishedTasks = schedulingSystem.getFinishedList().size();
		this.setNbrFinishedTasks(nbrFinishedTasks);
		
		float propFinishedTasks = nbrFinishedTasks / numberOfTasks;
		this.setPropFinishedTasks(propFinishedTasks);
		
		float propFailedTasks = 1 - propFinishedTasks;
		this.setPropFailedTasks(propFailedTasks);
	
	}
	
	public void print(){
		System.out.println("Results Analisys:");
		
		System.out.println("\tNumber of errors: "+this.getNumberOfErrors());
		
		System.out.println("\tDeadline: "+this.getDeadline());
		
		System.out.println("\tNumber of cycles: "+this.getNumberOfCycles());
		
		System.out.println("\tNumber of tasks: "+this.getNumberOfTasks());
		
		System.out.println("\tNumber of successful tasks: "+this.getNbrFinishedTasks());
		
		System.out.println("\tSuccessful tasks proportion: "+this.getPropFinishedTasks());
		
		System.out.println("\tFailed tasks proportion: "+this.getPropFailedTasks());
	}
	

	public SchedulingSystem getSchedulingSystem() {
		return schedulingSystem;
	}

	public void setSchedulingSystem(SchedulingSystem schedulingSystem) {
		this.schedulingSystem = schedulingSystem;
	}
	
	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public int getPropVelocity() {
		return propVelocity;
	}

	public void setPropVelocity(int propVelocity) {
		this.propVelocity = propVelocity;
	}

	public int getNumberOfErrors() {
		return numberOfErrors;
	}

	public void setNumberOfErrors(int numberOfErrors) {
		this.numberOfErrors = numberOfErrors;
	}

	public int getNumberOfCycles() {
		return numberOfCycles;
	}

	public void setNumberOfCycles(int numberOfCycles) {
		this.numberOfCycles = numberOfCycles;
	}

	public int getNbrFinishedTasks() {
		return nbrFinishedTasks;
	}

	public void setNbrFinishedTasks(int nbrFinishedTasks) {
		this.nbrFinishedTasks = nbrFinishedTasks;
	}

	public int getNumberOfTasks() {
		return numberOfTasks;
	}

	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}

	public float getPropFinishedTasks() {
		return propFinishedTasks;
	}

	public void setPropFinishedTasks(float propFinishedTasks) {
		this.propFinishedTasks = propFinishedTasks;
	}

	public float getPropFailedTasks() {
		return propFailedTasks;
	}

	public void setPropFailedTasks(float propFailedTasks) {
		this.propFailedTasks = propFailedTasks;
	}
	
}

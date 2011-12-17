package logicLayer.resultsAnalyzer;

import java.util.Vector;

import persistenceLayer.dataModel.SimulationTime.SimulationTime;
import persistenceLayer.logginSystem.CompLogginSystem;
import logicLayer.schedulingSystem.Actor;
import logicLayer.schedulingSystem.SchedulingSystem;

public class BasicAnalyzer extends ResultsAnalyzer{
	
	private int numberOfErrors;
	private int deadline; // desired number of cycles
	private int numberOfCycles; // actual number of cycles
	private int nbrExecTasks; // non cont. tasks, just tasks
	private int nbrExecContTasks;
	private int numberOfTasks; // number of all executed tasks
	private int nbrSuccessfulTasks;
	private int nbrFailedTasks;
	private int successfulExecTasks;
	private int successfulExecContTasks;
	private int failedExecTasks;
	private int failedExecContTasks;
	private int numberOfActors;
	private int numberOfResources;
	private float meanNbrTasksPerActor;
	private float propFinishedTasks;
	private float propFailedTasks;
	private float propVelocity;
	

	public BasicAnalyzer(SchedulingSystem schedulingSystem) {
		super(schedulingSystem);
	}
	
	public void analyze() {
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		CompLogginSystem logger = schedulingSystem.getLogger();

		// E = Number of errors:
		int numberOfErrors = 0;
		if (logger.getErrorMsgs() != null)
			numberOfErrors = logger.getErrorMsgs().size();
		this.setNumberOfErrors(numberOfErrors);

		// D = Deadline (in cycles)
		this.setDeadline(schedulingSystem.getDeadline());

		// C = Number of cycles:
		Vector<SimulationTime> simulationTimes = logger.getSimulationTimes();
		this.setNumberOfCycles(simulationTimes.size());

		// ET = Total number of executed tasks (including cont. tasks)
		int nbrExecTasks = schedulingSystem.getTasks().size();
		this.setNbrExecTasks(nbrExecTasks);

		// ECT = Total number of executed contingency tasks:
		int nbrExecContTasks = schedulingSystem.getLogger()
				.getNbrExecContTasks();
		this.setNbrExecContTasks(nbrExecContTasks);

		// TE = ET + ECT  = Total number of executed tasks
		int totalNbrExecTasks = nbrExecTasks + nbrExecContTasks;
		this.setNumberOfTasks(totalNbrExecTasks);		
		
		// ST = S(ET) + S(ECT) = Number of successful tasks (including cont. tasks)
		int nbrSuccessfulTasks = schedulingSystem.getLogger()
				.getSuccessfulFinishedTasks().size();
		this.setNbrSuccessfulTasks(nbrSuccessfulTasks);

		//  FT = F(ET) + F(ECT) = Number of failed tasks (including cont. tasks)
		int nbrFailedTasks = schedulingSystem.getLogger()
				.getFailedFinishedTasks().size();
		this.setNbrFailedTasks(nbrFailedTasks);

/*		float s1 = nbrSuccessfulTasks / (nbrSuccessfulTasks + nbrFailedTasks);
		float s2 = (-nbrSuccessfulTasks + nbrExecContTasks * s1)
				/ (nbrExecContTasks - nbrSuccessfulTasks - nbrFailedTasks);

		// S(ET) = Number of successful executed tasks
		int successfulExecTasks = (int) (s1 * nbrExecTasks);
		this.setSuccessfulExecTasks(successfulExecTasks);
		
		// S(ECT) Number of successful executed cont. tasks
		int successfulExecContTasks = (int) (s2 * nbrExecContTasks);
		this.setSuccessfulExecContTasks(successfulExecContTasks);
				
		// F(ET) Number of failed executed tasks
		int failedExecTasks = (int) ((1 - s1) * nbrExecTasks);
		this.setFailedExecTasks(failedExecTasks);
		
		// F(ECT) Number of failed executed cont. tasks
		int failedExecContTasks = (int) ((1 - s2) * nbrExecContTasks);
		this.setFailedExecContTasks(failedExecContTasks);
		
		// t si
		// tc no
		// t_e = t si
		// tc_e si
		// s = s(t_e + tc_e) = s(t_e) + s(tc_e) si
		// f = f(t_e + tc_e) = f(t_e) + f(tc_e) si
		// s(t_e) = s1 * t_e
		// s(tc_e) = s2 * tc_e
		// f(t_e) = (1-s1) * t_e
		// f(tc_e) = (1-s2) * tc_e

		// s = s1 t_e + s2 tc_e
		// f = (1-s1) t_e + (1-s2) tc_e
*/
		int numberOfActors = this.getSchedulingSystem().getActorsList().size();
		this.setNumberOfActors(numberOfActors);
		
		int numberOfResources = this.getSchedulingSystem().getResourcesList()
				.size();
		this.setNumberOfResources(numberOfResources);

		float meanNbrTasksPerActor = (float) numberOfTasks
				/ (float) numberOfActors;
		this.setMeanNbrTasksPerActor(meanNbrTasksPerActor);

		float propFinishedTasks = (float) nbrSuccessfulTasks
				/ (float) numberOfTasks;
		this.setPropFinishedTasks(propFinishedTasks);

		float propFailedTasks = (float) nbrFailedTasks / (float) numberOfTasks;
		this.setPropFailedTasks(propFailedTasks);

		float propVelocity = (float) this.getDeadline()
				/ (float) this.getNumberOfCycles();
		this.setPropVelocity(propVelocity);

		this.renderAnalysis();
	}

	
	public void renderAnalysis() {
		String analysis = "";

		analysis += "Results Analisys \n\n";

		analysis += "  E = Number of errors: " + this.getNumberOfErrors() + "\n";

		analysis += "  D = Deadline (in cycles): " + this.getDeadline() + "\n";

		analysis += "  C = Number of cycles: " + this.getNumberOfCycles() + "\n";

		analysis += "  ET = Total number of executed tasks: "
				+ this.getNumberOfTasks() + "\n";
		
		analysis += "  ECT = Total number of executed contingency tasks: "
				+ this.getNbrExecContTasks() + "\n";
		
		analysis += "  TE = ET + ECT  = Total number of executed tasks: "
				+ this.getNbrExecTasks() + "\n";

		analysis += "  ST = S(ET) + S(ECT) = Number of successful tasks: "
				+ this.getNbrSuccessfulTasks() + "\n";
		
		analysis += "  FT = F(ET) + F(ECT) = Number of failed tasks: " + this.getNbrFailedTasks()
				+ "\n";
		
		/*
		analysis += "  S(ET) = Number of successful executed tasks: "
				+ this.getSuccessfulExecTasks() + "\n";

		analysis += "  S(ECT) Number of successful executed cont. tasks: "
				+ this.getSuccessfulExecContTasks() + "\n";
		
		analysis += "  F(ET) Number of failed executed tasks: "
				+ this.getFailedExecTasks() + "\n";

		analysis += "  F(ECT) Number of failed executed cont. tasks: "
				+ this.getFailedExecContTasks() + "\n"; 
		 
		*/

		analysis += "  A = Number of actors: " + this.getNumberOfActors() + "\n";
		
		Vector<Actor> actors = this.getSchedulingSystem().getActorsList();
		int n = actors.size();
		for(int i=0;i<n;i++){
			analysis += "    Actor: "+actors.get(i).getResId()+", busy time: "
					+ actors.get(i).getBusyTime() + "\n";
		}
		
		analysis += "  R = Number of other resources (artifacts): "
				+ this.getNumberOfResources() + "\n";

		analysis += "  TPA = Mean number of tasks per actor: "
				+ this.getMeanNbrTasksPerActor() + "\n";

		analysis += "  Proportions: \n";

		analysis += "    Successful tasks: " + this.getPropFinishedTasks()
				+ "\n";

		analysis += "    Failed tasks: " + this.getPropFailedTasks() + "\n";

		analysis += "    Velocity (deadline/nbrOfCycles): "
				+ this.getPropVelocity() + "\n";

		String aux = this.getSchedulingSystem().getLogger().renderErrors();

		if (aux != "")
			analysis += "\n\n  " + aux + "\n";

		this.setAnalysis(analysis);
	}



	public int getNumberOfErrors() {
		return numberOfErrors;
	}


	public void setNumberOfErrors(int numberOfErrors) {
		this.numberOfErrors = numberOfErrors;
	}


	public int getDeadline() {
		return deadline;
	}


	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}


	public int getNumberOfCycles() {
		return numberOfCycles;
	}


	public void setNumberOfCycles(int numberOfCycles) {
		this.numberOfCycles = numberOfCycles;
	}


	public int getNbrExecTasks() {
		return nbrExecTasks;
	}


	public void setNbrExecTasks(int nbrExecTasks) {
		this.nbrExecTasks = nbrExecTasks;
	}


	public int getNbrExecContTasks() {
		return nbrExecContTasks;
	}


	public void setNbrExecContTasks(int nbrExecContTasks) {
		this.nbrExecContTasks = nbrExecContTasks;
	}


	public int getNumberOfTasks() {
		return numberOfTasks;
	}


	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}


	public int getNbrSuccessfulTasks() {
		return nbrSuccessfulTasks;
	}


	public void setNbrSuccessfulTasks(int nbrSuccessfulTasks) {
		this.nbrSuccessfulTasks = nbrSuccessfulTasks;
	}


	public int getNbrFailedTasks() {
		return nbrFailedTasks;
	}


	public void setNbrFailedTasks(int nbrFailedTasks) {
		this.nbrFailedTasks = nbrFailedTasks;
	}


	public int getSuccessfulExecTasks() {
		return successfulExecTasks;
	}


	public void setSuccessfulExecTasks(int successfulExecTasks) {
		this.successfulExecTasks = successfulExecTasks;
	}


	public int getSuccessfulExecContTasks() {
		return successfulExecContTasks;
	}


	public void setSuccessfulExecContTasks(int successfulExecContTasks) {
		this.successfulExecContTasks = successfulExecContTasks;
	}


	public int getFailedExecTasks() {
		return failedExecTasks;
	}


	public void setFailedExecTasks(int failedExecTasks) {
		this.failedExecTasks = failedExecTasks;
	}


	public int getFailedExecContTasks() {
		return failedExecContTasks;
	}


	public void setFailedExecContTasks(int failedExecContTasks) {
		this.failedExecContTasks = failedExecContTasks;
	}


	public int getNumberOfActors() {
		return numberOfActors;
	}


	public void setNumberOfActors(int numberOfActors) {
		this.numberOfActors = numberOfActors;
	}


	public int getNumberOfResources() {
		return numberOfResources;
	}


	public void setNumberOfResources(int numberOfResources) {
		this.numberOfResources = numberOfResources;
	}


	public float getMeanNbrTasksPerActor() {
		return meanNbrTasksPerActor;
	}


	public void setMeanNbrTasksPerActor(float meanNbrTasksPerActor) {
		this.meanNbrTasksPerActor = meanNbrTasksPerActor;
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


	public float getPropVelocity() {
		return propVelocity;
	}


	public void setPropVelocity(float propVelocity) {
		this.propVelocity = propVelocity;
	}

}

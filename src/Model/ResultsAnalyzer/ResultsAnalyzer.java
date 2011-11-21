package Model.ResultsAnalyzer;

import java.util.Vector;

import Controller.SchedulingSystem.SchedulingSystem;
import Model.DataModel.SimulationTime.SimulationTime;
import Model.IOSystem.FileManager;
import Model.LogginSystem.CompLogginSystem;

public class ResultsAnalyzer {
	private SchedulingSystem schedulingSystem;
	private int numberOfErrors;
	public int deadline; // desired number of cycles
	public int numberOfCycles; // actual number of cycles
	public int numberOfTasks;
	public int nbrSuccessfulTasks;
	public int nbrFailedTasks;
	private int numberOfActors;
	private int numberOfResources;
	private float meanNbrTasksPerActor;
	public float propFinishedTasks;
	public float propFailedTasks;
	public float propVelocity;
	
	private String analysis;
	
	public ResultsAnalyzer(SchedulingSystem schedulingSystem){
		this.setSchedulingSystem(schedulingSystem);
		this.setAnalysis("");
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
		
		int nbrSuccessfulTasks = schedulingSystem.getFinishedList().size();
		this.setNbrSuccessfulTasks(nbrSuccessfulTasks);
		
		int nbrFailedTasks = schedulingSystem.getFailedFinishedList().size();
		this.setNbrFailedTasks(nbrFailedTasks);
		
		int numberOfTasks = nbrSuccessfulTasks+nbrFailedTasks;
		this.setNumberOfTasks(numberOfTasks);
		
		int numberOfActors = this.getSchedulingSystem().getActorsList().size();
		this.setNumberOfActors(numberOfActors);
		
		int numberOfResources = this.getSchedulingSystem().getResourcesList().size();
		this.setNumberOfResources(numberOfResources);
		
		float meanNbrTasksPerActor = (float) numberOfTasks / (float) numberOfActors;
		this.setMeanNbrTasksPerActor(meanNbrTasksPerActor);
		
		float propFinishedTasks = (float) nbrSuccessfulTasks / (float) numberOfTasks;
		this.setPropFinishedTasks(propFinishedTasks);
		
		float propFailedTasks = (float) nbrFailedTasks / (float) numberOfTasks;
		this.setPropFailedTasks(propFailedTasks);
		
		float propVelocity = (float) this.getDeadline() / (float) this.getNumberOfCycles();
		this.setPropVelocity(propVelocity);
		
		this.renderAnalysis();
	
	}
	
	public void renderAnalysis(){
		String analysis = this.getAnalysis();
		
		analysis+="Results Analisys:\n";
		
		analysis+="\tNumber of errors: "+this.getNumberOfErrors()+"\n";
		
		analysis+="\tDeadline (in cycles): "+this.getDeadline()+"\n";
		
		analysis+="\tNumber of cycles: "+this.getNumberOfCycles()+"\n";
		
		analysis+="\tTotal number of executed tasks, including contingency tasks: "+this.getNumberOfTasks()+"\n";
		
		analysis+="\tNumber of successful tasks, including contingency tasks: "+this.getNbrSuccessfulTasks()+"\n";
		
		analysis+="\tNumber of failed tasks: "+this.getNbrFailedTasks()+"\n";
		
		analysis+="\tNumber of actors: "+this.getNumberOfActors()+"\n";
		
		analysis+="\tNumber of other resources (artifacts): "+this.getNumberOfResources()+"\n";
		
		analysis+="\tMean number of tasks per actor: "+this.getMeanNbrTasksPerActor()+"\n";
		
		analysis+="\tProportions: \n";
		
		analysis+="\t\tSuccessful tasks proportion, including contingency tasks: "+this.getPropFinishedTasks()+"\n";
		
		analysis+="\t\tFailed tasks proportion, including contingency tasks: "+this.getPropFailedTasks()+"\n";
		
		analysis+="\t\tVelocity proportion (deadline/nbrOfCycles): "+this.getPropVelocity()+"\n";
		
		this.setAnalysis(analysis);
	}
	
	public void print() {
		System.out.println(this.getAnalysis());
	}
	
	public void writeAnalysis() {
		String analysis = this.getAnalysis();
		String dir = this.getSchedulingSystem().getConfigurator().getOutputDir();
		String outputFile = this.getSchedulingSystem().getConfigurator().getOutputFile()+"_analysis.txt";
		String fileName = dir + outputFile;
		FileManager fileManager = new FileManager();
		fileManager.writeFile(fileName, analysis);
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

	public void setPropVelocity(float propVelocity) {
		this.propVelocity = propVelocity;
	}
	
	public float getPropVelocity() {
		return this.propVelocity;
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

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	
}

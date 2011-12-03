import java.util.Vector;
import LogicLayer.SchedulingSystem.SchedulingSystem;
import PersistenceLayer.DataModel.SimulationTime.SimulationTime;
import PersistenceLayer.IOSystem.FileManager;
import PersistenceLayer.LogginSystem.CompLogginSystem;

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

	public ResultsAnalyzer(SchedulingSystem schedulingSystem) {
		this.setSchedulingSystem(schedulingSystem);
		this.setAnalysis("");
	}

	public void analyze() {
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		CompLogginSystem logger = schedulingSystem.getCompLogginSystem();

		int numberOfErrors = 0;
		if (logger.getErrorMsgs() != null)
			numberOfErrors = logger.getErrorMsgs().size();
		this.setNumberOfErrors(numberOfErrors);

		this.setDeadline(schedulingSystem.getDeadline());

		Vector<SimulationTime> simulationTimes = logger.getSimulationTimes();
		this.setNumberOfCycles(simulationTimes.size());

		int nbrSuccessfulTasks = schedulingSystem.getFinishedList().size();
		this.setNbrSuccessfulTasks(nbrSuccessfulTasks);

		int nbrFailedTasks = schedulingSystem.getFailedFinishedList().size();
		this.setNbrFailedTasks(nbrFailedTasks);

		int numberOfTasks = nbrSuccessfulTasks + nbrFailedTasks;
		this.setNumberOfTasks(numberOfTasks);

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

	public String getAnalysis() {
		return analysis;
	}

	public int getDeadline() {
		return deadline;
	}

	public float getMeanNbrTasksPerActor() {
		return meanNbrTasksPerActor;
	}

	public int getNbrFailedTasks() {
		return nbrFailedTasks;
	}

	public int getNbrSuccessfulTasks() {
		return nbrSuccessfulTasks;
	}

	public int getNumberOfActors() {
		return numberOfActors;
	}

	public int getNumberOfCycles() {
		return numberOfCycles;
	}

	public int getNumberOfErrors() {
		return numberOfErrors;
	}

	public int getNumberOfResources() {
		return numberOfResources;
	}

	public int getNumberOfTasks() {
		return numberOfTasks;
	}

	public float getPropFailedTasks() {
		return propFailedTasks;
	}

	public float getPropFinishedTasks() {
		return propFinishedTasks;
	}

	public float getPropVelocity() {
		return this.propVelocity;
	}

	public SchedulingSystem getSchedulingSystem() {
		return schedulingSystem;
	}

	public void print() {
		System.out.println(this.getAnalysis());
	}

	public void renderAnalysis() {
		String analysis = this.getAnalysis();

		analysis += "Results Analisys \n\n";

		analysis += "  Number of errors: " + this.getNumberOfErrors() + "\n";

		analysis += "  Deadline (in cycles): " + this.getDeadline() + "\n";

		analysis += "  Number of cycles: " + this.getNumberOfCycles() + "\n";

		analysis += "  Total number of executed tasks *: "
				+ this.getNumberOfTasks() + "\n";

		analysis += "  Number of successful tasks *: "
				+ this.getNbrSuccessfulTasks() + "\n";

		analysis += "  Number of failed tasks: " + this.getNbrFailedTasks()
				+ "\n";

		analysis += "  Number of actors: " + this.getNumberOfActors() + "\n";

		analysis += "  Number of other resources (artifacts): "
				+ this.getNumberOfResources() + "\n";

		analysis += "  Mean number of tasks per actor: "
				+ this.getMeanNbrTasksPerActor() + "\n";

		analysis += "  Proportions: \n";

		analysis += "    Successful tasks *: " + this.getPropFinishedTasks()
				+ "\n";

		analysis += "    Failed tasks *: " + this.getPropFailedTasks() + "\n";

		analysis += "    Velocity (deadline/nbrOfCycles): "
				+ this.getPropVelocity() + "\n";

		analysis += "\n  *: with contigency tasks\n";

		String aux = this.getSchedulingSystem().getCompLogginSystem()
				.renderErrors();

		if (aux != "")
			analysis += "\n\n  " + aux + "\n";

		this.setAnalysis(analysis);
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public void setMeanNbrTasksPerActor(float meanNbrTasksPerActor) {
		this.meanNbrTasksPerActor = meanNbrTasksPerActor;
	}

	public void setNbrFailedTasks(int nbrFailedTasks) {
		this.nbrFailedTasks = nbrFailedTasks;
	}

	public void setNbrSuccessfulTasks(int nbrSuccessfulTasks) {
		this.nbrSuccessfulTasks = nbrSuccessfulTasks;
	}

	public void setNumberOfActors(int numberOfActors) {
		this.numberOfActors = numberOfActors;
	}

	public void setNumberOfCycles(int numberOfCycles) {
		this.numberOfCycles = numberOfCycles;
	}

	public void setNumberOfErrors(int numberOfErrors) {
		this.numberOfErrors = numberOfErrors;
	}

	public void setNumberOfResources(int numberOfResources) {
		this.numberOfResources = numberOfResources;
	}

	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}

	public void setPropFailedTasks(float propFailedTasks) {
		this.propFailedTasks = propFailedTasks;
	}

	public void setPropFinishedTasks(float propFinishedTasks) {
		this.propFinishedTasks = propFinishedTasks;
	}

	public void setPropVelocity(float propVelocity) {
		this.propVelocity = propVelocity;
	}

	public void setSchedulingSystem(SchedulingSystem schedulingSystem) {
		this.schedulingSystem = schedulingSystem;
	}

	public void writeAnalysis() {
		String analysis = this.getAnalysis();
		String fileName = this.getSchedulingSystem().getConfigurator()
				.getOutputDir()
				+ this.getSchedulingSystem().getConfigurator().getProjectName()
				+ "_analysis.txt";
		FileManager fileManager = new FileManager();
		fileManager.writeFile(fileName, analysis);
	}

}

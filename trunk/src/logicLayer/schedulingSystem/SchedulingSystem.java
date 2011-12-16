package logicLayer.schedulingSystem;

import java.util.Vector;

import logicLayer.resultsAnalyzer.BasicAnalyzer;
import logicLayer.resultsAnalyzer.ResultsAnalyzer;
import logicLayer.schedulingAlgorithmSystem.FCFS;
import persistenceLayer.dataModel.Configurator.Configurator;
import persistenceLayer.ioSystem.IOSystem;
import persistenceLayer.ioSystem.SerialIOSystem;
import persistenceLayer.ioSystem.XMLIOSystem;
import persistenceLayer.logginSystem.CompLogginSystem;

public class SchedulingSystem {

	public static void main(String[] args) {
		SchedulingSystem schedulingSystem = new SchedulingSystem();
		schedulingSystem.start();
	}

	private Configurator configurator;
	private IOSystem ioSystem;
	private CompLogginSystem logger;
	private ResultsAnalyzer resultsAnalyzer;

	private Actor dealerActor;
	private int deadline;
	private Vector<Task> tasks;
	private Vector<Task> newsList;
	private Vector<Actor> actorsList;
	private Vector<Resource> resourcesList;

	public SchedulingSystem() {
		Configurator configurator = new Configurator();
		IOSystem ioSystem = new SerialIOSystem(configurator, this);
		CompLogginSystem logger = new CompLogginSystem(configurator);
		ResultsAnalyzer resultsAnalyzer = null;
		int deadline = 0;
		Vector<Task> newsList = new Vector<Task>();
		Vector<Task> tasks = new Vector<Task>();
		Vector<Actor> actorsList = new Vector<Actor>();
		Vector<Resource> resourcesList = new Vector<Resource>();

		String dealerActorId = "dealerActor";
		FCFS saReadyList = new FCFS();
		int limitTime = -1;
		Actor dealerActor = new Actor(dealerActorId, "actor", saReadyList,
				limitTime, this, 100, 100, null, 100, null, null);

		this.setConfigurator(configurator);
		this.setIoSystem(ioSystem);
		this.setDealerActor(dealerActor);
		this.setCompLogginSystem(logger);
		this.setResultsAnalyzer(resultsAnalyzer);
		this.setDeadline(deadline);
		this.setNewsList(newsList);
		this.setTasks(tasks);
		this.setActorsList(actorsList);
		this.setResourcesList(resourcesList);
	}

	public void finishFailedTask(Task currTask) {
		currTask.execPostProcessing();
		this.getLogger().getFailedFinishedTasks().add(currTask);
	}

	public void finishTask(Task currTask) {
		currTask.execPostProcessing();
		this.getLogger().getSuccessfulFinishedTasks().add(currTask);
	}

	public Vector<Actor> getActorsList() {
		return actorsList;
	}

	public Configurator getConfigurator() {
		return configurator;
	}

	public int getDeadline() {
		return deadline;
	}

	public Actor getDealerActor() {
		return dealerActor;
	}

	public IOSystem getIoSystem() {
		return ioSystem;
	}

	public CompLogginSystem getLogger() {
		return logger;
	}

	public Vector<Task> getNewsList() {
		return newsList;
	}

	public Actor getResource(String name) {
		Vector<Actor> actorsList = this.getActorsList();
		boolean found = false;
		int i = 0;
		int n = actorsList.size();
		Actor actor = null;
		while (i < n && !found) {
			if (actorsList.get(i).getResId().equals(name)) {
				found = true;
				actor = actorsList.get(i);
			} else
				i++;
		}
		return actor;
	}

	public Vector<Resource> getResourcesList() {
		return resourcesList;
	}

	public ResultsAnalyzer getResultsAnalyzer() {
		return resultsAnalyzer;
	}

	public Vector<Task> getTasks() {
		return tasks;
	}

	private void incTime() {
		Vector<Actor> actorsList = this.getActorsList();
		int n = actorsList.size();
		for (int i = 0; i < n; i++) {
			Actor actor = actorsList.get(i);
			actor.incTime();
		}
	}

	public void loadData() {

		IOSystem ioSystem = this.getIoSystem();
		int deadline = this.getDeadline();
		Vector<Task> newsList = new Vector<Task>();
		Vector<Task> tasks = new Vector<Task>();
		Vector<Actor> actorsList = new Vector<Actor>();
		Vector<Resource> resourcesList = new Vector<Resource>();
		Actor dealerActor = null;
		ResultsAnalyzer resultsAnalyzer = null;

		ioSystem.loadAll();
		deadline = ioSystem.getDeadline();
		tasks = ioSystem.getTasksList();
		newsList.addAll(tasks);
		actorsList = ioSystem.getActorsList();
		resourcesList = ioSystem.getResourcesList();
		dealerActor = this.getDealerActor();
		dealerActor.setReadyList(newsList);
		resultsAnalyzer = ioSystem.getResultsAnalyzer();
		
		this.setDeadline(deadline);
		this.setNewsList(newsList);
		this.setTasks(tasks);
		this.setActorsList(actorsList);
		this.setResourcesList(resourcesList);
		this.setDealerActor(dealerActor);
		this.setResultsAnalyzer(resultsAnalyzer);
	}

	public void loadData(String inputDir) {

		if (inputDir != null && !inputDir.equals("")) {
			String bar = this.getConfigurator().getBarFromPath(inputDir);
			this.getConfigurator().setInputDir(inputDir + bar);

			String[] s = inputDir.split(bar);
			String projectName = s[s.length - 1];
			this.getConfigurator().setProjectName(projectName);
		}

		this.loadData();
	}

	public void reset() {
		Vector<Task> tasks = this.getTasks();
		int n = tasks.size();
		for (int i = 0; i < n; i++)
			tasks.elementAt(i).reset();

		Vector<Resource> allResources = this.getIoSystem()
				.getAllResourcesList();
		n = allResources.size();
		for (int i = 0; i < n; i++)
			allResources.elementAt(i).reset();
		this.getDealerActor().reset();

		Vector<Task> readyList = new Vector<Task>();
		readyList.addAll(this.getTasks());
		this.getDealerActor().setReadyList(readyList);
		this.setNewsList(readyList);

		ResultsAnalyzer resultsAnalyzer = new BasicAnalyzer(this);
		CompLogginSystem logger = new CompLogginSystem(this.getConfigurator());

		this.setResultsAnalyzer(resultsAnalyzer);
		this.setCompLogginSystem(logger);
	}

	public void saveData() {
		this.getIoSystem().saveAll();
		this.getLogger().writeLog();
		this.getResultsAnalyzer().writeAnalysis();
	}

	public void saveData(String outputDir) {
		if (outputDir != null && !outputDir.equals("")) {
			String projectName = this.getConfigurator().getProjectName();
			this.saveData(projectName, outputDir);
		} else
			this.saveData();
	}

	public void saveData(String projectName, String outputDir) {
		String bar = this.getConfigurator().getSaveBarFromPath(outputDir);
		outputDir = outputDir + bar + projectName + bar;
		this.getConfigurator().setOutputDir(outputDir);
		this.saveData();
	}

	private boolean scheduleFinished() {
		Vector<Actor> actors = this.getActorsList();
		int i = 0;
		int n = actors.size();
		while (i < n && actors.elementAt(i).isInactive())
			i++;
		if (n == i)
			return true;
		else
			return false;
	}

	public void setActorsList(Vector<Actor> actorsList) {
		this.actorsList = actorsList;
	}

	public void setCompLogginSystem(CompLogginSystem compLogginSystem) {
		this.logger = compLogginSystem;
	}

	public void setConfigurator(Configurator configurator) {
		this.configurator = configurator;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public void setDealerActor(Actor dealerActor) {
		this.dealerActor = dealerActor;
	}

	public void setIoSystem(IOSystem ioSystem) {
		this.ioSystem = ioSystem;
	}

	public void setNewsList(Vector<Task> newsList) {
		this.newsList = newsList;
	}

	public void setResourcesList(Vector<Resource> resourcesList) {
		this.resourcesList = resourcesList;
	}

	public void setResultsAnalyzer(ResultsAnalyzer resultsAnalyzer) {
		this.resultsAnalyzer = resultsAnalyzer;
	}

	public void setTasks(Vector<Task> tasks) {
		this.tasks = tasks;
	}

	public void simulateAndLog() {
		this.reset();
		Vector<Actor> actorsList = getActorsList();
		CompLogginSystem logger = this.getLogger();
		Actor dealerActor = this.getDealerActor();
		actorsList.add(0, dealerActor);
		int i = 0;
		int n = actorsList.size();
		while (!scheduleFinished()) {
			for (int j = 0; j < n; j++)
				actorsList.get(j).exec();
			this.incTime();
			logger.log(i, actorsList);
			i++;
		}
		actorsList.remove(0);
	}

	public void start() {
		start(null, null);
	}

	public void start(String inputDir, String outputDir) {
		System.out.print("Loading data...");
		this.loadData(inputDir);
		System.out.println(" done.");
		System.out.print("Simulation started...");
		this.simulateAndLog();
		System.out.println(" done.");
		System.out.print("Analyzing results...");
		this.getResultsAnalyzer().analyze();
		System.out.println(" done.");
		System.out.print("Saving data...");
		this.saveData(outputDir);
		System.out.println(" done.\n");
		this.getResultsAnalyzer().print();
		System.out.println("Done!\n\n");
	}
	
	public void start2() {
		/* When a change is made in the application
		 * test cases likely will not work. Execute the application
		 * with start2 instead start and likely they will work.
		 * */
		IOSystem ioSystem = new XMLIOSystem(this.getConfigurator(), this);
		this.setIoSystem(ioSystem);
		start();
		SerialIOSystem serialIOSystem = new SerialIOSystem(this.getConfigurator(), this);
		serialIOSystem.saveAll();
	}	

}

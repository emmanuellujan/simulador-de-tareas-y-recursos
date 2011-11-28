package Controller.SchedulingSystem;

import java.util.Vector;

import Controller.SchedulingAlgorithmSystem.FCFS;
import Model.DataModel.Configurator.Configurator;
import Model.IOSystem.IOSystem;
import Model.IOSystem.SerialIOSystem;
import Model.IOSystem.XMLIOSystem;
import Model.LogginSystem.CompLogginSystem;
import Model.ResultsAnalyzer.ResultsAnalyzer;

public class SchedulingSystem {

	public static void main(String[] args) {
		SchedulingSystem schedulingSystem = new SchedulingSystem();
		schedulingSystem.start();
	}

	private Configurator configurator;
	private IOSystem ioSystem;
	private CompLogginSystem compLogginSystem;

	private ResultsAnalyzer resultsAnalyzer;
	private int numberOfTasks;
	private Actor dealerActor;
	private int deadline;
	private Vector<Task> tasks;
	private Vector<Task> newsList;
	private Vector<Actor> actorsList;
	private Vector<Resource> resourcesList;
	private Vector<Task> finishedList;

	private Vector<Task> failedFinishedList;

	public SchedulingSystem() {
		Configurator configurator = new Configurator();
		//IOSystem ioSystem = new XMLIOSystem(configurator, this);
		IOSystem ioSystem = new SerialIOSystem(configurator, this);
		CompLogginSystem compLogginSystem = new CompLogginSystem(configurator);
		ResultsAnalyzer resultsAnalyzer = new ResultsAnalyzer(this);
		int deadline = 0;
		Vector<Task> newsList = new Vector<Task>();
		Vector<Task> tasks = new Vector<Task>();
		Vector<Actor> actorsList = new Vector<Actor>();
		Vector<Resource> resourcesList = new Vector<Resource>();
		Vector<Task> finishedList = new Vector<Task>();
		Vector<Task> failedFinishedList = new Vector<Task>();

		String dealerActorId = "dealerActor";
		FCFS saReadyList = new FCFS();
		int limitTime = -1;
		Actor dealerActor = new Actor(dealerActorId, "actor", saReadyList,
				limitTime, this, 100, 100, null, 100, null);

		this.setConfigurator(configurator);
		this.setIoSystem(ioSystem);
		this.setDealerActor(dealerActor);
		this.setCompLogginSystem(compLogginSystem);
		this.setResultsAnalyzer(resultsAnalyzer);
		this.setDeadline(deadline);
		this.setNewsList(newsList);
		this.setTasks(tasks);
		this.setActorsList(actorsList);
		this.setResourcesList(resourcesList);
		this.setNumberOfTasks(newsList.size());
		this.setFinishedList(finishedList);
		this.setFailedFinishedList(failedFinishedList);
	}

	public void finishFailedTask(Task currTask) {
		currTask.execPostProcessing();
		this.getFailedFinishedList().add(currTask);
	}

	public void finishTask(Task currTask) {
		currTask.execPostProcessing();
		this.getFinishedList().add(currTask);
	}

	public Vector<Actor> getActorsList() {
		return actorsList;
	}

	public CompLogginSystem getCompLogginSystem() {
		return compLogginSystem;
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

	public Vector<Task> getFailedFinishedList() {
		return failedFinishedList;
	}

	public Vector<Task> getFinishedList() {
		return finishedList;
	}

	public IOSystem getIoSystem() {
		return ioSystem;
	}

	public Vector<Task> getNewsList() {
		return newsList;
	}

	public int getNumberOfTasks() {
		return numberOfTasks;
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

		ioSystem.loadAll();
		deadline = ioSystem.getDeadline();
		tasks = ioSystem.getTasksList();
		newsList.addAll(tasks);
		actorsList = ioSystem.getActorsList();
		resourcesList = ioSystem.getResourcesList();

		Actor dealerActor = this.getDealerActor();
		dealerActor.setReadyList(newsList);

		this.setDealerActor(dealerActor);
		this.setDeadline(deadline);
		this.setNewsList(newsList);
		this.setTasks(tasks);
		this.setActorsList(actorsList);
		this.setResourcesList(resourcesList);
		this.setNumberOfTasks(newsList.size());
	}

	public void loadData(String inputDir) {

		String bar = this.getConfigurator().getBarFromPath(inputDir);
		this.getConfigurator().setInputDir(inputDir + bar);

		String[] s = inputDir.split(bar);
		String projectName = s[s.length - 1];
		this.getConfigurator().setProjectName(projectName);

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
	}

	public void saveData() {
		//SerialIOSystem serialIOSystem = new SerialIOSystem(this.getConfigurator(), this);
		//serialIOSystem.saveAll();
		this.getIoSystem().saveAll();
		this.getCompLogginSystem().writeLog();
		this.getResultsAnalyzer().writeAnalysis();
	}

	public void saveData(String outputDir) {
		String projectName = this.getConfigurator().getProjectName();
		this.saveData(projectName, outputDir);
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
		if (actors.size() == i)
			return true;
		else
			return false;
	}

	public void setActorsList(Vector<Actor> actorsList) {
		this.actorsList = actorsList;
	}

	public void setCompLogginSystem(CompLogginSystem compLogginSystem) {
		this.compLogginSystem = compLogginSystem;
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

	public void setFailedFinishedList(Vector<Task> failedFinishedList) {
		this.failedFinishedList = failedFinishedList;
	}

	public void setFinishedList(Vector<Task> finishedList) {
		this.finishedList = finishedList;
	}

	public void setIoSystem(IOSystem ioSystem) {
		this.ioSystem = ioSystem;
	}

	public void setNewsList(Vector<Task> newsList) {
		this.newsList = newsList;
	}

	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
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
		CompLogginSystem logger = this.getCompLogginSystem();
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
	}

	public void start() {
		System.out.print("Loading data...");
		// this.loadData("/media/7a9cedf1-b094-440e-b619-c03d0ebfa4e2/projects/prj/unicen/diseño/tasks-on-resources-simulator/test_cases/test_case_1/");
		this.loadData();
		System.out.println(" done.");
		System.out.print("Simulation started...");
		this.simulateAndLog();
		System.out.println(" done.");
		System.out.print("Analyzing results...");
		this.getResultsAnalyzer().analyze();
		System.out.println(" done.");
		System.out.print("Saving data...");
		// this.saveData("test_case_1","/media/7a9cedf1-b094-440e-b619-c03d0ebfa4e2/projects/prj/unicen/diseño/tasks-on-resources-simulator/test_cases/");
		this.saveData();
		System.out.println(" done.");
		this.getResultsAnalyzer().print();
		System.out.println("Done!");
	}

}

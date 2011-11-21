package Controller.SchedulingSystem;

import java.util.Vector;

import Model.DataModel.Configurator.Configurator;

import Model.IOSystem.IOSystem;
import Model.IOSystem.SerialIOSystem;
import Model.IOSystem.XMLIOSystem;
import Model.LogginSystem.CompLogginSystem;
import Model.ResultsAnalyzer.ResultsAnalyzer;
import Controller.SchedulingAlgorithmSystem.FCFS;

public class SchedulingSystem {

	private Configurator configurator;
	private IOSystem ioSystem;
	private CompLogginSystem compLogginSystem;
	private ResultsAnalyzer resultsAnalyzer;

	private int numberOfTasks;
	private Actor dealerActor;
	private int deadline;
	private Vector<Task> newsList;
	private Vector<Actor> actorsList;
	private Vector<Resource> resourcesList;
	private Vector<Task> finishedList;
	private Vector<Task> failedFinishedList;

	public SchedulingSystem() {
		Configurator configurator = new Configurator();
		IOSystem ioSystem = new XMLIOSystem(configurator, this);
		// IOSystem ioSystem = new SerialIOSystem(configurator, this);
		CompLogginSystem compLogginSystem = new CompLogginSystem(configurator);
		ResultsAnalyzer resultsAnalyzer = new ResultsAnalyzer(this);
		int deadline = 0;
		Vector<Task> newsList = new Vector<Task>();
		Vector<Actor> actorsList = new Vector<Actor>();
		Vector<Resource> resourcesList = new Vector<Resource>();
		Vector<Task> finishedList = new Vector<Task>();
		Vector<Task> failedFinishedList = new Vector<Task>();

		String dealerActorId = "dealerActor";
		FCFS saReadyList = new FCFS();
		int limitTime = -1;
		Actor dealerActor = new Actor(dealerActorId, "actor", saReadyList,
				limitTime, this, 100, 100, null, 100, null);
		dealerActor.setReadyList(newsList);

		this.setConfigurator(configurator);
		this.setIoSystem(ioSystem);
		this.setDealerActor(dealerActor);
		this.setCompLogginSystem(compLogginSystem);
		this.setResultsAnalyzer(resultsAnalyzer);
		this.setDeadline(deadline);
		this.setNewsList(newsList);
		this.setActorsList(actorsList);
		this.setResourcesList(resourcesList);
		this.setNumberOfTasks(newsList.size());
		this.setFinishedList(finishedList);
		this.setFailedFinishedList(failedFinishedList);
	}

	public void loadData() {

		IOSystem ioSystem = this.getIoSystem();
		int deadline = this.getDeadline();
		Vector<Task> newsList = this.getNewsList();
		Vector<Actor> actorsList = this.getActorsList();
		Vector<Resource> resourcesList = this.getResourcesList();

		ioSystem.loadAll();
		deadline = ioSystem.getDeadline();
		newsList = ioSystem.getTasksList();
		actorsList = ioSystem.getActorsList();
		resourcesList = ioSystem.getResourcesList();

		Actor dealerActor = this.getDealerActor();
		dealerActor.setReadyList(newsList);

		this.setDealerActor(dealerActor);
		this.setDeadline(deadline);
		this.setNewsList(newsList);
		this.setActorsList(actorsList);
		this.setResourcesList(resourcesList);
		this.setNumberOfTasks(newsList.size());

		/*
		 * for(int i=0;i<resourcesList.size();i++){
		 * resourcesList.elementAt(i).print();
		 * System.out.println("-------------------------------------"); }
		 * 
		 * dealerActor.print();
		 * 
		 * for(int i=0;i<actorsList.size();i++){
		 * actorsList.elementAt(i).print();
		 * System.out.println("-------------------------------------"); }
		 * 
		 * for(int i=0;i<newsList.size();i++){ newsList.elementAt(i).print();
		 * System.out.println("-------------------------------------"); }
		 */
	}

	public void saveData() {
		//IOSystem ioSystem = new SerialIOSystem(this.getConfigurator(), this);
		//ioSystem.saveAll();
		this.getIoSystem().saveAll();
		this.getCompLogginSystem().writeLog();
		this.getResultsAnalyzer().writeAnalysis();
	}

	public void start() {
		System.out.print("Loading data...");
		this.loadData();
		System.out.println(" done.");
		System.out.print("Simulation started...");
		this.simulateAndLog();
		System.out.println(" done.");
		System.out.print("Analyzing results...");
		this.getResultsAnalyzer().analyze();
		System.out.println(" done.");
		System.out.print("Saving data...");
		this.saveData();
		System.out.println(" done.");
		this.getResultsAnalyzer().print();
		System.out.println("Done!");
	}

	public void simulateAndLog() {
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

	private void incTime() {
		Vector<Actor> actorsList = this.getActorsList();
		int n = actorsList.size();
		for (int i = 0; i < n; i++) {
			Actor actor = actorsList.get(i);
			actor.incTime();
		}
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

	public void finishTask(Task currTask) {
		currTask.execPostProcessing();
		this.getFinishedList().add(currTask);
	}

	public void finishFailedTask(Task currTask) {
		currTask.execPostProcessing();
		this.getFailedFinishedList().add(currTask);
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

	public Vector<Task> getNewsList() {
		return newsList;
	}

	public void setNewsList(Vector<Task> newsList) {
		this.newsList = newsList;
	}

	public Vector<Actor> getActorsList() {
		return actorsList;
	}

	public void setActorsList(Vector<Actor> actorsList) {
		this.actorsList = actorsList;
	}

	public Vector<Task> getFinishedList() {
		return finishedList;
	}

	public void setFinishedList(Vector<Task> finishedList) {
		this.finishedList = finishedList;
	}

	public Vector<Task> getFailedFinishedList() {
		return failedFinishedList;
	}

	public void setFailedFinishedList(Vector<Task> failedFinishedList) {
		this.failedFinishedList = failedFinishedList;
	}

	public IOSystem getInputSystem() {
		return ioSystem;
	}

	public void setInputSystem(IOSystem InputSystem) {
		this.ioSystem = InputSystem;
	}

	public int getNumberOfTasks() {
		return numberOfTasks;
	}

	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}

	public Actor getDealerActor() {
		return dealerActor;
	}

	public void setDealerActor(Actor dealerActor) {
		this.dealerActor = dealerActor;
	}

	public Vector<Resource> getResourcesList() {
		return resourcesList;
	}

	public void setResourcesList(Vector<Resource> resourcesList) {
		this.resourcesList = resourcesList;
	}

	public ResultsAnalyzer getResultsAnalyzer() {
		return resultsAnalyzer;
	}

	public void setResultsAnalyzer(ResultsAnalyzer resultsAnalyzer) {
		this.resultsAnalyzer = resultsAnalyzer;
	}

	public CompLogginSystem getCompLogginSystem() {
		return compLogginSystem;
	}

	public void setCompLogginSystem(CompLogginSystem compLogginSystem) {
		this.compLogginSystem = compLogginSystem;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public IOSystem getIoSystem() {
		return ioSystem;
	}

	public void setIoSystem(IOSystem ioSystem) {
		this.ioSystem = ioSystem;
	}

	public Configurator getConfigurator() {
		return configurator;
	}

	public void setConfigurator(Configurator configurator) {
		this.configurator = configurator;
	}

	public static void main(String[] args) {
		SchedulingSystem schedulingSystem = new SchedulingSystem();
		schedulingSystem.start();
	}

}
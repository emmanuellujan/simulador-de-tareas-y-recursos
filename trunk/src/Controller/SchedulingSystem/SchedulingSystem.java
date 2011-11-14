package Controller.SchedulingSystem;
import java.util.Vector;

import Model.DataModel.Configurator.Configurator;
import Model.InputSystem.InputSystem;
import Model.InputSystem.XMLInputSystem;
import Model.LogginSystem.CompLogginSystem;
import Model.ResultsAnalyzer.ResultsAnalyzer;
import Controller.SchedulingAlgorithmSystem.FCFS;

public class SchedulingSystem {

	private InputSystem inputSystem;
	private ResultsAnalyzer resultsAnalyzer;
	private Vector<Task> newsList;
	private Vector<Actor> actorsList;
	private Vector<Resource> resourcesList;
	private Vector<Task> finishedList;
	private int numberOfTasks;
	private Actor deliverRes;

	public SchedulingSystem(){		
		
	}

	public void loadData(){
		Vector<Task> newsList;
		Vector<Actor> actorsList;
		Vector<Resource> resourcesList;
		Vector<Task> finishedList = new Vector<Task>();

		Configurator configurator = new Configurator();
		InputSystem inputSystem = new XMLInputSystem(configurator,this);
		
		CompLogginSystem.getInstance(configurator);
		
		ResultsAnalyzer resultsAnalyzer = new ResultsAnalyzer(this);
				
		newsList = inputSystem.loadNewsList();
		actorsList = inputSystem.loadActorsList();
		resourcesList = inputSystem.loadResourcesList();
		
		inputSystem.updateRelations();

		/*for(int i=0;i<resourcesList.size();i++){
			resourcesList.elementAt(i).print();
			System.out.println("-------------------------------------");
		}
		
		for(int i=0;i<actorsList.size();i++){
			actorsList.elementAt(i).print();
			System.out.println("-------------------------------------");
		}*/
		
		String deliverResId="deliverRes";
		FCFS saReadyList = new FCFS();
		int limitTime = -1;
		Actor deliverRes = new Actor(deliverResId, saReadyList, limitTime, this, 100, 100, null, 100, null);
		deliverRes.setReadyList(newsList);
				
		this.setNewsList(newsList);
		this.setActorsList(actorsList);
		this.setResourcesList(resourcesList);
		this.setFinishedList(finishedList);
		this.setNumberOfTasks(newsList.size());
		this.setInputSystem(inputSystem);
		this.setDeliverRes(deliverRes);
		this.setResultsAnalyzer(resultsAnalyzer);
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
		this.getResultsAnalyzer().print();
		System.out.println("Done!");
	}
	
	public void simulateAndLog() {
		Vector<Actor> actorsList = getActorsList();
		CompLogginSystem logger = CompLogginSystem.getInstance();
		Actor deliverRes = this.getDeliverRes();
		actorsList.add(0, deliverRes);
		int i = 0;
		int n = actorsList.size();
		while(!scheduleFinished()){
			for(int j=0;j<n;j++)
				actorsList.get(j).exec();
			this.incTime();
			logger.log(i,actorsList);
			i++;
		}
		logger.writeLog();
	}

	private void incTime() {
		Vector<Actor> actorsList = this.getActorsList();
		int n = actorsList.size();
		for(int i=0;i<n;i++){
			Actor actor = actorsList.get(i);
			actor.incTime();
		}
	}

	private boolean scheduleFinished() {
		Vector<Task> finishedList = this.getFinishedList();
		int n = finishedList.size();
		int numberOfTasks = this.getNumberOfTasks();
		if(numberOfTasks==n)
			return true;
		else
			return false;
	}

	public void finishTask(Task currTask) {
		this.getFinishedList().add(currTask);
	}

	public Actor getResource(String name) {
		Vector<Actor> actorsList = this.getActorsList();
		boolean finded = false;
		int i = 0;
		int n = actorsList.size();
		Actor actor = null;
		while(i<n && !finded){
			if(actorsList.get(i).getResId().equals(name)){
				finded = true;
				actor = actorsList.get(i);
			}else
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

	public InputSystem getInputSystem() {
		return inputSystem;
	}

	public void setInputSystem(InputSystem InputSystem) {
		this.inputSystem = InputSystem;
	}

	public int getNumberOfTasks() {
		return numberOfTasks;
	}

	public void setNumberOfTasks(int numberOfTasks) {
		this.numberOfTasks = numberOfTasks;
	}

	public Actor getDeliverRes() {
		return deliverRes;
	}

	public void setDeliverRes(Actor deliverRes) {
		this.deliverRes = deliverRes;
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

	public static void main(String[] args){
		SchedulingSystem schedulingSystem = new SchedulingSystem();
		schedulingSystem.start();
	}

	public int getDeadline() {
		// TODO Auto-generated method stub
		return 0;
	}

}

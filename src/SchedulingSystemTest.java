import java.util.Hashtable;
import java.util.Vector;

import junit.framework.Assert;
import logicLayer.filterSystem.ActorRelationshipFilter;
import logicLayer.filterSystem.AndFilter;
import logicLayer.filterSystem.EqualPropertyFilter;
import logicLayer.filterSystem.Filter;
import logicLayer.filterSystem.TaskOwnerFilter;
import logicLayer.resultsAnalyzer.BasicAnalyzer;
import logicLayer.schedulingAlgorithmSystem.FCFS;
import logicLayer.schedulingAlgorithmSystem.SchedulingAlgorithm;
import logicLayer.schedulingSystem.Actor;
import logicLayer.schedulingSystem.Resource;
import logicLayer.schedulingSystem.SchedulingSystem;
import logicLayer.schedulingSystem.Task;
import logicLayer.schedulingSystem.Update;
import logicLayer.schedulingSystem.Updater;

import org.junit.Before;
import org.junit.Test;

import persistenceLayer.ioSystem.IOSystem;
import persistenceLayer.ioSystem.SerialIOSystem;
import persistenceLayer.ioSystem.XMLIOSystem;

public class SchedulingSystemTest {

	private SchedulingSystem schedulingSystem;
	private String dir;

	public String getDir() {
		return dir;
	}

	public SchedulingSystem getSchedulingSystem() {
		return schedulingSystem;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public void setSchedulingSystem(SchedulingSystem schedulingSystem) {
		this.schedulingSystem = schedulingSystem;
	}

	/**
	 * @throws java.lang.Exception
	 */

	// Code executed before each test
	@Before
	public void setUp() throws Exception {
		SchedulingSystem schedulingSystem = new SchedulingSystem();
		this.setSchedulingSystem(schedulingSystem);
		this.setDir("/media/7a9cedf1-b094-440e-b619-c03d0ebfa4e2/projects/prj/unicen/disenio/tasks-on-resources-simulator/test_cases");
	}

	public void test(String dir2, double d) {
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		String dir = this.getDir();
		String bar = schedulingSystem.getConfigurator().getBarFromPath(dir);
		schedulingSystem.start2(dir + bar + dir2, dir);
		double result = ((BasicAnalyzer) schedulingSystem.getResultsAnalyzer())
				.getPropVelocity();
		Assert.assertEquals(d, result, 0.0001);
	}

	// Code that tests one thing
	@Test
	public void test1() {
		this.test("test_case_1", 8.0);
	}

	@Test
	public void test2() {
		this.test("test_case_2", 7.2727275);
	}

	@Test
	public void test3() {

		String dir2 = "test_case_3";
		double d = 10.0;

		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		String dir = this.getDir();
		String bar = schedulingSystem.getConfigurator().getBarFromPath(dir);

		Vector<String> workUnits = new Vector<String>();
		workUnits.add("actor0");
		workUnits.add("actor0");
		workUnits.add("actor0");
		Task task = new Task("task0", 1, workUnits, null, null, "New", 1,
				schedulingSystem, null, null);

		// Esta tarea necesita ser ejecutada siempre por un
		// empleado Categoría A
		String key = "Category";
		String value = "A";
		EqualPropertyFilter epf1 = new EqualPropertyFilter(key, value);

		TaskOwnerFilter tof = new TaskOwnerFilter(task);

		AndFilter af = new AndFilter(epf1, tof);
		task.setFilter(af);

		// Cuando la tarea termina se busca un reporte vacío
		key = "type";
		value = "report";
		epf1 = new EqualPropertyFilter(key, value);

		key = "state";
		value = "empty";
		EqualPropertyFilter epf2 = new EqualPropertyFilter(key, value);

		af = new AndFilter(epf1, epf2);

		// y luego se actualización el reporte como completo
		Update u1 = new Update();
		key = "state";
		value = "complete";
		u1.addProperty(key, value);
		Updater u = new Updater();
		u.addUpdate(af, u1);
		task.setUpdater(u);

		String inputDir = dir + bar + dir2;
		String outputDir = dir;
		IOSystem ioSystem = new XMLIOSystem(schedulingSystem.getConfigurator(),
				schedulingSystem);
		schedulingSystem.setIoSystem(ioSystem);
		System.out.print("Loading data...");
		schedulingSystem.loadData(inputDir);
		schedulingSystem.getTasks().add(task);
		System.out.println(" done.");
		System.out.print("Simulation started...");
		schedulingSystem.simulateAndLog();
		System.out.println(" done.");
		System.out.print("Analyzing results...");
		schedulingSystem.getResultsAnalyzer().analyze();
		System.out.println(" done.");
		System.out.print("Saving data...");
		schedulingSystem.saveData(outputDir);
		System.out.println(" done.\n");
		schedulingSystem.getResultsAnalyzer().print();
		System.out.println("Done!\n\n");
		SerialIOSystem serialIOSystem = new SerialIOSystem(
				schedulingSystem.getConfigurator(), schedulingSystem);
		serialIOSystem.saveAll();

		double result = ((BasicAnalyzer) schedulingSystem.getResultsAnalyzer())
				.getPropVelocity();
		Assert.assertEquals(d, result, 0.0001);
	}

	@Test
	public void test4() {
		this.test("test_case_4", 7.2727275);
	}

	@Test
	public void test5() {
		this.test("test_case_5", 16.0);
	}

	@Test
	public void test6() {
		this.test("test_case_6", 8.888889);
	}

	@Test
	public void test7() {
		this.test("test_case_7", 1.4035088);
	}

	@Test
	public void test8() {
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		String dir = this.getDir();
		String bar = schedulingSystem.getConfigurator().getBarFromPath(dir);

		// Create Document
		String resId = "document0";
		String type = "Artifact";
		Hashtable<String, String> properties = new Hashtable<String, String>();
		properties.put("completo", "cero");
		int maxRelations = 10;
		Vector<String> relationsIds = new Vector<String>();
		relationsIds.add("actor0");
		Resource document = new Resource(resId, type, properties, maxRelations,
				relationsIds, schedulingSystem);

		// Create Actor
		resId = "actor0";
		type = "Actor";
		SchedulingAlgorithm saReadyList = new FCFS();
		int limitTime = -1;
		int capacity = 10;
		int maxTaskNumber = 10;
		properties = new Hashtable<String, String>();
		properties.put("felicidad", "normal");
		maxRelations = 10;
		relationsIds = new Vector<String>();
		relationsIds.add("document0");
		Updater updater = null;
		Actor actor = new Actor(resId, type, saReadyList, limitTime,
				schedulingSystem, capacity, maxTaskNumber, properties,
				maxRelations, relationsIds, updater);

		// Updater of the Actor
		// Filter
		String key = "completo";
		String value = "cero";
		EqualPropertyFilter f1 = new EqualPropertyFilter(key, value);
		ActorRelationshipFilter f2 = new ActorRelationshipFilter(actor);
		AndFilter f3 = new AndFilter(f1, f2);
		// Update of the Updater
		Update u1 = new Update(actor);
		key = "felicidad";
		value = "poca";
		u1.addProperty(key, value);
		Updater u = new Updater();
		u.addUpdate(f3, u1);

		actor.setUpdater(u);

		// Update Relations between Actor and Task
		actor.getResources().add(document);

		// Create Task
		String taskId = "task0";
		int priority = 1;
		Vector<String> workUnits = new Vector<String>();
		workUnits.add("actor0");
		workUnits.add("actor0");
		workUnits.add("actor0");
		String contTaskId = "";
		Task contingencyTask = null;
		String currentStatus = "New";
		int difficult = 1;
		Filter filter = null;
		Updater updater1 = null;
		Task task = new Task(taskId, priority, workUnits, contTaskId,
				contingencyTask, currentStatus, difficult, schedulingSystem,
				filter, updater1);

		// Exec simulator
		String dir2 = "test_case_8";
		double d = 10.0;
		String inputDir = dir + bar + dir2;
		String outputDir = dir;

		System.out.print("Loading data...");
		schedulingSystem.getConfigurator().setInputDir(inputDir);
		schedulingSystem.getConfigurator().setOutputDir(outputDir);
		schedulingSystem.getConfigurator().setProjectName(dir2);
		schedulingSystem.setDeadline(80);
		schedulingSystem.getResourcesList().add(document);
		schedulingSystem.getTasks().add(task);
		schedulingSystem.getActorsList().add(actor);

		System.out.println(" done.");
		System.out.print("Simulation started...");
		schedulingSystem.simulateAndLog();
		System.out.println(" done.");
		System.out.print("Analyzing results...");
		schedulingSystem.getResultsAnalyzer().analyze();
		System.out.println(" done.");
		System.out.print("Saving data...");
		schedulingSystem.saveData(outputDir);
		System.out.println(outputDir);
		System.out.println(" done.\n");
		schedulingSystem.getResultsAnalyzer().print();
		System.out.println("Done!\n\n");

		double result = ((BasicAnalyzer) schedulingSystem.getResultsAnalyzer())
				.getPropVelocity();
		Assert.assertEquals(d, result, 0.0001);

	}

}

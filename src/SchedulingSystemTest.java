import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import LogicLayer.SchedulingSystem.SchedulingSystem;

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
		this.setDir("/media/7a9cedf1-b094-440e-b619-c03d0ebfa4e2/projects/prj/unicen/diseño/tasks-on-resources-simulator/test_cases");
	}

	public void test(String dir2, double d) {
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		String dir = this.getDir();
		String bar = schedulingSystem.getConfigurator().getBarFromPath(dir);
		schedulingSystem.start(dir + bar + dir2, dir);
		double result = schedulingSystem.getResultsAnalyzer().getPropVelocity();
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
		this.test("test_case_3", 8.888889);
	}

	@Test
	public void test4() {
		this.test("test_case_4", 8.888889);
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

}

package Controller.SchedulingAlgorithmSystem;

public class SAFactory {

	public SAFactory() {
	}

	public SchedulingAlgorithm getSchedulingAlgorithm(
			String sSchedulingAlgorithm) {
		SchedulingAlgorithm schedulingAlgorithm = null;
		if (sSchedulingAlgorithm.equals("FCFS"))
			schedulingAlgorithm = new FCFS();
		else if (sSchedulingAlgorithm.equals("PrioritiesSA"))
			schedulingAlgorithm = new PrioritiesSA();

		return schedulingAlgorithm;
	}

}

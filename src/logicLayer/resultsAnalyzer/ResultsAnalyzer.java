package logicLayer.resultsAnalyzer;

import logicLayer.schedulingSystem.SchedulingSystem;
import persistenceLayer.ioSystem.FileManager;

public abstract class ResultsAnalyzer {
	private SchedulingSystem schedulingSystem;
	private String analysis;

	public ResultsAnalyzer(SchedulingSystem schedulingSystem) {
		this.setSchedulingSystem(schedulingSystem);
		this.setAnalysis("");
	}

	public abstract void analyze();

	public String getAnalysis() {
		return analysis;
	}

	public SchedulingSystem getSchedulingSystem() {
		return schedulingSystem;
	}

	public void print() {
		System.out.println(this.getAnalysis());
	}

	public abstract void renderAnalysis();

	public void reset() {
		// TODO Auto-generated method stub
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
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

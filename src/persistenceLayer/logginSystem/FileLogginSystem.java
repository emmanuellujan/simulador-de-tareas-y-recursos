package persistenceLayer.logginSystem;

import java.util.Vector;

import persistenceLayer.dataModel.Configurator.Configurator;
import persistenceLayer.dataModel.SimulationTime.SimulationTime;
import persistenceLayer.ioSystem.FileManager;


public abstract class FileLogginSystem extends LogginSystem {
	protected String extension;
	protected FileManager fileManager;

	public FileLogginSystem(Configurator configurator,
			Vector<SimulationTime> simulationTimes, String extension) {
		super(configurator, simulationTimes);
		FileManager fileManager = new FileManager();
		this.setFileManager(fileManager);
		this.setExtension(extension);
	}

	public String getExtension() {
		return extension;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public String getOutputFileName() {
		String outputFileName = this.getConfigurator().getOutputDir()
				+ this.getConfigurator().getProjectName() + "_log."
				+ this.getExtension();
		return outputFileName;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

	protected void writeFile(String s) {
		this.getFileManager().writeFile(this.getOutputFileName(), s);
	}

}

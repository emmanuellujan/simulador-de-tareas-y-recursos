package Model.LogginSystem;
import java.util.Vector;

import Model.DataModel.Configurator.Configurator;
import Model.DataModel.SimulationTime.SimulationTime;
import Model.IOSystem.FileManager;


public abstract class FileLogginSystem extends LogginSystem{
	protected String outputFileName;
	protected FileManager fileManager;
	
	public FileLogginSystem(Configurator configurator,Vector<SimulationTime> simulationTimes) {
		super(configurator,simulationTimes);
		FileManager fileManager = new FileManager();
		this.setFileManager(fileManager);
	}
	
	protected void writeFile(String s) {
		this.getFileManager().writeFile(this.getOutputFileName(), s);
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

	public FileManager getFileManager() {
		return fileManager;
	}

	public void setFileManager(FileManager fileManager) {
		this.fileManager = fileManager;
	}

}

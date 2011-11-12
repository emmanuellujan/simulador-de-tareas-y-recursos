package Model.LogginSystem;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import Model.DataModel.Configurator.Configurator;
import Model.DataModel.SimulationTime.SimulationTime;


public abstract class FileLogginSystem extends LogginSystem{
	String outputFileName;

	public FileLogginSystem(Configurator configurator) {
		super(configurator);
	}
	
	public FileLogginSystem(Configurator configurator,Vector<SimulationTime> simulationTimes) {
		super(configurator,simulationTimes);
	}
	
	protected void writeFile(String s) {
		DataOutputStream f = null;
		try{
			String fileName = this.getOutputFileName();
			f = new DataOutputStream( new FileOutputStream(fileName,false) );
			f.writeBytes(s);
		}
		catch(FileNotFoundException fnfe) {  }
		catch (IOException ioe) {  }
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
}

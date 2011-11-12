package Model.LogginSystem;
import java.util.Vector;
import Model.DataModel.Configurator.Configurator;

public class CompLogginSystem extends LogginSystem{
	
	private static CompLogginSystem compLogginSystem;
	private Vector<LogginSystem> logginSystems;

	public CompLogginSystem(Configurator configurator) {
		super(configurator);
	
		Vector<LogginSystem> logginSystems = new Vector<LogginSystem>();
		this.setLogginSystems(logginSystems);
		
		LogginSystem txtLogginSystem = new TXTLogginSystem(configurator,this.getSimulationTimes());
		LogginSystem xmlLogginSystem = new XMLLogginSystem(configurator,this.getSimulationTimes());
		this.addLogginSystem(xmlLogginSystem);
		this.addLogginSystem(txtLogginSystem);
	}

	public static CompLogginSystem getInstance(Configurator configurator){
		if(compLogginSystem != null)
			return compLogginSystem;
		else{
			compLogginSystem = new CompLogginSystem(configurator);
			return compLogginSystem;
		}
	}
	
	public static CompLogginSystem getInstance(){
		return compLogginSystem;
	}
	
	public void writeLog(){
		Vector<LogginSystem> logginSystems = this.getLogginSystems();
		int n = logginSystems.size();
		for(int i=0; i<n; i++)
			logginSystems.get(i).writeLog();
	}
	
	public Vector<LogginSystem> getLogginSystems() {
		return logginSystems;
	}

	public void setLogginSystems(Vector<LogginSystem> logginSystems) {
		this.logginSystems = logginSystems;
	}
	
	public void addLogginSystem(LogginSystem logginSystem){
		this.logginSystems.add(logginSystem);
	}

}

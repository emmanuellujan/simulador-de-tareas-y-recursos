package Model.DataModel.SimulationTime;
import java.util.Vector;

import Controller.SchedulingSystem.Resource;


public class SimulationTime {
	private int currentTime;
	private Vector<SimulationDevice> devices;
	
	public SimulationTime(int time, Vector<Resource> devicesList){
		Vector<SimulationDevice> devices = new Vector<SimulationDevice>(); 
		Resource resource;
		SimulationDevice sDevice;
		int n = devicesList.size();
		for(int i=0;i<n;i++){
			resource = devicesList.get(i); 
			sDevice = new SimulationDevice(resource);
			devices.add(sDevice);
		}
		this.setCurrentTime(time);
		this.setDevices(devices);
	}

	public int getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public Vector<SimulationDevice> getDevices() {
		return devices;
	}

	public void setDevices(Vector<SimulationDevice> devices) {
		this.devices = devices;
	}
}

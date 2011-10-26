package Model.InputSystem;
import java.io.File;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Model.DataModel.Configurator.Configurator;
import Controller.SchedulingAlgorithmSystem.SAFactory;
import Controller.SchedulingAlgorithmSystem.SchedulingAlgorithm;
import Controller.SchedulingSystem.SchedulingSystem;
import Controller.SchedulingSystem.Device;
import Controller.SchedulingSystem.Process;


public class XMLInputSystem extends InputSystem{

	public XMLInputSystem(Configurator configurator, SchedulingSystem schedulingSystem) {
		super(configurator,schedulingSystem);
	}

	public Vector<Process> loadNewsList() {
		String fileName = this.getConfigurator().getIoDirectory() + this.getConfigurator().getInputFile() + ".xml";
		Vector<Process> processes = new Vector<Process>(); 
		try{
			File file = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("process");
			int n = nodeList.getLength();
			for (int i = 0; i < n; i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {	
					Element element = (Element) node;

					//processId
					NodeList processIdElementList = element.getElementsByTagName("processId");
					Element processIdElement = (Element) processIdElementList.item(0);
					NodeList processId = processIdElement.getChildNodes();
					String sProcessId = ((Node) processId.item(0)).getNodeValue();

					//priority
					NodeList priorityElementList = element.getElementsByTagName("priority");
					Element priorityElement = (Element) priorityElementList.item(0);
					NodeList priority = priorityElement.getChildNodes();
					int iPriority =  Integer.valueOf(((Node) priority.item(0)).getNodeValue()) ;

					//unitComps
					Vector<String> compUnits = new Vector<String>();
					NodeList cuElementList = element.getElementsByTagName("compUnit");
					int m = cuElementList.getLength();
					for(int j=0;j<m;j++){
						Element cuElement = (Element) cuElementList.item(j);
						String unitComp = cuElement.getFirstChild().getNodeValue();
						compUnits.add(unitComp);
					}

					Process process = new Process(sProcessId,iPriority,compUnits);
					processes.add(process);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return processes;
	}

	public Vector<Device> loadDevicesList(){

		String fileName = this.getConfigurator().getIoDirectory() + this.getConfigurator().getInputFile() + ".xml";
		Vector<Device> devices = new Vector<Device>(); 
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		SAFactory saFactory = new SAFactory();
		try{
			File file = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("device");
			int n = nodeList.getLength();
			for (int i = 0; i < n; i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {	
					Element element = (Element) node;

					//deviceId
					NodeList deviceIdElementList = element.getElementsByTagName("deviceId");
					Element deviceIdElement = (Element) deviceIdElementList.item(0);
					NodeList deviceId = deviceIdElement.getChildNodes();
					String sDeviceId = ((Node) deviceId.item(0)).getNodeValue();

					//schedulingAlgorithm
					NodeList saElementList = element.getElementsByTagName("schedulingAlgorithm");
					Element saElement = (Element) saElementList.item(0);
					NodeList schedulingAlgorithm = saElement.getChildNodes();
					String sSchedulingAlgorithm = ((Node) schedulingAlgorithm.item(0)).getNodeValue();
					SchedulingAlgorithm sAlgorithm = saFactory.getSchedulingAlgorithm(sSchedulingAlgorithm);

					//quantum
					NodeList quantumElementList = element.getElementsByTagName("quantum");
					Element quantumElement = (Element) quantumElementList.item(0);
					NodeList quantum = quantumElement.getChildNodes();
					int iQuantum =  Integer.valueOf(((Node) quantum.item(0)).getNodeValue());

					Device device = new Device(sDeviceId, sAlgorithm, iQuantum, schedulingSystem);
					devices.add(device);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return devices;
	}

}

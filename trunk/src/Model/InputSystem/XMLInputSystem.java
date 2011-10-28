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
import Controller.SchedulingSystem.Actor;
import Controller.SchedulingSystem.Task;


public class XMLInputSystem extends InputSystem{

	public XMLInputSystem(Configurator configurator, SchedulingSystem schedulingSystem) {
		super(configurator,schedulingSystem);
	}

	public Vector<Task> loadNewsList() {
		String fileName = this.getConfigurator().getIoDirectory() + this.getConfigurator().getInputFile() + ".xml";
		Vector<Task> tasks = new Vector<Task>(); 
		try{
			File file = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("task");
			int n = nodeList.getLength();
			for (int i = 0; i < n; i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {	
					Element element = (Element) node;

					//taskId
					NodeList taskIdElementList = element.getElementsByTagName("taskId");
					Element taskIdElement = (Element) taskIdElementList.item(0);
					NodeList taskId = taskIdElement.getChildNodes();
					String sTaskId = ((Node) taskId.item(0)).getNodeValue();

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

					Task task = new Task(sTaskId,iPriority,compUnits);
					tasks.add(task);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tasks;
	}

	public Vector<Actor> loadActorsList(){

		String fileName = this.getConfigurator().getIoDirectory() + this.getConfigurator().getInputFile() + ".xml";
		Vector<Actor> actors = new Vector<Actor>(); 
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		SAFactory saFactory = new SAFactory();
		try{
			File file = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("resource");
			int n = nodeList.getLength();
			for (int i = 0; i < n; i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {	
					Element element = (Element) node;

					//resourceId
					NodeList resourceIdElementList = element.getElementsByTagName("resourceId");
					Element resourceIdElement = (Element) resourceIdElementList.item(0);
					NodeList resourceId = resourceIdElement.getChildNodes();
					String sResourceId = ((Node) resourceId.item(0)).getNodeValue();

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

					Actor actor = new Actor(sResourceId, sAlgorithm, iQuantum, schedulingSystem);
					actors.add(actor);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actors;
	}

}

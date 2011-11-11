package Model.InputSystem;

import java.io.File;
import java.util.Hashtable;
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
import Controller.SchedulingSystem.Resource;
import Controller.SchedulingSystem.SchedulingSystem;
import Controller.SchedulingSystem.Actor;
import Controller.SchedulingSystem.Task;

public class XMLInputSystem extends InputSystem {

	public XMLInputSystem(Configurator configurator,
			SchedulingSystem schedulingSystem) {
		super(configurator, schedulingSystem);
	}

	public Vector<Task> loadNewsList() {
		String fileName = this.getConfigurator().getIoDirectory()
				+ this.getConfigurator().getInputFile() + ".xml";
		Vector<Task> tasks = new Vector<Task>();
		try {
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

					// taskId
					NodeList taskIdElementList = element
							.getElementsByTagName("taskId");
					Element taskIdElement = (Element) taskIdElementList.item(0);
					NodeList taskId = taskIdElement.getChildNodes();
					String sTaskId = ((Node) taskId.item(0)).getNodeValue();

					// difficult
					NodeList difficultElementList = element
							.getElementsByTagName("difficult");
					Element difficultElement = (Element) difficultElementList
							.item(0);
					NodeList difficult = difficultElement.getChildNodes();
					String sDifficult = ((Node) difficult.item(0))
							.getNodeValue();

					// priority
					NodeList priorityElementList = element
							.getElementsByTagName("priority");
					Element priorityElement = (Element) priorityElementList
							.item(0);
					NodeList priority = priorityElement.getChildNodes();
					int iPriority = Integer.valueOf(((Node) priority.item(0))
							.getNodeValue());

					// Contingency Task
					NodeList contingencyTaskElementList = element
							.getElementsByTagName("contingencyTask");
					Element contingencyTaskElement = (Element) contingencyTaskElementList
							.item(0);
					NodeList contingencyTask = contingencyTaskElement
							.getChildNodes();
					String sContingencyTask = null;
					Node n1 = contingencyTask.item(0);
					if (n1 != null)
						sContingencyTask = ((Node) contingencyTask.item(0))
								.getNodeValue();

					// compsUnits
					Vector<String> compUnits = new Vector<String>();
					NodeList cuElementList = element
							.getElementsByTagName("compUnit");
					int m = cuElementList.getLength();
					for (int j = 0; j < m; j++) {
						Element cuElement = (Element) cuElementList.item(j);
						String unitComp = cuElement.getFirstChild()
								.getNodeValue();
						compUnits.add(unitComp);
					}

					Task task = new Task(sTaskId, iPriority, compUnits,
							sContingencyTask, null, "New",
							Integer.parseInt(sDifficult));

					tasks.add(task);
				}
			}

			Vector<Task> contTasks = new Vector<Task>();

			for (int i = 0; i < tasks.size(); i++) {
				Task task = tasks.elementAt(i);
				String contTaskId = task.getContTaskId();
				for (int j = 0; j < tasks.size(); j++) {
					Task task2 = tasks.elementAt(j);
					if (task2.getTaskId().equals(contTaskId)) {
						task.setContingencyTask(task2);
						contTasks.add(task2);
						tasks.remove(task2);
					}
				}
				for (int j = 0; j < contTasks.size(); j++) {
					Task task2 = contTasks.elementAt(j);
					if (task2.getTaskId().equals(contTaskId))
						task.setContingencyTask(task2);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tasks;
	}

	public Vector<Actor> loadActorsList() {

		String fileName = this.getConfigurator().getIoDirectory()
				+ this.getConfigurator().getInputFile() + ".xml";
		Vector<Actor> actors = new Vector<Actor>();
		SchedulingSystem schedulingSystem = this.getSchedulingSystem();
		SAFactory saFactory = new SAFactory();
		try {
			File file = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();

			NodeList nodeList = doc.getElementsByTagName("actor");
			int n = nodeList.getLength();
			for (int i = 0; i < n; i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;

					// actorId
					NodeList actorIdElementList = element
							.getElementsByTagName("actorId");
					Element actorIdElement = (Element) actorIdElementList
							.item(0);
					NodeList actorId = actorIdElement.getChildNodes();
					String sActorId = ((Node) actorId.item(0)).getNodeValue();

					// maxRelations
					NodeList maxRelationsElementList = element
							.getElementsByTagName("maxRelations");
					Element maxRelationsElement = (Element) maxRelationsElementList
							.item(0);
					NodeList maxRelations = maxRelationsElement.getChildNodes();
					String smaxRelations = ((Node) maxRelations.item(0))
							.getNodeValue();

					// actorCapacity
					NodeList actorCapacityElementList = element
							.getElementsByTagName("capacity");
					Element actorCapacityElement = (Element) actorCapacityElementList
							.item(0);
					NodeList actorCapacity = actorCapacityElement
							.getChildNodes();
					String sActorCapacity = ((Node) actorCapacity.item(0))
							.getNodeValue();

					// maxTasks
					NodeList maxTasksElementList = element
							.getElementsByTagName("maxTasks");
					Element maxTasksElement = (Element) maxTasksElementList
							.item(0);
					NodeList maxTasks = maxTasksElement.getChildNodes();
					String sMaxTasks = ((Node) maxTasks.item(0)).getNodeValue();

					// schedulingAlgorithm
					NodeList saElementList = element
							.getElementsByTagName("schedulingAlgorithm");
					Element saElement = (Element) saElementList.item(0);
					NodeList schedulingAlgorithm = saElement.getChildNodes();
					String sSchedulingAlgorithm = ((Node) schedulingAlgorithm
							.item(0)).getNodeValue();
					SchedulingAlgorithm sAlgorithm = saFactory
							.getSchedulingAlgorithm(sSchedulingAlgorithm);

					// quantum
					NodeList quantumElementList = element
							.getElementsByTagName("quantum");
					Element quantumElement = (Element) quantumElementList
							.item(0);
					NodeList quantum = quantumElement.getChildNodes();
					int iQuantum = Integer.valueOf(((Node) quantum.item(0))
							.getNodeValue());

					// Properties
					Hashtable<String, String> properties = new Hashtable<String, String>();
					NodeList pElementList = element
							.getElementsByTagName("property");
					int m = pElementList.getLength();
					for (int j = 0; j < m; j++) {
						Element pElement = (Element) pElementList.item(j);
						String key = pElement.getChildNodes().item(1)
								.getChildNodes().item(0).getNodeValue();
						String value = pElement.getChildNodes().item(3)
								.getChildNodes().item(0).getNodeValue();
						properties.put(key, value);
					}

					// Relations
					Vector<String> relationsIds = new Vector<String>();
					NodeList rElementList = element
							.getElementsByTagName("relation");
					int m1 = rElementList.getLength();
					for (int j = 0; j < m1; j++) {
						Element rElement = (Element) rElementList.item(j);
						String resourceId = rElement.getFirstChild()
								.getNodeValue();
						relationsIds.add(resourceId);
					}

					Actor actor = new Actor(sActorId, sAlgorithm, iQuantum,
							schedulingSystem, Integer.parseInt(sActorCapacity),
							Integer.parseInt(sMaxTasks), properties,
							Integer.parseInt(smaxRelations), relationsIds);

					actors.add(actor);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setActorsList(actors);
		return actors;
	}

	public Vector<Resource> loadResourcesList() {

		String fileName = this.getConfigurator().getIoDirectory()
				+ this.getConfigurator().getInputFile() + ".xml";
		Vector<Resource> resources = new Vector<Resource>();
		try {
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

					// resourceId
					NodeList resourceIdElementList = element
							.getElementsByTagName("resourceId");
					Element resourceIdElement = (Element) resourceIdElementList
							.item(0);
					NodeList resourceId = resourceIdElement.getChildNodes();
					String sResourceId = ((Node) resourceId.item(0))
							.getNodeValue();

					// maxRelations
					NodeList maxRelationsElementList = element
							.getElementsByTagName("maxRelations");
					Element maxRelationsElement = (Element) maxRelationsElementList
							.item(0);
					NodeList maxRelations = maxRelationsElement.getChildNodes();
					String smaxRelations = ((Node) maxRelations.item(0))
							.getNodeValue();

					// Properties
					Hashtable<String, String> properties = new Hashtable<String, String>();
					NodeList pElementList = element
							.getElementsByTagName("property");
					int m = pElementList.getLength();
					for (int j = 0; j < m; j++) {
						Element pElement = (Element) pElementList.item(j);
						String key = pElement.getChildNodes().item(1)
								.getChildNodes().item(0).getNodeValue();
						String value = pElement.getChildNodes().item(3)
								.getChildNodes().item(0).getNodeValue();
						properties.put(key, value);
					}

					// Relations
					Vector<String> relationsIds = new Vector<String>();
					NodeList rElementList = element
							.getElementsByTagName("relation");
					int m1 = rElementList.getLength();
					for (int j = 0; j < m1; j++) {
						Element rElement = (Element) rElementList.item(j);
						String resourceId1 = rElement.getFirstChild()
								.getNodeValue();
						relationsIds.add(resourceId1);
					}

					Resource resource = new Resource(sResourceId, properties,
							Integer.parseInt(smaxRelations), relationsIds);

					resources.add(resource);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setResourcesList(resources);
		return resources;
	}

	public void updateRelations() {

		Vector<Resource> allResList = new Vector<Resource>();
		allResList.addAll(this.getActorsList());
		allResList.addAll(this.getResourcesList());

		// Update relations
		for (int i = 0; i < allResList.size(); i++) {
			Resource resource = allResList.elementAt(i);
			for (int j = 0; j < resource.getRelationsIds().size(); j++) {
				String resourceId = resource.getRelationsIds().elementAt(j);
				boolean resourceFound = false;
				int k = 0;
				while (!resourceFound) {
					Resource resource2 = allResList.elementAt(k);
					if (resource2.getResId().equals(resourceId)) {
						resource.addRelation(resource2);
						resourceFound = true;
					} else
						k++;
				}
			}
		}
	}

}

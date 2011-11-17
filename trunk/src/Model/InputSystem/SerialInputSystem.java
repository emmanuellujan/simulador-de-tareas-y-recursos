package Model.InputSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Vector;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import Controller.SchedulingSystem.Actor;
import Controller.SchedulingSystem.Resource;
import Controller.SchedulingSystem.SchedulingSystem;
import Controller.SchedulingSystem.Task;
import Model.DataModel.Configurator.Configurator;

public class SerialInputSystem extends InputSystem {

	public SerialInputSystem(Configurator configurator,
			SchedulingSystem schedulingSystem) {
		super(configurator, schedulingSystem);
	}

	public void saveAll() {

		XStream xs = new XStream();
		try {

			String dir = this.getConfigurator().getIoDirectory();
			dir = dir + this.getConfigurator().getInputFile() + "/";

			FileOutputStream fs;
			// Vector<Resource> allResources = this.getAllResourcesList();
			Vector<Resource> allResources = this.getSchedulingSystem()
					.getInputSystem().getAllResourcesList();
			int n = allResources.size();
			int i = 0;
			for (i = 0; i < n; i++) {
				Resource resource = allResources.elementAt(i);
				fs = new FileOutputStream(dir + i + ".xml");
				xs.toXML(resource, fs);
			}

			Vector<Task> tasks = this.getSchedulingSystem().getNewsList();
			n = tasks.size();
			for (int j = 0; j < n; j++) {
				Task task = tasks.elementAt(j);
				fs = new FileOutputStream(dir + (i + j) + ".xml");
				xs.toXML(task, fs);
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	public void loadAll() {

		String dir = this.getConfigurator().getIoDirectory();
		dir = dir + this.getConfigurator().getInputFile() + "/";

		Vector<Resource> allResources = new Vector<Resource>();
		Vector<Actor> actors = new Vector<Actor>();
		Vector<Resource> resources = new Vector<Resource>();
		Vector<Task> tasks = new Vector<Task>();

		XStream xs = new XStream(new DomDriver());

		try {

			int i = 0;
			FileInputStream fis = null;
			SchedulingSystem schedulingSystem = this.getSchedulingSystem();  

			String fileName = dir + i + ".xml";
			fis = new FileInputStream(fileName);
			Object o = xs.fromXML(fis);
			while ((o != null) && (o.getClass().getName().contains("Actor"))) {
				Actor actor = (Actor) o;
				actor.setSchedulingSystem(schedulingSystem);
				allResources.add(actor);
				actors.add((Actor) actor);
				i++;
				fileName = dir + i + ".xml";
				o = null;
				if (new File(fileName).exists()) {
					fis = new FileInputStream(fileName);
					o = xs.fromXML(fis);
				}
			}

			while ((o != null) && (o.getClass().getName().contains("Resource"))) {
				Resource resource = (Resource) o;
				resource.setSchedulingSystem(schedulingSystem);
				allResources.add(resource);
				resources.add((Resource) resource);
				i++;
				fileName = dir + i + ".xml";
				o = null;
				if (new File(fileName).exists()) {
					fis = new FileInputStream(fileName);
					o = xs.fromXML(fis);
				}
			}

			while ((o != null) && (o.getClass().getName().contains("Task"))) {
				Task task = (Task) o;
				task.setSchedulingSystem(schedulingSystem);
				tasks.add(task);
				i++;
				fileName = dir + i + ".xml";
				o = null;
				if (new File(fileName).exists()) {
					fis = new FileInputStream(fileName);
					o = xs.fromXML(fis);
				}
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		
		this.setResourcesList(resources);
		this.setActorsList(actors);
		this.setAllResourcesList(allResources);
		this.setTasksList(tasks);
		this.updateRelations();

	}

	public int getDeadline() {
		// TODO Auto-generated method stub
		return 80;
	}

	public Vector<Task> loadNewsList() {
		return this.getTasksList();
	}

	public Vector<Actor> loadActorsList() {
		return this.getActorsList();
	}

	public Vector<Resource> loadResourcesList() {
		return this.getResourcesList();
	}

	public void updateRelations() {

		Vector<Resource> allResList = this.getAllResourcesList();
		for (int i = 0; i < allResList.size(); i++) {
			Resource resource = allResList.elementAt(i);
			resource.getResources().clear();
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
	/*	
		Vector<Task> tasks = this.getTasksList();
		for (int i = 0; i < tasks.size(); i++) {
			Task task = tasks.elementAt(i);
			String contTaskId = task.getContTaskId();
			if(contTaskId!=null){
				task.setContingencyTask(null);
				boolean taskFound = false;
				int j = 0;
				while (!taskFound) {
					Task task2 = tasks.elementAt(j);
					String taskId = task2.getTaskId();
					if (contTaskId.equals(taskId)) {
						task.setContingencyTask(task2);
						taskFound = true;
					}else
						j++;
				}
			}
		}
*/
	}

}

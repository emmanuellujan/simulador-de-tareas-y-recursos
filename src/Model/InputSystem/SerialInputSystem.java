package Model.InputSystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

		// Serialize the object
		XStream xs = new XStream();

		// Write to a file in the file system
		try {
	
			String dir = this.getConfigurator().getIoDirectory();
			dir = dir + this.getConfigurator().getInputFile() + "/";
			
			FileOutputStream fs;
			// Vector<Resource> allResources = this.getAllResourcesList();
			Vector<Resource> allResources = this.getSchedulingSystem().getInputSystem().getAllResourcesList();
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

		/*
		 * String dir = this.getConfigurator().getIoDirectory(); dir = dir +
		 * this.getConfigurator().getInputFile() + "/"; // Vector<Resource>
		 * allResources = this.getAllResourcesList(); Vector<Resource>
		 * allResources = this.getSchedulingSystem()
		 * .getInputSystem().getAllResourcesList(); int n = allResources.size();
		 * int i=0; for (i = 0; i < n; i++) { Resource resource =
		 * allResources.elementAt(i); this.save(resource, dir + i + ".obj"); }
		 * 
		 * Vector<Task> tasks = this.getSchedulingSystem().getNewsList(); n =
		 * tasks.size(); for (int j = 0; j < n; j++) { Task task =
		 * tasks.elementAt(j); this.save(task, dir + (i+j) + ".obj"); }
		 */
	}

	public void loadAll() {

		String dir = this.getConfigurator().getIoDirectory();
		dir = dir + this.getConfigurator().getInputFile() + "/";

		Vector<Resource> allResources = this.getAllResourcesList();
		Vector<Actor> actors = this.getActorsList();
		Vector<Resource> resources = this.getResourcesList();
		Vector<Task> tasks = this.getTasksList();

		XStream xs = new XStream(new DomDriver());

		try {

			int i = 0;
			FileInputStream fis = new FileInputStream(dir + i + ".xml");
			Resource resource = null;
			xs.fromXML(fis, resource);
			while (resource != null) {
				allResources.add(resource);
				if (resource.getType().equals("actor"))
					actors.add((Actor) resource);
				else if (resource.getType().equals("artifact"))
					resources.add(resource);
				i++;
				fis = new FileInputStream(dir + i + ".xml");
				xs.fromXML(fis, resource);
			}

			i = 0;
			fis = new FileInputStream(dir + i + ".xml");
			Task task = null;
			xs.fromXML(fis, task);
			while (task != null) {
				tasks.add(task);
				i++;
				fis = new FileInputStream(dir + i + ".xml");
				xs.fromXML(fis, task);
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

		this.setResourcesList(resources);
		this.setActorsList(actors);
		this.setAllResourcesList(allResources);
		this.setTasksList(tasks);

		/*
		 * String dir = this.getConfigurator().getIoDirectory(); dir = dir +
		 * this.getConfigurator().getInputFile() + "/";
		 */

		/*
		 * Vector<Resource> allResources = this.getSchedulingSystem()
		 * .getInputSystem().getAllResourcesList(); Vector<Actor> actors =
		 * this.getSchedulingSystem() .getInputSystem().getActorsList();
		 * Vector<Resource> resources = this.getSchedulingSystem()
		 * .getInputSystem().getResourcesList();
		 */
		/*
		 * Vector<Resource> allResources = this.getAllResourcesList();
		 * Vector<Actor> actors = this.getActorsList(); Vector<Resource>
		 * resources = this.getResourcesList(); Vector<Task> tasks =
		 * this.getTasksList();
		 * 
		 * int i =0; Resource resource = (Resource) this.load(dir +i+ ".obj");
		 * while(resource!=null){ allResources.add(resource);
		 * if(resource.getType().equals("actor")) actors.add((Actor) resource);
		 * else if(resource.getType().equals("artifact"))
		 * resources.add(resource); i++; resource = (Resource) this.load(dir + i
		 * + ".obj"); }
		 * 
		 * i =0; Task task = (Task) this.load(dir +i+ ".obj");
		 * while(task!=null){ tasks.add(task); i++; task = (Task) this.load(dir
		 * + i + ".obj"); }
		 * 
		 * //this.getSchedulingSystem().getInputSystem().setResourcesList(resources
		 * );
		 * //this.getSchedulingSystem().getInputSystem().setActorsList(actors);
		 * //this.getSchedulingSystem().getInputSystem().setAllResourcesList(
		 * allResources);
		 * //this.getSchedulingSystem().getInputSystem().setTasksList(tasks);
		 * 
		 * this.setResourcesList(resources); this.setActorsList(actors);
		 * this.setAllResourcesList(allResources); this.setTasksList(tasks);
		 */

	}

	/*
	 * public boolean save(Object obj, String fileName) { try { FileOutputStream
	 * file = new FileOutputStream(fileName); ObjectOutputStream os; os = new
	 * ObjectOutputStream(file); os.writeObject(obj); os.close(); file.close();
	 * } catch (IOException e) { // e.printStackTrace(); return false; } return
	 * true; }
	 * 
	 * public Object load(String fileName) { System.out.println(fileName);
	 * Object obj = null; try { FileInputStream fis = new
	 * FileInputStream(fileName); ObjectInputStream ois = new
	 * ObjectInputStream(fis); obj = (Object) ois.readObject(); ois.close();
	 * fis.close(); } catch (ClassNotFoundException e) { e.printStackTrace(); }
	 * catch (FileNotFoundException e) { e.printStackTrace(); } catch
	 * (IOException e) { e.printStackTrace(); } return obj; }
	 */

	@Override
	public int getDeadline() {
		// TODO Auto-generated method stub
		return 80;
	}

	@Override
	public Vector<Task> loadNewsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Actor> loadActorsList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<Resource> loadResourcesList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRelations() {
		// TODO Auto-generated method stub

	}

}

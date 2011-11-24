
package Controller.SchedulingSystem;

import java.util.Vector;
import java.util.logging.Filter;

import Controller.FilterSystem.ActorRelationshipFilter;
import Controller.FilterSystem.JobPositionFilter;
import Controller.FilterSystem.NameFilter;
import Controller.FilterSystem.TaskFilter;
import Gui.Frames.NewsFrame;
import Gui.Frames.SimulatorFrame;

/**
 * 
 * Clase encargada de evaluar cuestiones relacionadas con el grafo o poder
 * realizar modificaciones en el mismo.
 * 
 * @author F.Rossi
 */
public class SystemServices {

	public SystemServices() {
	}

	public Vector<Resource> deleteResource(Vector<Resource> list,
			String resourceId) {
		for (int i = 0; i < list.size(); i++)
			if (list.elementAt(i).getResId().equals(resourceId)) {
				list.remove(i);
				return list;
			}
		return list;
	}

	public void deleteResourceRelation(String firstResourceId,
			String secondResourceId) {
		Vector<Resource> currentList = new Vector<Resource>();
		for (int i = 0; i < SimulatorFrame.getInstance().getMainResourcesList()
				.size(); i++) {
			if (SimulatorFrame.getInstance().getMainResourcesList()
					.elementAt(i).getResId().equals(firstResourceId)) {
				currentList = SimulatorFrame.getInstance()
						.getMainResourcesList().elementAt(i).getResources();// Recursos
																			// con
																			// los
																			// cuales
																			// se
																			// comunica
																			// este
																			// recurso
																			// actual
				SimulatorFrame
						.getInstance()
						.getMainResourcesList()
						.elementAt(i)
						.setResources(
								this.deleteResource(currentList,
										secondResourceId));
				NewsFrame.getInstance().setLabel("Relationship deleted!");
				NewsFrame.getInstance().setVisible(true);
			}
		}
	}

	public Vector<Resource> getResourceByJobPosition(Filter fJobPosition,
			String name) {
		Vector<Resource> result = new Vector<Resource>();
		for (int i = 0; i < SimulatorFrame.getInstance().getMainResourcesList()
				.size(); i++)
			if (((JobPositionFilter) fJobPosition).eval(SimulatorFrame
					.getInstance().getMainResourcesList().get(i)))
				result.add(SimulatorFrame.getInstance().getMainResourcesList()
						.get(i));

		return result;
	}

	public Vector<Resource> getResourceByMaxTasksCapacity(Filter fMaxTask,
			String name) {
		Vector<Resource> result = new Vector<Resource>();
		for (int i = 0; i < SimulatorFrame.getInstance().getMainResourcesList()
				.size(); i++)
			if (((TaskFilter) fMaxTask).eval(SimulatorFrame.getInstance()
					.getMainResourcesList().get(i)))
				result.add(SimulatorFrame.getInstance().getMainResourcesList()
						.get(i));

		return result;
	}

	public Vector<Resource> getResourceByName(Filter fName, String name) {
		Vector<Resource> result = new Vector<Resource>();
		for (int i = 0; i < SimulatorFrame.getInstance().getMainResourcesList()
				.size(); i++)
			if (((NameFilter) fName).eval(SimulatorFrame.getInstance()
					.getMainResourcesList().get(i)))
				result.add(SimulatorFrame.getInstance().getMainResourcesList()
						.get(i));

		return result;
	}

	/*
	 * Se considerá que un Actor, con el cual tendremos una relacion. Siempre
	 * será identificado con un nombre. De lo contrario si no fuese de esa
	 * forma, podría identificarse con el id.
	 */
	public Vector<Resource> getResourceByRelationResource(
			Filter fWorkerRelationship, String name) {
		Vector<Resource> result = new Vector<Resource>();
		for (int i = 0; i < SimulatorFrame.getInstance().getMainResourcesList()
				.size(); i++)
			if (((ActorRelationshipFilter) fWorkerRelationship)
					.eval(SimulatorFrame.getInstance().getMainResourcesList()
							.get(i))) {
				if (!result.contains(SimulatorFrame.getInstance()
						.getMainResourcesList().get(i))) {
					result.add(SimulatorFrame.getInstance()
							.getMainResourcesList().get(i));
				}
			}
		return result;
	}

	public void setResourceProperty(String resourceId, String property,
			String value) {
		for (int i = 0; i < SimulatorFrame.getInstance().getMainResourcesList()
				.size(); i++)
			if (SimulatorFrame.getInstance().getMainResourcesList()
					.elementAt(i).getResId().equals(resourceId)) {
				SimulatorFrame.getInstance().getMainResourcesList()
						.elementAt(i).setProperty(property, value);
				NewsFrame.getInstance().setLabel("Property updated!");
				NewsFrame.getInstance().setVisible(true);
			}
	}

}

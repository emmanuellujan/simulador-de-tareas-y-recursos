package LogicLayer.SchedulingSystem;

import java.util.Vector;
import java.util.logging.Filter;

import LogicLayer.FilterSystem.ActorRelationshipFilter;
import LogicLayer.FilterSystem.JobPositionFilter;
import PresentationLayer.Frames.ErrorFrame;
import PresentationLayer.Frames.NewsFrame;
import PresentationLayer.Frames.RelationFrame;
import PresentationLayer.Frames.SimulatorFrame;

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

	public Vector<String> deleteRelation(Vector<String> list,
			String firstResourceId, String secondResourceId) {
		for (int i = 0; i < list.size(); i++) {
			if ((((String) list.elementAt(i)).contains("Recurso: "
					+ firstResourceId))
					&& (((String) list.elementAt(i)).contains("con recurso: "
							+ secondResourceId))) {
				list.removeElementAt(i);
			}
		}

		return list;
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

	public Vector<String> deleteResourceId(Vector<String> list,
			String resourceId) {
		for (int i = 0; i < list.size(); i++)
			if (list.elementAt(i).equals(resourceId)) {
				list.remove(i);
				return list;
			}
		return list;
	}

	public void deleteResourceRelation(String firstResourceId,
			String secondResourceId) {
		Vector<Resource> currentList = new Vector<Resource>();
		Vector<String> currentIdsList = new Vector<String>();
		Vector<String> relations = new Vector<String>();
		int firtSize = 0;
		if (firstResourceId.equals(secondResourceId)) {
			SimulatorFrame.getInstance().setVisible(false);
			ErrorFrame.getInstance().setBackFrame("SimulatorFrame");
			ErrorFrame.getInstance().setLabel("Resources cannot be the equals");
			ErrorFrame.getInstance().setLocationRelativeTo(null);
			ErrorFrame.getInstance().setVisible(true);
		} else {
			for (int i = 0; i < SimulatorFrame.getInstance()
					.getMainResourcesList().size(); i++) {
				if (SimulatorFrame.getInstance().getMainResourcesList()
						.elementAt(i).getResId().equals(firstResourceId)) {
					Resource element = (Resource) SimulatorFrame.getInstance()
							.getMainResourcesList().elementAt(i);
					currentList = element.getResources();
					firtSize = ((Vector) element.getResources()).size();
					SimulatorFrame
							.getInstance()
							.getMainResourcesList()
							.elementAt(i)
							.setResources(
									this.deleteResource(currentList,
											secondResourceId));
					currentIdsList = SimulatorFrame.getInstance()
							.getMainResourcesList().elementAt(i)
							.getRelationsIds();
					SimulatorFrame
							.getInstance()
							.getMainResourcesList()
							.elementAt(i)
							.setRelationsIds(
									this.deleteResourceId(currentIdsList,
											secondResourceId));
					relations = RelationFrame.getInstance().getRelationsList();
					RelationFrame.getInstance().setRelationsList(
							this.deleteRelation(relations, firstResourceId,
									secondResourceId));

					SimulatorFrame.getInstance().setVisible(false);
					NewsFrame.getInstance().setBackFrame("SimulatorFrame");

					if (firtSize > SimulatorFrame.getInstance()
							.getMainResourcesList().elementAt(i).getResources()
							.size())
						NewsFrame.getInstance().setLabel(
								"Relationship deleted!");
					else
						NewsFrame.getInstance().setLabel(
								"Relationship doesn't exist");
					NewsFrame.getInstance().setLocationRelativeTo(null);
					NewsFrame.getInstance().setVisible(true);
				}
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
				SimulatorFrame.getInstance().setVisible(false);
				NewsFrame.getInstance().setBackFrame("SimulatorFrame");
				NewsFrame.getInstance().setLabel("Property updated!");
				NewsFrame.getInstance().setLocationRelativeTo(null);
				NewsFrame.getInstance().setVisible(true);
			}
	}

}

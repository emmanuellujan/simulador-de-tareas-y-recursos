package logicLayer.filterSystem;

import java.util.Vector;

import logicLayer.schedulingSystem.Resource;

/**
 * 
 * @author F.Rossi
 */
public class ActorRelationshipFilter extends SimpleFilter {

	public ActorRelationshipFilter(Object obj) {
		super(obj);
	}

	@Override
	public boolean eval(Object currentResource) {
		Vector<Resource> relationResources = ((Resource) currentResource)
				.getResources();
		for (int i = 0; i < relationResources.size(); i++) {
			if (relationResources.elementAt(i).equals(element))
				return true;
		}
		return false;
	}

}

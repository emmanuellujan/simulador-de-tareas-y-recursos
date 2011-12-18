package logicLayer.filterSystem;

import java.util.Vector;

import logicLayer.schedulingSystem.Resource;

public class ActorRelationshipFilter extends SimpleFilter {

	public ActorRelationshipFilter(Object obj) {
		super(obj);
	}

	@Override
	public boolean eval(Object currentResource) {
		Vector<Resource> relationResources = ((Resource) this.getElement())
				.getResources();
		for (int i = 0; i < relationResources.size(); i++) {
			if (relationResources.elementAt(i).equals(currentResource))
				return true;
		}
		return false;
	}

}

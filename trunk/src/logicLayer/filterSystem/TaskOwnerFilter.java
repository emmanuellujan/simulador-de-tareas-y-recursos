package logicLayer.filterSystem;

import logicLayer.schedulingSystem.Actor;
import logicLayer.schedulingSystem.Task;

public class TaskOwnerFilter extends SimpleFilter {

	public TaskOwnerFilter(Object obj) {
		super(obj);
	}

	@Override
	public boolean eval(Object a) {
		Actor r = ((Actor) a);
		Task t = (Task) this.getElement();
		if (r.owns(t))
			return true;
		else
			return false;
	}

}

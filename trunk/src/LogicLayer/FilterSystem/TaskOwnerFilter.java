package LogicLayer.FilterSystem;

import LogicLayer.SchedulingSystem.Task;
import LogicLayer.SchedulingSystem.Actor;

public class TaskOwnerFilter extends SimpleFilter {

	public TaskOwnerFilter(Object obj) {
		super(obj);
	}

	public boolean eval(Object a) {
		Actor r = ((Actor) a);
		Task t = (Task) this.getElement();
		if (r.owns(t))
			return true;
		else
			return false;
	}

}

package Controller.FilterSystem;

import Controller.SchedulingSystem.Actor;

public class TaskFilter extends SimpleFilter {

	public TaskFilter(Object obj) {
		super(obj);
	}

	public boolean eval(Object currentResource) {
		Object resourceMaxTasks = ((Actor) currentResource).getMaxTasksNumber();
		return (((Double) this.getElement()).doubleValue() > ((Double) resourceMaxTasks)
				.doubleValue());
	}

}

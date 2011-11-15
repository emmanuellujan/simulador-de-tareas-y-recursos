package Controller.SchedulingSystem;

import java.util.Enumeration;
import java.util.Hashtable;

import Controller.FilterSystem.Filter;

public class Updater {
	Hashtable<Filter,Update> updates;
	
	public Updater(){}

	public void update(Resource resource) {
		Hashtable<Filter,Update> updates = this.getUpdates();
		Enumeration<Filter> e = updates.keys();
		Filter filter;
		while (e.hasMoreElements()) {
			filter = e.nextElement();
			if(filter.eval(resource))
				updates.get(filter).update();
		}
	}

	public Hashtable<Filter, Update> getUpdates() {
		return updates;
	}

	public void setUpdates(Hashtable<Filter, Update> updates) {
		this.updates = updates;
	}
	
}

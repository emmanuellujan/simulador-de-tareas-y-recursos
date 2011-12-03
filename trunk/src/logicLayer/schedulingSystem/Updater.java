package logicLayer.schedulingSystem;

import java.util.Enumeration;
import java.util.Hashtable;

import logicLayer.filterSystem.Filter;


public class Updater {
	Hashtable<Filter, Update> updates;

	public Updater() {
		Hashtable<Filter, Update> updates = new Hashtable<Filter, Update>();
		this.setUpdates(updates);
	}

	public void addUpdate(Filter f, Update u) {
		this.updates.put(f, u);
	}

	public Hashtable<Filter, Update> getUpdates() {
		return updates;
	}

	public void setUpdates(Hashtable<Filter, Update> updates) {
		this.updates = updates;
	}

	public void update(Resource resource) {
		Hashtable<Filter, Update> updates = this.getUpdates();
		Enumeration<Filter> e = updates.keys();
		Filter filter;
		while (e.hasMoreElements()) {
			filter = e.nextElement();
			if (filter.eval(resource))
				updates.get(filter).update(resource);
		}
	}

}

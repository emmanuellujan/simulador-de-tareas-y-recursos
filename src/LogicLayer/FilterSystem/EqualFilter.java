package Controller.FilterSystem;

import Controller.SchedulingSystem.Resource;

/**
 * 
 * @author F.Rossi
 */
public class EqualFilter extends SimpleFilter {

	public EqualFilter(Object obj) {
		super(obj);
	}

	public boolean eval(Object r) {
		return this.getElement().equals((Resource) r);
	}
}

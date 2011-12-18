package logicLayer.filterSystem;

import logicLayer.schedulingSystem.Resource;

/**
 * 
 * @author F.Rossi
 */
public class EqualFilter extends SimpleFilter {

	public EqualFilter(Object obj) {
		super(obj);
	}

	@Override
	public boolean eval(Object r) {
		String id1 = ((Resource) this.getElement()).getResId();
		String id2 = ((Resource) r).getResId();
		return id1.equals(id2);
	}
}

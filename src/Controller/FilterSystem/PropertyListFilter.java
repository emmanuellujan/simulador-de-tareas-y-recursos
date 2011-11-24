
package Controller.FilterSystem;

import java.util.Vector;

import Controller.SchedulingSystem.Resource;

/**
 * 
 * @author F.Rossi
 */
public class PropertyListFilter extends Filter {

	protected Object key;
	protected Object value;
	protected Object resource;

	public PropertyListFilter(Object currentKey, Object currentValue,
			Object currentResource) {
		this.setKey(currentKey);
		this.setValue(currentValue);
		this.setResource(currentResource);
	}

	@Override
	public boolean eval(Object a) {
		Resource r1 = (Resource) a;
		Vector<Resource> elements = ((Vector<Resource>) a);
		Resource r2 = (Resource) this.getResource();
		String id = "";
		String key = "";
		String value = "";
		int n = elements.size();
		for (int i = 0; i < n; i++) {
			id = elements.elementAt(i).getResId();
			key = this.getKey().toString();
			value = this.getValue().toString();
			if ((r2.getResId().equals(id)) && r1.getProperty(key).equals(value))
				return true;
		}
		return false;
	}

	protected Object getKey() {
		return key;
	}

	protected Object getResource() {
		return resource;
	}

	protected Object getValue() {
		return value;
	}

	protected void setKey(Object elem) {
		this.key = elem;
	}

	protected void setResource(Object elem) {
		this.resource = elem;
	}

	protected void setValue(Object elem) {
		this.value = elem;
	}

}

package logicLayer.filterSystem;

import java.util.Vector;

import logicLayer.schedulingSystem.Resource;

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

	public boolean eval(Object a) {		
		Vector<Resource> elements = ((Vector<Resource>) a);
		Resource r1 = (Resource) this.getResource();
		Resource r2 = null;
		String id1 = r1.getResId();
		String id2 = "";
		String key = this.getKey().toString();
		String value = this.getValue().toString();
		int n = elements.size();
		for (int i = 0; i < n; i++) {
			r2 = elements.elementAt(i);
			id2 = r2.getResId();
			if ((id1.equals(id2)) && r2.getProperty(key).equals(value))
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

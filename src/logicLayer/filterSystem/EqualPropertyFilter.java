package logicLayer.filterSystem;

import logicLayer.schedulingSystem.Resource;

/**
 * 
 * @author F.Rossi
 */
public class EqualPropertyFilter extends PropertyFilter {

	public EqualPropertyFilter(Object currentKey, Object currentValue) {
		super(currentKey, currentValue);
	}

	public boolean eval(Object a) {
		Resource r = ((Resource) a);
		String key = (String) this.getKey();
		String value = (String) this.getValue();
		String v2 = r.getProperty(key);
		if (v2 != null)
			return v2.equals(value);
		else
			return false;
	}

}

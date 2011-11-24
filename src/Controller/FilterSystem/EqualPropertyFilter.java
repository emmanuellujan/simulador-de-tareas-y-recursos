
package Controller.FilterSystem;

import Controller.SchedulingSystem.Resource;

/**
 * 
 * @author F.Rossi
 */
public class EqualPropertyFilter extends PropertyFilter {

	public EqualPropertyFilter(Object currentKey, Object currentValue) {
		super(currentKey, currentValue);
	}

	@Override
	public boolean eval(Object a) {
		return (((Resource) a).getProperty(this.getKey().toString())
				.equals(this.getValue()));
	}

}

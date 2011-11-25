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
		//System.out.println(":"+(String)this.getKey()+" "+(String)this.getValue());
		return (((Resource) a).getProperty((String)this.getKey())
				.equals((String)this.getValue()));
	}

}

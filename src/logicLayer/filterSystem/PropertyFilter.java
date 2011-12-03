package logicLayer.filterSystem;

/**
 * 
 * @author F.Rossi
 */
public abstract class PropertyFilter extends Filter {

	protected Object key;
	protected Object value;

	public PropertyFilter(Object currentKey, Object currentValue) {
		this.setKey(currentKey);
		this.setValue(currentValue);
	}

	protected Object getKey() {
		return key;
	}

	protected Object getValue() {
		return value;
	}

	protected void setKey(Object elem) {
		this.key = elem;
	}

	protected void setValue(Object elem) {
		this.value = elem;
	}
}

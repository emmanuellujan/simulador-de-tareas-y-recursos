package Controller.FilterSystem;

/**
 * 
 * @author F.Rossi
 */
public abstract class ComplexFilter extends Filter {

	protected Filter filterA, filterB;

	public ComplexFilter(Filter fA, Filter fB) {
		this.setFilterA(fA);
		this.setFilterB(fB);
	}

	protected Filter getFilterA() {
		return filterA;
	}

	protected Filter getFilterB() {
		return filterB;
	}

	protected void setFilterA(Filter fA) {
		this.filterA = fA;
	}

	protected void setFilterB(Filter fB) {
		this.filterB = fB;
	}

}

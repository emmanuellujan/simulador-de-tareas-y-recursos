package Controller.FilterSystem;

/**
 * 
 * @author F.Rossi
 */
public class OrFilter extends ComplexFilter {

	public OrFilter(Filter fA, Filter fB) {
		super(fA, fB);
	}

	public boolean eval(Object r) {
		return filterA.eval(r) || filterB.eval(r);
	}

}

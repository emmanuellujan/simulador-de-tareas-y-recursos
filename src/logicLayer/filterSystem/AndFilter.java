package logicLayer.filterSystem;

/**
 * 
 * @author F.Rossi
 */
public class AndFilter extends ComplexFilter {

	public AndFilter(Filter fA, Filter fB) {
		super(fA, fB);
	}

	public boolean eval(Object r) {
		return filterA.eval(r) && filterB.eval(r);
	}

}

package LogicLayer.FilterSystem;
import LogicLayer.SchedulingSystem.Resource;

/**
 * 
 * @author F.Rossi
 */
public class JobPositionFilter extends SimpleFilter {

	public JobPositionFilter(Object obj) {
		super(obj);
	}

	@Override
	public boolean eval(Object currentResource) {
		Object resourceName = ((Resource) currentResource)
				.getProperty("Job Position");
		return this.getElement().equals(resourceName);
	}

}

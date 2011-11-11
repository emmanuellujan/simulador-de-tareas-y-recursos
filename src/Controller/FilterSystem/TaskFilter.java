package Controller.FilterSystem;

import Controller.SchedulingSystem.Actor;
import Controller.SchedulingSystem.Resource;

public class TaskFilter extends SimpleFilter{
    
    public TaskFilter(Object obj){
        super(obj);
    }

    public boolean eval(Resource currentResource) {
        Object resourceMaxTasks = ((Actor)currentResource).getMaxTasksNumber();
        return (((Double)this.getElement()).doubleValue() > ((Double)resourceMaxTasks).doubleValue());
    }
    
}

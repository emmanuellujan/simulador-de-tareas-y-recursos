/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.FilterSystem;

import Controller.SchedulingSystem.Actor;
import Controller.SchedulingSystem.Resource;

/**
 *
 * @author F.Rossi
 */
public class TaskFilter extends SimpleFilter{
    
    public TaskFilter(Object obj){
        super(obj);
    }

    @Override
    public boolean eval(Resource currentResource) {
        Object resourceMaxTasks = ((Actor)currentResource).getTaskMaxSize();
        return (((Double)this.getElement()).doubleValue() > ((Double)resourceMaxTasks).doubleValue());
    }
    
}

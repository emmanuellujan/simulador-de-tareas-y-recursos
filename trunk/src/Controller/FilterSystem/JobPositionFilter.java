/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.FilterSystem;

import Controller.SchedulingSystem.Resource;

/**
 *
 * @author F.Rossi
 */
public class JobPositionFilter extends SimpleFilter{
    
    public JobPositionFilter(Object obj){
        super(obj);
    }

    @Override
    public boolean eval(Resource currentResource) {
        Object resourceName = currentResource.getProperty("Job Position");
        return this.getElement().equals(resourceName);
    }
    
}

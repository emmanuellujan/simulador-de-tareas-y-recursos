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
public class NameFilter extends SimpleFilter{
    
    public NameFilter(Object obj){
        super(obj);
    }

    @Override
    public boolean eval(Object currentResource) {
        Object resourceName = ((Resource)currentResource).getProperty("Name");
        return this.getElement().equals(resourceName);
    }
    
}

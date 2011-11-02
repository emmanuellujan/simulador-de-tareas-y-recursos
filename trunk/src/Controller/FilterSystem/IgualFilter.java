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
public class IgualFilter extends SimpleFilter{
    
    public IgualFilter(Object obj){   
        super(obj);  
    }
    
    public boolean eval(Resource r) {
        return this.getElement().equals(r); 
    }
    
}

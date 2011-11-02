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
public class OrFilter extends ComplexFilter {
    
    public OrFilter(Filter fA, Filter fB){   
        super(fA,fB);  
    }
    
    public boolean eval(Resource r) {
        return filterA.eval(r) || filterB.eval(r); 
    }
         
}

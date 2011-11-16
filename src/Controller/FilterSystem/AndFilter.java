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
public class AndFilter extends ComplexFilter{
    
    public AndFilter(Filter fA, Filter fB){   
        super(fA,fB);  
    }
    
    public boolean eval(Object r) {
        return filterA.eval(r) && filterB.eval(r); 
    }
    
}

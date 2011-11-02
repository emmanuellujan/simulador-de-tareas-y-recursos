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
public abstract class Filter {
    
    public abstract boolean eval(Resource a);
    
}

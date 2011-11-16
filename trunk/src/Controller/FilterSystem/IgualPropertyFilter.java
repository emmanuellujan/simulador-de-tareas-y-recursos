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
public class IgualPropertyFilter extends PropertyFilter{
    
    public IgualPropertyFilter(Object currentKey, Object currentValue){
        super(currentKey,currentValue);
    }

    @Override
    public boolean eval(Object a) {
        return  (((Resource)a).getProperty(this.getKey().toString()).equals(this.getValue()));
    }
    
}

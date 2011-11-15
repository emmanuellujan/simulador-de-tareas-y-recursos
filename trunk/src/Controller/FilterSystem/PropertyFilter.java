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
public abstract class PropertyFilter extends Filter{
    
    protected Object key; 
    protected Object value; 
    
    public PropertyFilter(Object currentKey, Object currentValue){
        this.setKey(currentKey);
        this.setValue(currentValue);
    }
    
    protected Object getKey() {
        return key;
    }

    protected void setKey(Object elem) {
	this.key = elem;
    }
    
    protected Object getValue() {
        return value;
    }

    protected void setValue(Object elem) {
	this.value = elem;
    }
}

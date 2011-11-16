/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.FilterSystem;

import Controller.SchedulingSystem.Resource;
import java.util.Vector;

/**
 *
 * @author F.Rossi
 */
public class ListPropertyFilter extends Filter{
    
    protected Object key; 
    protected Object value; 
    protected Object resource;
    
    public ListPropertyFilter(Object currentKey, Object currentValue, Object currentResource){
        this.setKey(currentKey);
        this.setValue(currentValue);
        this.setResource(currentResource);
    }
    
    protected Object getResource() {
        return resource;
    }

    protected void setResource(Object elem) {
	this.resource = elem;
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

    @Override
    public boolean eval(Object a) {
        Vector<Resource> elements = ((Vector)a);
        for(int i = 0; i<elements.size(); i++){
            if((this.getResource().equals(elements.elementAt(i)))&&((Resource)a).getProperty(this.getKey().toString()).equals(this.getValue()))
                return true;
        }        
        return false; 
    }
    
}

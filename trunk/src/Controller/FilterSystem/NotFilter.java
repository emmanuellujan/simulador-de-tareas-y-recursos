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
public abstract class NotFilter extends Filter{
    
    protected Filter filter;
    
    public NotFilter(Filter nFilter){
        this.setFilter(nFilter);
    }
    
    public boolean eval(Resource r){
        return this.getFilter().eval(r);
    }
    
    protected Filter getFilter() {
        return filter;
    }

    protected void setFilter(Filter nfilter) {
	this.filter = nfilter;
    }
    
}

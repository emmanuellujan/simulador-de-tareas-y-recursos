/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.SchedulingSystem;

import Controller.FilterSystem.NameFilter;
import java.util.Vector;
import java.util.logging.Filter;

/**
 *
 * @author F.Rossi
 */
public class SystemServices {
    
    private Vector<Resource> resourcesList;
    
    public SystemServices(Vector<Resource> resources) {
        this.setResourcesList(resources);  
    }
    
    public Vector<Resource> getResourcesList() {
        return resourcesList;
    }
	
    public void setResourcesList(Vector<Resource> resources) {
	this.resourcesList = resources;
    }
    
    public Vector<Resource> getResourceByName(Filter fName, String name){
        Vector<Resource> result = new Vector<Resource>();
        for(int i = 0;i<this.getResourcesList().size();i++)
            if(((NameFilter)fName).eval(this.getResourcesList().get(i)))
               result.add(this.getResourcesList().get(i));         
        return result;
    }
    
    public Vector<Resource> getResourceByMaxTasksCapacity(){
        return null;
    }
    
    public Vector<Resource> getResourceByJobPosition(){
        return null;
    }
    
    public Vector<Resource> getResourceByRelationWorker(){
        return null;
    }
    
    public Vector<Resource> getResourceByRelationArtifact(){
        return null;
    }
    
}

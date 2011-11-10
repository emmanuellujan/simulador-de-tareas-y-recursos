/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.SchedulingSystem;

import Controller.FilterSystem.ActorRelationshipFilter;
import Controller.FilterSystem.JobPositionFilter;
import Controller.FilterSystem.NameFilter;
import Controller.FilterSystem.TaskFilter;
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

    public Vector<Resource> getResourceByMaxTasksCapacity(Filter fMaxTask, String name){
        Vector<Resource> result = new Vector<Resource>();
        for(int i = 0;i<this.getResourcesList().size();i++)
            if(((TaskFilter)fMaxTask).eval(this.getResourcesList().get(i)))
               result.add(this.getResourcesList().get(i));         
        return result;
    }
    
    public Vector<Resource> getResourceByJobPosition(Filter fJobPosition, String name){
        Vector<Resource> result = new Vector<Resource>();
        for(int i = 0;i<this.getResourcesList().size();i++)
            if(((JobPositionFilter)fJobPosition).eval(this.getResourcesList().get(i)))
               result.add(this.getResourcesList().get(i));         
        return result;
    }
    
    /*
     * Se considerá que un Actor, con el cual tendremos una relacion. Siempre será
     * identificado con un nombre. De lo contrario si no fuese de esa forma, podría 
     * identificarse con el id.
     */
    public Vector<Resource> getResourceByRelationResource(Filter fWorkerRelationship, String name){
        Vector<Resource> result = new Vector<Resource>();
        for(int i = 0;i<this.getResourcesList().size();i++)
            if(((ActorRelationshipFilter)fWorkerRelationship).eval(this.getResourcesList().get(i))){
                if(!result.contains(this.getResourcesList().get(i))){
                    result.add(this.getResourcesList().get(i));   
                }                     
            }   
        return result;
    }
    
}

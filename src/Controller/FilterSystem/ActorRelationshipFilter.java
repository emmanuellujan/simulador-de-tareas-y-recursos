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
public class ActorRelationshipFilter extends SimpleFilter{
    
    public ActorRelationshipFilter(Object obj){
        super(obj);
    }
 
    @Override
    public boolean eval(Resource currentResource) {
        Vector<Resource> relationResources = currentResource.getResources();         
        for(int i = 0;i<relationResources.size();i++){
            if(relationResources.elementAt(i).equals(element))
                return true;
        }        
        return false;
    }
    
}

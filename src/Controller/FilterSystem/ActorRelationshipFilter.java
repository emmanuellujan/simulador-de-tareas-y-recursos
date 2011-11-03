/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.FilterSystem;

import Controller.SchedulingSystem.Relations;
import Controller.SchedulingSystem.Resource;
import java.util.Vector;
import javax.annotation.Resources;
import javax.management.relation.Relation;

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
        Relations elementRelations = currentResource.getRelations();
        Vector<Resource> relationResources = elementRelations.getResources();
        for(int i = 0;i<relationResources.size();i++){
            if(relationResources.elementAt(i).equals(element))
                return true;
        }        
        return false;
    }
    
}

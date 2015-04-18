package com.rokuan.calliopecore.sentence.structure;


/**
 * Created by LEBEAU Christophe on 27/02/2015.
 */
public abstract class NominalGroup {	
    public enum GroupType {
    	PRONOUN,
    	NOMINAL_GROUP,
    	VERB,
    	ABSTRACT
    }
    
    private GroupType type;
    
    protected NominalGroup(GroupType ty){
    	type = ty;
    }
    
    public GroupType getType(){
    	return type;
    }
}

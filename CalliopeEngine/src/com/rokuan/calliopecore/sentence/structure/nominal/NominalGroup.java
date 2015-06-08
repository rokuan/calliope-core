package com.rokuan.calliopecore.sentence.structure.nominal;


/**
 * Created by LEBEAU Christophe on 27/02/2015.
 */
public abstract class NominalGroup {	
    public enum GroupType {
    	PRONOUN,
    	COMPLEMENT,
    	VERB,
    	ABSTRACT,
    	LANGUAGE,
    	STATE,
    	PLACE,
    	PERSON
    }
    
    private GroupType type;
    
    protected NominalGroup(GroupType ty){
    	type = ty;
    }
    
    public GroupType getType(){
    	return type;
    }
}

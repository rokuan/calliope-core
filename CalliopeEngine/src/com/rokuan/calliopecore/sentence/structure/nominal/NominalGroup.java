package com.rokuan.calliopecore.sentence.structure.nominal;

import com.google.gson.annotations.Expose;


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
    	PERSON,
    	COLOR,
    	NUMBER,
    	DATE,
    	OBJECT
    }

	@Expose
    private GroupType groupType;
    
    protected NominalGroup(GroupType ty){
    	groupType = ty;
    }
    
    public GroupType getType(){
    	return groupType;
    }
}

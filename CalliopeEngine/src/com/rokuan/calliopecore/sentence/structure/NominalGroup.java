package com.rokuan.calliopecore.sentence.structure;


/**
 * Created by LEBEAU Christophe on 27/02/2015.
 */
public abstract class NominalGroup {	
    public enum TargetType {
    	PRONOUN,
    	NOMINAL_GROUP,
    	VERB
    }
    
    private TargetType type;
    
    protected NominalGroup(TargetType ty){
    	type = ty;
    }
    
    public TargetType getType(){
    	return type;
    }
}

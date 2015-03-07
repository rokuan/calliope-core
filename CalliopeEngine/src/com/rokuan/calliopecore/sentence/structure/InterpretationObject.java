package com.rokuan.calliopecore.sentence.structure;

import com.rokuan.calliopecore.sentence.Type.Pronoun;

/**
 * Created by LEBEAU Christophe on 17/02/2015.
 */
public abstract class InterpretationObject {
    public enum RequestType {
        ORDER,
        QUESTION
    }

    //public EngineAction action;
    //public Verb verb;
    public RequestType type;
    public Enum<?> action;
    public Target subject = new Target(Pronoun.TU);	// Calliope
    public Target target;	
    //public List<ComplementObject> what;
    public ComplementObject what;

    protected InterpretationObject(RequestType t){
        type = t;
    }
    
    @Override
    public String toString(){
    	StringBuilder result = new StringBuilder();
    	
    	result.append("{ type :");
    	result.append('"');
    	result.append(type);
    	result.append('"');
    	result.append(", ");
    	result.append("action : ");
    	result.append('"');
    	result.append(action);
    	result.append('"');
    	result.append(", ");
    	result.append("subject: ");
    	result.append(subject);
    	//result.append(null);
    	
    	return result.toString();
    }
}

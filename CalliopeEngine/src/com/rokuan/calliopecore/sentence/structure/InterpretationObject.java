package com.rokuan.calliopecore.sentence.structure;

import java.util.ArrayList;
import java.util.List;

import com.rokuan.calliopecore.sentence.Adjective;
import com.rokuan.calliopecore.sentence.Type.Pronoun;
import com.rokuan.calliopecore.sentence.structure.data.count.AllItemsObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeObject;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;
import com.rokuan.calliopecore.sentence.structure.nominal.PronounTarget;

/**
 * Created by LEBEAU Christophe on 17/02/2015.
 */
public abstract class InterpretationObject {
    public enum RequestType {
        ORDER,
        QUESTION,
        AFFIRMATION
    }

    //public EngineAction action;
    //public Verb verb;
    private RequestType type;
    public Enum<?> action;
    public NominalGroup subject = new PronounTarget(Pronoun.TU);	// Calliope
    public NominalGroup target;	
    public CountObject count = new AllItemsObject();
	public List<Adjective> acjectives = new ArrayList<Adjective>();
	public NominalGroup what;
    public TimeObject when;
    //public ComplementObject why;
    public NominalGroup where;
    public NominalGroup how; 
    
    protected InterpretationObject(RequestType t){
        type = t;
    }
    
    public String getDescription(){
    	// TODO:
    	String leftPart = (action == null) ? "" : action.toString();
    	String rightPart = (what == null) ? "" : what.toString();
    	//String rightPart = (what == null) ? "" : what;
    	return leftPart + ':' + rightPart;
    }
    
    @Override
    public String toString(){
    	StringBuilder result = new StringBuilder();
    	
    	result.append("type=");
    	result.append(type);
    	result.append(";");
    	result.append("action=");
    	result.append(action);
    	result.append(";");
    	result.append("what=");
    	result.append(what);
    	
    	return result.toString();
    }
    
    public RequestType getType(){
    	return type;
    }
}

package com.rokuan.calliopecore.sentence.structure;

import java.util.ArrayList;
import java.util.List;

import com.rokuan.calliopecore.sentence.Adjective;
import com.rokuan.calliopecore.sentence.Type.Pronoun;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.criteria.CriterionObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeObject;

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
    public Target subject = new Target(Pronoun.TU);	// Calliope
    public Target target;	
    //public List<ComplementObject> what;
    //public ComplementObject what;
    public CountObject count = new CountObject();
	public List<Adjective> acjectives = new ArrayList<Adjective>();
    public String what = "";
    public ComplementObject of;
    public String to;
    public TimeObject when;
    public ComplementObject why;
    public PlaceObject where;
    public String how;
    public List<CriterionObject> criteria = new ArrayList<CriterionObject>();    
    
    protected InterpretationObject(RequestType t){
        type = t;
    }
    
    public String getDescription(){
    	// TODO:
    	String leftPart = (action == null) ? "" : action.toString();
    	//String rightPart = (what == null) ? "" : what.object;
    	String rightPart = (what == null) ? "" : what;
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

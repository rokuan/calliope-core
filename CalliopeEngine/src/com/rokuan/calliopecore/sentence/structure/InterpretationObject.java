package com.rokuan.calliopecore.sentence.structure;

/**
 * Created by LEBEAU Christophe on 17/02/2015.
 */
public class InterpretationObject {
    public enum RequestType {
        ORDER,
        QUESTION
    }


    //public EngineAction action;
    //public Verb verb;
    public RequestType type;
    public Enum<?> action;
    public Target subject;
    public Target target;
    //public List<ComplementObject> what;
    public ComplementObject what;


    public InterpretationObject(){

    }

    protected InterpretationObject(RequestType t){
        type = t;
    }
}

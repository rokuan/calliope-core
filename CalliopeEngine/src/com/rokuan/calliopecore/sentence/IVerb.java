package com.rokuan.calliopecore.sentence;


/**
 * Created by LEBEAU Christophe on 19/02/2015.
 */
public interface IVerb {
    String getName();   
    
    boolean hasAction(Action.ActionType action);
}

package com.rokuan.calliopecore.sentence;

import java.util.Set;

import com.rokuan.calliopecore.sentence.Action.ActionType;


/**
 * Created by LEBEAU Christophe on 19/02/2015.
 */
public interface IVerb {
    String getValue();   
    
    boolean hasAction(Action.ActionType action);
    
    Set<ActionType> getActions();
}

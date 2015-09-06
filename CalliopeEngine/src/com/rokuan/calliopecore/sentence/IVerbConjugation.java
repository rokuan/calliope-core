package com.rokuan.calliopecore.sentence;

import com.rokuan.calliopecore.sentence.Type.Form;

/**
 * Created by LEBEAU Christophe on 19/02/2015.
 */
public interface IVerbConjugation {	
    public enum Tense {
        PAST,
        PRESENT,
        FUTURE
    }
	
    IVerb getVerb();

    Form getForm();

	boolean does(Action.ActionType action);
}

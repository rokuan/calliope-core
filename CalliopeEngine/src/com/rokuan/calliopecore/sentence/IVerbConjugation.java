package com.rokuan.calliopecore.sentence;


/**
 * Created by LEBEAU Christophe on 19/02/2015.
 */
public interface IVerbConjugation {	
    public enum Tense {
        PAST,
        PRESENT,
        FUTURE
    }

    public enum Form {
        INDICATIVE,
        CONDITIONAL,
        SUBJUNCTIVE,
        IMPERATIVE,
        INFINITIVE,
        PARTICIPLE,
    }
	
    IVerb getVerb();

    Form getForm();
    
    Tense getTense();

	boolean does(Action.ActionType action);
}

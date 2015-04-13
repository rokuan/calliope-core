package com.rokuan.calliopecore.sentence;

/**
 * Created by LEBEAU Christophe on 19/02/2015.
 */
public class VerbConjugation {
    //private Verb.Tense tense;
    protected Verb.ConjugationTense tense;
    protected Verb.Form form;
    protected Verb.Pronoun pronoun;
    protected String name;
    protected Verb<?> verb;
    
    public VerbConjugation(){
    	
    }
    
    public VerbConjugation(Verb.ConjugationTense conjugTense, Verb.Form conjugForm, Verb.Pronoun conjugPerson, String conjugValue, Verb<?> conjugVerb){
    	tense = conjugTense;
    	form = conjugForm;
    	pronoun = conjugPerson;
    	name = conjugValue;
    	verb = conjugVerb;
    }
    
    public Verb<?> getVerb() {
        return verb;
    }

    public void setVerb(Verb<?> verb) {
        this.verb = verb;
    }
}

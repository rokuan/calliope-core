package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LEBEAU Christophe on 19/02/2015.
 */
@DatabaseTable(tableName = "conjugations")
public class VerbConjugation {
	@DatabaseField(id = true)
	private int id;
	@DatabaseField
    protected String name;
	@DatabaseField(uniqueCombo = true, foreign = true)
    protected Verb verb;
	@DatabaseField(uniqueCombo = true)
    protected Verb.ConjugationTense tense;
	@DatabaseField(uniqueCombo = true)
    protected Verb.Form form;
	@DatabaseField(uniqueCombo = true)
    protected Verb.Pronoun pronoun;
    
    public VerbConjugation(){
    	
    }
    
    public VerbConjugation(Verb.ConjugationTense conjugTense, Verb.Form conjugForm, Verb.Pronoun conjugPerson, String conjugValue, Verb conjugVerb){
    	tense = conjugTense;
    	form = conjugForm;
    	pronoun = conjugPerson;
    	name = conjugValue;
    	verb = conjugVerb;
    }

    public Verb getVerb() {
        return verb;
    }

    public void setVerb(Verb verb) {
        this.verb = verb;
    }
}

package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by LEBEAU Christophe on 19/02/2015.
 */
@DatabaseTable(tableName = "conjugations")
public class VerbConjugation {
	public static final String VALUE_FIELD_NAME = "value";
	public static final String VERB_FIELD_NAME = "verb";
	public static final String TENSE_FIELD_NAME = "tense";
	public static final String FORM_FIELD_NAME = "form";
	public static final String PERSON_FIELD_NAME = "pronoun"; 
	
	@DatabaseField(id = true)
	private int id;
	@DatabaseField(columnName = VALUE_FIELD_NAME)
    protected String name;
	@DatabaseField(columnName = VERB_FIELD_NAME, uniqueCombo = true, foreign = true)
    protected Verb verb;
	@DatabaseField(columnName = TENSE_FIELD_NAME, uniqueCombo = true)
    protected Verb.ConjugationTense tense;
	@DatabaseField(columnName = FORM_FIELD_NAME, uniqueCombo = true)
    protected Verb.Form form;
	@DatabaseField(columnName = PERSON_FIELD_NAME, uniqueCombo = true)
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

package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.Action.VerbAction;

/**
 * Created by LEBEAU Christophe on 19/02/2015.
 */
@DatabaseTable(tableName = "verbs")
public class Verb {
    public enum Tense {
        PAST,
        PRESENT,
        FUTURE
    }

    public enum ConjugationTense {
        PRESENT,
        PAST,
        PERFECT, //PASSE_COMPOSE,
        IMPERFECT, //IMPARFAIT,
        PAST_PERFECT, //PLUS_QUE_PARFAIT,
        SIMPLE_PAST, //PASSE_SIMPLE,
        PLUPERFECT, //PASSE_ANTERIEUR
        FUTURE,
        FUTURE_ANTERIOR //FUTUR_ANTERIEUR
    }

    public enum Form {
        INDICATIVE,
        CONDITIONAL,
        SUBJUNCTIVE,
        IMPERATIVE,
        INFINITIVE,
        PARTICIPLE,
    }

    public enum Pronoun {
        JE,
        TU,
        IL_ELLE_ON,
        NOUS,
        VOUS,
        ILS_ELLES,
        UNDEFINED
    }
    
    public static final String ID_FIELD_NAME = "verb";
    public static final String ACTION_FIELD_NAME = "action";
    public static final String AUXILIARY_FIELD_NAME = "auxiliary";
    
    @DatabaseField(columnName = ID_FIELD_NAME, id = true)
    protected String verb = "";
    @DatabaseField(columnName = ACTION_FIELD_NAME)
    protected Action.VerbAction action = VerbAction.UNDEFINED;
    @DatabaseField(columnName = AUXILIARY_FIELD_NAME)
    protected boolean auxiliary = false;
    
    public Verb(){
    	
    }
    
    public Verb(String infinitiveForm, Action.VerbAction verbAction, boolean aux){
    	verb = infinitiveForm;
    	action = verbAction;
    	auxiliary = aux;
    }

    public String getVerb() {
        return verb;
    }

    public Action.VerbAction getAction() {
        return action;
    }

    public boolean isAuxiliary() {
        return auxiliary;
    }
}

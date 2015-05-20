package com.rokuan.calliopecore.sentence;

/**
 * Created by LEBEAU Christophe on 19/02/2015.
 */
public class Verb<ActionType extends Enum<ActionType>> {
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
    
    protected String verb = "";
    protected Enum<ActionType> action;
    protected boolean auxiliary = false;
    
    public Verb(){
    	
    }
    
    public Verb(String infinitiveForm, Enum<ActionType> verbAction, boolean aux){
    	verb = infinitiveForm;
    	action = verbAction;
    	auxiliary = aux;
    }

    public String getVerb() {
        return verb;
    }

    public Enum<ActionType> getAction() {
        return action;
    }

    public boolean isAuxiliary() {
        return auxiliary;
    }
}

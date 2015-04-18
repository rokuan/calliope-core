package com.rokuan.calliopecore.sentence;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by LEBEAU Christophe on 22/02/2015.
 */
public class Word {
    public enum WordType {
    	ANY,
        PROPER_NAME,
        COMMON_NAME,
        ADVERB,
        VERB,
        ADJECTIVE,
        ONOMATOPEIA,
        PREPOSITION,
        CONJUNCTION,
        AUXILIARY,
        DEMONSTRATIVE_PRONOUN,
        DEFINITE_ARTICLE,
        INDEFINITE_ADJECTIVE,
        INDEFINITE_PRONOUN,
        INTERROGATIVE_PRONOUN,
        RELATIVE_PRONOUN,
        NUMERICAL_ADJECTIVE,
        NUMBER,
        NUMERICAL_POSITION,
        DEMONSTRATIVE_ADJECTIVE,
        PERSONAL_PRONOUN,
        INDEFINITE_ARTICLE,
        POSSESSIVE_PRONOUN,
        POSSESSIVE_ADJECTIVE,
        EUPHONIOUS_LINK,
        INTERROGATIVE_ADJECTIVE,
        PREPOSITION_FROM,
        PREPOSITION_TO,
        DATE_MONTH,
        DATE,
        DATE_UNIT,
        TIME,
        // New
        QUANTITY,
        PREPOSITION_BETWEEN,	// entre
        PREPOSITION_AND,	// et
        PREPOSITION_AT,	// à/au
        PREPOSITION_OF,	// de
        PREPOSITION_IN,	// en
        PREPOSITION_WITH,	// avec
        CONJUGATION_LINK,	// t
        SUPERLATIVE,	// moins/plus
        TARGET_PRONOUN,	// moi/toi/me/te/...
        DATE_UNIT_HOUR,	// midi/minuit
        MEAN_OF_TRANSPORT, // pied/voiture/bus/avion
        PLACE_TYPE,	// restaurant/cinéma/...
        CITY,
        COUNTRY,
        FIRSTNAME
    }

    //private List<WordType> types = new ArrayList<WordType>();
    private Set<WordType> types = new HashSet<WordType>();
    private String value;
    private VerbConjugation verbInfo;
    
    public Word(String v, WordType t){
        types.add(t);
        value = v;
    }

    public Word(String v, List<WordType> ts){
        if(ts != null){
            types.addAll(ts);
        }

        value = v;
    }

    public Word(String v, WordType... ts){
        for(WordType t: ts){
            types.add(t);
        }

        value = v;
    }

    public boolean isOfType(WordType type){
    	if(type == WordType.ANY){
    		return true;
    	}
    	
        return types.contains(type);
    }

    public String getValue() {
        return value;
    }
    
    public VerbConjugation getVerbInfo() {
		return verbInfo;
	}

	public void setVerbInfo(VerbConjugation verbInfo) {
		this.verbInfo = verbInfo;
	}

	public Set<WordType> getTypes(){
		return types;
	}
}

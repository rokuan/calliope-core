package com.rokuan.calliopecore.sentence.structure.nominal;

import com.rokuan.calliopecore.content.INominalObject;


/**
 * Created by LEBEAU Christophe on 27/02/2015.
 */
public abstract class NominalGroup implements INominalObject {	
    public enum GroupType {
    	PRONOUN,
    	COMPLEMENT,
    	VERB,
    	ABSTRACT,
    	LANGUAGE,
    	PLACE,
    	PERSON,
    	COLOR,
    	NUMBER,
    	DATE,
    	OBJECT,
    	ADDITIONAL_PLACE,
    	NAMED_PLACE,
    	CITY,
    	COUNTRY,
    	LOCATION
    }
}

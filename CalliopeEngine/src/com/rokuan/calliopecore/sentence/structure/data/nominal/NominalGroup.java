package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.place.AdditionalPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.LocationObject;
import com.rokuan.calliopecore.sentence.structure.data.place.NamedPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.time.SingleTimeObject;


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
    	//PLACE,
    	PERSON,
    	COLOR,
    	NUMBER,
    	PHONE_NUMBER,
    	DATE,
    	OBJECT,
    	ADDITIONAL_PLACE,
    	NAMED_PLACE,
    	CITY,
    	COUNTRY,
    	LOCATION
    }
    
    public static Class<? extends INominalObject> getClassFromGroupType(GroupType ty){
    	Class<? extends INominalObject> clazz = null;
    	
    	switch(ty){
		case ABSTRACT:
			clazz = AbstractTarget.class;
			break;
		case COMPLEMENT:
			clazz = ComplementObject.class;
			break;
		case COLOR:
			// TODO
			break;
		case LANGUAGE:
			clazz = LanguageObject.class;
			break;
		case NUMBER:
			// TODO
			break;
		case OBJECT:
			clazz = AdditionalObject.class;
			break;
		case PERSON:
			clazz = AdditionalPerson.class;
			break;				
		case PRONOUN:
			clazz = PronounTarget.class;
			break;
		case LOCATION:
			clazz = LocationObject.class;
			break;
		case VERB:
			clazz = VerbalGroup.class;
			break;
		//case PLACE:
		case DATE:
			clazz = SingleTimeObject.class;
			break;
		case ADDITIONAL_PLACE:
			clazz = AdditionalPlaceObject.class;
			break;
		case CITY:
			clazz = CityObject.class;
			break;
		case COUNTRY:
			clazz = CountryObject.class;
			break;
		case NAMED_PLACE:
			clazz = NamedPlaceObject.class;
			break;		
		case PHONE_NUMBER:
			clazz = PhoneNumberObject.class;
			break;
		}
    	
    	return clazz;
    }
}

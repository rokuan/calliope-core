package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.place.AdditionalPlace;
import com.rokuan.calliopecore.sentence.structure.data.place.LocationObject;
import com.rokuan.calliopecore.sentence.structure.data.place.NamedPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.time.SingleTimeObject;


/**
 * Created by LEBEAU Christophe on 27/02/2015.
 */
public abstract class NominalGroup implements INominalObject {	
    public enum GroupType {
    	PRONOUN,
    	COMMON_NAME,
    	VERB,
    	ABSTRACT,
    	LANGUAGE,
    	//PLACE,
    	PERSON,
    	ADDITIONAL_PERSON,
    	COLOR,
    	NUMBER,
    	PHONE_NUMBER,
    	DATE,
    	OBJECT,
    	ADDITIONAL_PLACE,
    	NAMED_PLACE,
    	PLACE_TYPE,
    	CITY,
    	COUNTRY,
    	LOCATION,
    	UNIT,
    	CHARACTER,
    	QUANTITY
    }
    
    public static Class<? extends INominalObject> getClassFromGroupType(GroupType ty){
    	Class<? extends INominalObject> clazz = null;
    	
    	switch(ty){
		case ABSTRACT:
			clazz = AbstractTarget.class;
			break;
		case COMMON_NAME:
			clazz = NameObject.class;
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
			clazz = PersonObject.class;
			break;
		case ADDITIONAL_PERSON:
			clazz = AdditionalPerson.class;
			break;				
		case PRONOUN:
			clazz = PronounSubject.class;
			break;
		case LOCATION:
			clazz = LocationObject.class;
			break;
		case VERB:
			clazz = VerbalGroup.class;
			break;
		case DATE:
			clazz = SingleTimeObject.class;
			break;
		case ADDITIONAL_PLACE:
			clazz = AdditionalPlace.class;
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
		case UNIT:
			clazz = UnitObject.class;
			break;
		case CHARACTER:
			clazz = CharacterObject.class;
			break;
		case PLACE_TYPE:
			clazz = PlaceObject.class;
			break;
		case QUANTITY:
			clazz = QuantityObject.class;
			break;
		}
    	
    	return clazz;
    }
}

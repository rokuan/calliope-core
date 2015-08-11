package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.place.AdditionalPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.LocationObject;
import com.rokuan.calliopecore.sentence.structure.data.place.NamedPlaceObject;
import com.rokuan.calliopecore.sentence.structure.nominal.AbstractTarget;
import com.rokuan.calliopecore.sentence.structure.nominal.AdditionalObject;
import com.rokuan.calliopecore.sentence.structure.nominal.AdditionalPerson;
import com.rokuan.calliopecore.sentence.structure.nominal.CityObject;
import com.rokuan.calliopecore.sentence.structure.nominal.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.nominal.CountryObject;
import com.rokuan.calliopecore.sentence.structure.nominal.LanguageObject;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;
import com.rokuan.calliopecore.sentence.structure.nominal.PronounTarget;
import com.rokuan.calliopecore.sentence.structure.nominal.VerbalGroup;

public class NominalGroupDeserializer implements JsonDeserializer<INominalObject> {
	@Override
	public INominalObject deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		GsonBuilder builder = new GsonBuilder();
		
		builder.registerTypeAdapter(NominalGroup.class, new NominalGroupDeserializer());
		builder.registerTypeAdapter(CountObject.class, new CountObjectDeserializer());
		
		Gson gson = builder.create();
		Class<? extends INominalObject> clazz = null;
		
		switch(NominalGroup.GroupType.valueOf(arg0.getAsJsonObject().get("groupType").getAsString())){
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
		case PLACE:
		case DATE:
			// TODO: ?
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
		default:
			break;			
		}
		
		return gson.fromJson(arg0, clazz);
	}    	
}

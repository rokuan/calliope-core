package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeObject;
import com.rokuan.calliopecore.sentence.structure.nominal.AbstractTarget;
import com.rokuan.calliopecore.sentence.structure.nominal.AdditionalObject;
import com.rokuan.calliopecore.sentence.structure.nominal.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.nominal.LanguageObject;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;
import com.rokuan.calliopecore.sentence.structure.nominal.PronounTarget;
import com.rokuan.calliopecore.sentence.structure.nominal.VerbalGroup;

public class NominalGroupDeserializer implements JsonDeserializer<NominalGroup> {
	@Override
	public NominalGroup deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(PlaceObject.class, new PlaceObjectDeserializer());
		builder.registerTypeAdapter(TimeObject.class, new TimeObjectDeserializer());
		builder.registerTypeAdapter(NominalGroup.class, new NominalGroupDeserializer());
		builder.registerTypeAdapter(CountObject.class, new CountObject.CountObjectDeserializer());
		
		Gson gson = builder.create();
		Class<? extends NominalGroup> clazz = null;
		
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
		case DATE:
			clazz = TimeObject.class;
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
			// TODO
			break;
		case PLACE:
			clazz = PlaceObject.class;
			break;				
		case PRONOUN:
			clazz = PronounTarget.class;
			break;
		case STATE:
			// TODO:
			break;
		case VERB:
			clazz = VerbalGroup.class;
			break;
		}
		
		return gson.fromJson(arg0, clazz);
	}    	
}

package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.structure.data.place.AdditionalPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.MonumentObject;
import com.rokuan.calliopecore.sentence.structure.data.place.NominalPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial;
import com.rokuan.calliopecore.sentence.structure.data.place.StateObject;


public class PlaceObjectDeserializer implements JsonDeserializer<PlaceAdverbial> {
	@Override
	public PlaceAdverbial deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		GsonBuilder builder = new GsonBuilder();
		
		Gson gson = builder.create();
		Class<? extends PlaceAdverbial> clazz = null;
		
		switch(PlaceAdverbial.PlaceType.valueOf(arg0.getAsJsonObject().get("placeType").getAsString())){
		case CUSTOM:
			clazz = AdditionalPlaceObject.class;
			break;
		case NOMINAL:
			clazz = NominalPlaceObject.class;
			break;
		case MONUMENT:
			clazz = MonumentObject.class;
			break;
		case STATE:
			clazz = StateObject.class;
			break;
		}
		
		return gson.fromJson(arg0, clazz);
	}
}
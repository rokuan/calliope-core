package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial;


public class PlaceAdverbialDeserializer implements JsonDeserializer<IPlaceObject> {
	@Override
	public IPlaceObject deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		//GsonBuilder builder = new GsonBuilder();
		GsonBuilder builder = FullGsonBuilder.getDeserializationGsonBuilder();
		Gson gson = builder.create();
		Class<? extends IPlaceObject> clazz = PlaceAdverbial.getClassFromPlaceType(PlaceAdverbial.PlaceType.valueOf(arg0.getAsJsonObject().get("placeType").getAsString()));
		return gson.fromJson(arg0, clazz);
	}
}
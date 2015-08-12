package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial;

public class PlaceAdverbialSerializer implements JsonSerializer<IPlaceObject> {

	@Override
	public JsonElement serialize(IPlaceObject arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonElement result = null;
		Class<? extends IPlaceObject> clazz = PlaceAdverbial.getClassFromPlaceType(arg0.getPlaceType());
		GsonBuilder builder = FullGsonBuilder.getSerializationGsonBuilder();
		
		result = builder.create().toJsonTree(arg0, clazz);		
		result.getAsJsonObject().add("placeType", new JsonPrimitive(arg0.getPlaceType().toString()));
		
		return result;
	}

}

package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.IPlaceInfo;

public class PlaceSerializer implements JsonSerializer<IPlaceInfo> {

	@Override
	public JsonElement serialize(IPlaceInfo arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();
		obj.add("value", new JsonPrimitive(arg0.getValue()));
		obj.add("placeCategory", new JsonPrimitive(arg0.getPlaceCategory().name()));
		return obj;
	}

}

package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.ICityInfo;
import com.rokuan.calliopecore.sentence.ICityInfo.Location;

public class CitySerializer implements JsonSerializer<ICityInfo> {

	@Override
	public JsonElement serialize(ICityInfo arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();
		obj.add("value", new JsonPrimitive(arg0.getValue()));
		obj.add("location", new Gson().toJsonTree(arg0.getLocation(), Location.class));
		return obj;
	}
	
}

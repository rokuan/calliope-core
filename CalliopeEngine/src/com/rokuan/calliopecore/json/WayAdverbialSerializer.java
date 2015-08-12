package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial;

public class WayAdverbialSerializer implements JsonSerializer<IWayObject> {

	@Override
	public JsonElement serialize(IWayObject arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonElement result = null;
		Class<? extends IWayObject> clazz = WayAdverbial.getClassFromWayType(arg0.getWayType());		
		GsonBuilder builder = FullGsonBuilder.getSerializationGsonBuilder();
		
		result = builder.create().toJsonTree(arg0, clazz);		
		result.getAsJsonObject().add("wayType", new JsonPrimitive(arg0.getWayType().toString()));
		
		return result;
	}

}

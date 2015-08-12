package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial;

public class TimeAdverbialSerializer implements JsonSerializer<ITimeObject>{

	@Override
	public JsonElement serialize(ITimeObject arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonElement result = null;
		Class<? extends ITimeObject> clazz = TimeAdverbial.getClassFromTimeType(arg0.getTimeType());
		GsonBuilder builder = FullGsonBuilder.getSerializationGsonBuilder();
		
		result = builder.create().toJsonTree(arg0, clazz);		
		result.getAsJsonObject().add("timeType", new JsonPrimitive(arg0.getTimeType().toString()));
		
		return result;
	}

}

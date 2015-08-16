package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial;


public class TimeAdverbialDeserializer implements JsonDeserializer<ITimeObject> {
	@Override
	public ITimeObject deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		GsonBuilder builder = FullGsonBuilder.getDeserializationGsonBuilder();
		Gson gson = builder.create();
		Class<? extends ITimeObject> clazz = TimeAdverbial.getClassFromTimeType(TimeAdverbial.TimeType.valueOf(arg0.getAsJsonObject().get("timeType").getAsString()));
		return gson.fromJson(arg0, clazz);
	}    	
}

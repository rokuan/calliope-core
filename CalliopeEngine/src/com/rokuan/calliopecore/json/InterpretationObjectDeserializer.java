package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;

public class InterpretationObjectDeserializer implements JsonDeserializer<InterpretationObject> {
	@Override
	public InterpretationObject deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		GsonBuilder builder = FullGsonBuilder.getDeserializationGsonBuilder();		
		Gson gson = builder.create();
		Class<? extends InterpretationObject> clazz = InterpretationObject.getClassFromRequestType(InterpretationObject.RequestType.valueOf(arg0.getAsJsonObject().get("requestType").getAsString()));
		return gson.fromJson(arg0, clazz);
	}    	
}

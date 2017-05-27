package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;

public class InterpretationObjectSerialization {
	private static final String JSON_REQUEST_TYPE_KEY = "request_type";
	
	public static class Serializer implements JsonSerializer<InterpretationObject> {

		@Override
		public JsonElement serialize(InterpretationObject arg0, Type arg1,
				JsonSerializationContext arg2) {
			Class<? extends InterpretationObject> clazz = InterpretationObject.getClassFromRequestType(arg0.getRequestType());
			GsonBuilder builder = FullGsonBuilder.getSerializationGsonBuilder();	
			JsonObject obj = builder.create().toJsonTree(arg0, clazz).getAsJsonObject();			
			obj.add(JSON_REQUEST_TYPE_KEY, new JsonPrimitive(arg0.getRequestType().name()));
			return obj;
		}
		
	}
	
	public static class Deserializer implements JsonDeserializer<InterpretationObject> {
		
		@Override
		public InterpretationObject deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			GsonBuilder builder = FullGsonBuilder.getDeserializationGsonBuilder();		
			Gson gson = builder.create();
			Class<? extends InterpretationObject> clazz = InterpretationObject.getClassFromRequestType(InterpretationObject.RequestType.valueOf(arg0.getAsJsonObject().get(JSON_REQUEST_TYPE_KEY).getAsString()));
			return gson.fromJson(arg0, clazz);
		}
		
	}
}

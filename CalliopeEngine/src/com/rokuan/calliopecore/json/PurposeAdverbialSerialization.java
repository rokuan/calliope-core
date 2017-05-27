package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial;

public class PurposeAdverbialSerialization {
	private static final String JSON_PURPOSE_TYPE_KEY = "purpose_type";
	
	public static class Serializer implements JsonSerializer<IPurposeObject> {

		@Override
		public JsonElement serialize(IPurposeObject arg0, Type arg1,
				JsonSerializationContext arg2) {
			JsonElement result;
			Class<? extends IPurposeObject> clazz = PurposeAdverbial.getClassFromPurposeType(arg0.getPurposeType());
			GsonBuilder builder = FullGsonBuilder.getSerializationGsonBuilder();
			
			result = builder.create().toJsonTree(arg0, clazz);		
			result.getAsJsonObject().add(JSON_PURPOSE_TYPE_KEY, new JsonPrimitive(arg0.getPurposeType().name()));
			
			return result;
		}

	}
	
	public static class Deserializer implements JsonDeserializer<IPurposeObject> {
		
		@Override
		public IPurposeObject deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			GsonBuilder builder = FullGsonBuilder.getDeserializationGsonBuilder();
			Gson gson = builder.create();
			Class<? extends IPurposeObject> clazz = PurposeAdverbial.getClassFromPurposeType(PurposeAdverbial.PurposeType.valueOf(arg0.getAsJsonObject().get(JSON_PURPOSE_TYPE_KEY).getAsString()));
			return gson.fromJson(arg0, clazz);
		}
		
	}
}

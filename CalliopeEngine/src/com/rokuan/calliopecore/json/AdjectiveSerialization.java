package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.IAdjectiveInfo;
import com.rokuan.calliopecore.sentence.IAdjectiveInfo.AdjectiveValue;

public class AdjectiveSerialization {
	private static final String JSON_VALUE_KEY = "value";
	private static final String JSON_ADJECTIVE_TYPE_KEY = "adjectiveType";
	
	public static class Serializer implements JsonSerializer<IAdjectiveInfo> {

		@Override
		public JsonElement serialize(IAdjectiveInfo arg0, Type arg1,
				JsonSerializationContext arg2) {
			JsonObject obj = new JsonObject();		
			obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
			obj.add(JSON_ADJECTIVE_TYPE_KEY, new JsonPrimitive(arg0.getAdjectiveType().name()));
			return obj;
		}

	}
	
	public static class Deserializer implements JsonDeserializer<IAdjectiveInfo> {

		@Override
		public IAdjectiveInfo deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			JsonObject obj = arg0.getAsJsonObject();
			
			final String value = obj.get(JSON_VALUE_KEY).getAsString();
			final AdjectiveValue adjType = AdjectiveValue.valueOf(obj.get(JSON_ADJECTIVE_TYPE_KEY).getAsString());
			
			return new IAdjectiveInfo() {				
				@Override
				public String getValue() {
					return value;
				}
				
				@Override
				public AdjectiveValue getAdjectiveType() {
					return adjType;
				}
			};
		}

	}
}

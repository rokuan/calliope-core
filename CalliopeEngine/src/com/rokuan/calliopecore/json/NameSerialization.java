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
import com.rokuan.calliopecore.sentence.INameInfo;

public class NameSerialization {
	private static final String JSON_VALUE_KEY = "value";
	private static final String JSON_TAG_KEY = "name_tag";
	
	public static class Serializer implements JsonSerializer<INameInfo> {

		@Override
		public JsonElement serialize(INameInfo arg0, Type arg1,
				JsonSerializationContext arg2) {
			JsonObject obj = new JsonObject();
			obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
			obj.add(JSON_TAG_KEY, new JsonPrimitive(arg0.getNameTag()));
			return obj;
		}

	}
	
	public static class Deserializer implements JsonDeserializer<INameInfo> {

		@Override
		public INameInfo deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			JsonObject obj = arg0.getAsJsonObject();
			
			final String value = obj.get(JSON_VALUE_KEY).getAsString();
			final String nameTag = obj.get(JSON_TAG_KEY).getAsString();
					
			return new INameInfo() {
				@Override
				public String getValue() {
					return value;
				}
				
				@Override
				public String getNameTag() {
					return nameTag;
				}
			};
		}

	}
}

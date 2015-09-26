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
import com.rokuan.calliopecore.sentence.ICountryInfo;

public class CountrySerialization {
	private static final String JSON_VALUE_KEY = "value";
	private static final String JSON_COUNTRY_CODE_KEY = "countryCode"; 
	
	public static class Serializer implements JsonSerializer<ICountryInfo> {

		@Override
		public JsonElement serialize(ICountryInfo arg0, Type arg1,
				JsonSerializationContext arg2) {
			JsonObject obj = new JsonObject();
			obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
			obj.add(JSON_COUNTRY_CODE_KEY, new JsonPrimitive(arg0.getCountryCode()));
			return obj;
		}

	}
	
	public static class Deserializer implements JsonDeserializer<ICountryInfo> {

		@Override
		public ICountryInfo deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			JsonObject obj = arg0.getAsJsonObject();
			
			final String value = obj.get(JSON_VALUE_KEY).getAsString();
			final String countryCode = obj.get(JSON_COUNTRY_CODE_KEY).getAsString();
			
			return new ICountryInfo() {
				@Override
				public String getValue() {
					return value;
				}
				
				@Override
				public String getCountryCode() {
					return countryCode;
				}
			};
		}

	}
}

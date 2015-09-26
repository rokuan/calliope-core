package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.ICityInfo;
import com.rokuan.calliopecore.sentence.ICityInfo.Location;

public class CitySerialization {
	private static final String JSON_VALUE_KEY = "value";
	private static final String JSON_LOCATION_KEY = "location";
	
	public static class Serializer implements JsonSerializer<ICityInfo> {

		@Override
		public JsonElement serialize(ICityInfo arg0, Type arg1,
				JsonSerializationContext arg2) {
			JsonObject obj = new JsonObject();
			obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
			obj.add(JSON_LOCATION_KEY, new Gson().toJsonTree(arg0.getLocation(), Location.class));
			return obj;
		}
		
	}
	
	public static class Deserializer implements JsonDeserializer<ICityInfo> {

		@Override
		public ICityInfo deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			JsonObject obj = arg0.getAsJsonObject();
			
			final String value = obj.get(JSON_VALUE_KEY).getAsString();
			final Location location = new Gson().fromJson(obj.get(JSON_LOCATION_KEY), Location.class);
			
			return new ICityInfo() {
				@Override
				public String getValue() {
					return value;
				}
				
				@Override
				public Location getLocation() {
					return location;
				}
			};
		}

	}
}

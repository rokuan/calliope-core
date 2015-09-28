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
import com.rokuan.calliopecore.sentence.IPronoun;
import com.rokuan.calliopecore.sentence.IPronoun.PronounSource;

public class PronounSerialization {
	private static final String JSON_VALUE_KEY = "value";
	private static final String JSON_SOURCE_KEY = "source";
	
	public static class Serializer implements JsonSerializer<IPronoun>{

		@Override
		public JsonElement serialize(IPronoun arg0, Type arg1,
				JsonSerializationContext arg2) {
			JsonObject obj = new JsonObject();	
			obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
			obj.add(JSON_SOURCE_KEY, new JsonPrimitive(arg0.getSource().name()));		
			return obj;
		}

	}
	
	public static class Deserializer implements JsonDeserializer<IPronoun>{

		@Override
		public IPronoun deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			JsonObject obj = arg0.getAsJsonObject();
			
			final String value = obj.get(JSON_VALUE_KEY).getAsString();
			final PronounSource source = PronounSource.valueOf(obj.get(JSON_SOURCE_KEY).getAsString());
			
			return new IPronoun() {
				@Override
				public String getValue() {
					return value;
				}
				
				@Override
				public PronounSource getSource() {
					return source;
				}				
			};
		}

	}
}

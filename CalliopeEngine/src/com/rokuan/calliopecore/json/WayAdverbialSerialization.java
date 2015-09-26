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
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial;

public class WayAdverbialSerialization {
	private static final String JSON_WAY_TYPE_KEY = "wayType";
	
	public static class Serializer implements JsonSerializer<IWayObject> {

		@Override
		public JsonElement serialize(IWayObject arg0, Type arg1,
				JsonSerializationContext arg2) {
			JsonElement result = null;
			Class<? extends IWayObject> clazz = WayAdverbial.getClassFromWayType(arg0.getWayType());		
			GsonBuilder builder = FullGsonBuilder.getSerializationGsonBuilder();
			
			result = builder.create().toJsonTree(arg0, clazz);		
			result.getAsJsonObject().add(JSON_WAY_TYPE_KEY, new JsonPrimitive(arg0.getWayType().name()));
			
			return result;
		}

	}
	
	public static class Deserializer implements JsonDeserializer<IWayObject> {
		
		@Override
		public IWayObject deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			GsonBuilder builder = FullGsonBuilder.getDeserializationGsonBuilder();
			Gson gson = builder.create();
			Class<? extends IWayObject> clazz = WayAdverbial.getClassFromWayType(WayAdverbial.WayType.valueOf(arg0.getAsJsonObject().get(JSON_WAY_TYPE_KEY).getAsString()));		
			return gson.fromJson(arg0, clazz);
		}
		
	}
}

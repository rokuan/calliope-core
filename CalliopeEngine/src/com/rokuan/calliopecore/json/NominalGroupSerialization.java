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
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NominalGroup;

public class NominalGroupSerialization {
	private static final String JSON_GROUP_TYPE_KEY = "group_type";
	
	public static class Serializer implements JsonSerializer<INominalObject> {
		@Override
		public JsonElement serialize(INominalObject arg0, Type arg1, JsonSerializationContext arg2) {
			JsonElement result;
			Class<? extends INominalObject> clazz = NominalGroup.getClassFromGroupType(arg0.getGroupType());
			GsonBuilder builder = FullGsonBuilder.getSerializationGsonBuilder();
			
			result = builder.create().toJsonTree(arg0, clazz);		
			result.getAsJsonObject().add(JSON_GROUP_TYPE_KEY, new JsonPrimitive(arg0.getGroupType().toString()));
			
			return result;
		}
	}
	
	public static class Deserializer implements JsonDeserializer<INominalObject> {
		@Override
		public INominalObject deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
			GsonBuilder builder = FullGsonBuilder.getDeserializationGsonBuilder();		
			Gson gson = builder.create();
			Class<? extends INominalObject> clazz = NominalGroup.getClassFromGroupType(NominalGroup.GroupType.valueOf(arg0.getAsJsonObject().get(JSON_GROUP_TYPE_KEY).getAsString()));		
			return gson.fromJson(arg0, clazz);
		}
	}
}

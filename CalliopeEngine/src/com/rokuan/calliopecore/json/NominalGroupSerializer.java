package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NominalGroup;

public class NominalGroupSerializer implements JsonSerializer<INominalObject> {

	@Override
	public JsonElement serialize(INominalObject arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonElement result = null;
		Class<? extends INominalObject> clazz = NominalGroup.getClassFromGroupType(arg0.getGroupType());
		GsonBuilder builder = FullGsonBuilder.getSerializationGsonBuilder();
		
		result = builder.create().toJsonTree(arg0, clazz);		
		result.getAsJsonObject().add("groupType", new JsonPrimitive(arg0.getGroupType().toString()));
		
		return result;
	}

}

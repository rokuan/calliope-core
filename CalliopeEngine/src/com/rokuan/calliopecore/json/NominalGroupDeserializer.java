package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NominalGroup;

public class NominalGroupDeserializer implements JsonDeserializer<INominalObject> {
	@Override
	public INominalObject deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		GsonBuilder builder = FullGsonBuilder.getDeserializationGsonBuilder();		
		Gson gson = builder.create();
		Class<? extends INominalObject> clazz = NominalGroup.getClassFromGroupType(NominalGroup.GroupType.valueOf(arg0.getAsJsonObject().get("groupType").getAsString()));		
		return gson.fromJson(arg0, clazz);
	}    	
}

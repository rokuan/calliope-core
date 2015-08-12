package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial;

public class PurposeAdverbialSerializer implements JsonSerializer<IPurposeObject> {

	@Override
	public JsonElement serialize(IPurposeObject arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonElement result = null;
		Class<? extends IPurposeObject> clazz = PurposeAdverbial.getClassFromPurposeType(arg0.getPurposeType());
		GsonBuilder builder = FullGsonBuilder.getSerializationGsonBuilder();
		
		result = builder.create().toJsonTree(arg0, clazz);		
		result.getAsJsonObject().add("purposeType", new JsonPrimitive(arg0.getPurposeType().toString()));
		
		return result;
	}

}

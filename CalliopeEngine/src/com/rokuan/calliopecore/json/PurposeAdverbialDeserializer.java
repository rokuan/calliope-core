package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial;

public class PurposeAdverbialDeserializer implements JsonDeserializer<IPurposeObject> {
	@Override
	public IPurposeObject deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		/*GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(INominalObject.class, new NominalGroupDeserializer());*/
		GsonBuilder builder = FullGsonBuilder.getDeserializationGsonBuilder();
		Gson gson = builder.create();
		Class<? extends IPurposeObject> clazz = PurposeAdverbial.getClassFromPurposeType(PurposeAdverbial.PurposeType.valueOf(arg0.getAsJsonObject().get("purposeType").getAsString()));
		return gson.fromJson(arg0, clazz);
	}  
}

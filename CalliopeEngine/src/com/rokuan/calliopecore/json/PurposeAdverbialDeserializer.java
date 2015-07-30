package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial;
import com.rokuan.calliopecore.sentence.structure.data.purpose.VerbalPurposeObject;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;

public class PurposeAdverbialDeserializer implements JsonDeserializer<PurposeAdverbial> {
	@Override
	public PurposeAdverbial deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(NominalGroup.class, new NominalGroupDeserializer());
		
		Gson gson = builder.create();
		Class<? extends PurposeAdverbial> clazz = null;
		
		switch(PurposeAdverbial.PurposeType.valueOf(arg0.getAsJsonObject().get("purposeType").getAsString())){
		case VERBAL:
			clazz = VerbalPurposeObject.class;
			break;
		}
		
		return gson.fromJson(arg0, clazz);
	}  
}

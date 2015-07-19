package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.structure.AffirmationObject;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;
import com.rokuan.calliopecore.sentence.structure.OrderObject;
import com.rokuan.calliopecore.sentence.structure.QuestionObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;

public class InterpretationObjectDeserializer implements JsonDeserializer<InterpretationObject> {
	@Override
	public InterpretationObject deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(CountObject.class, new CountObject.CountObjectDeserializer());
		builder.registerTypeAdapter(NominalGroup.class, new NominalGroupDeserializer());
		Gson gson = builder.create();
		Class<? extends InterpretationObject> clazz = null;

		switch(InterpretationObject.RequestType.valueOf(arg0.getAsJsonObject().get("requestType").getAsString())){
		case ORDER:
			clazz = OrderObject.class;
			break;
		case AFFIRMATION:
			clazz = AffirmationObject.class;
			break;
		case QUESTION:
			clazz = QuestionObject.class;
			break;
		}

		return gson.fromJson(arg0, clazz);
	}    	
}

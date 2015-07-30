package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.structure.data.time.RelativeTimeObject;
import com.rokuan.calliopecore.sentence.structure.data.time.SingleTimeObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial;
import com.rokuan.calliopecore.sentence.structure.data.time.TimePeriodObject;
import com.rokuan.calliopecore.sentence.structure.data.time.VerbalTimeObject;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;


public class TimeAdverbialDeserializer implements JsonDeserializer<TimeAdverbial> {
	@Override
	public TimeAdverbial deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(NominalGroup.class, new NominalGroupDeserializer());
		
		Gson gson = builder.create();
		Class<? extends TimeAdverbial> clazz = null;
		
		switch(TimeAdverbial.TimeType.valueOf(arg0.getAsJsonObject().get("timeType").getAsString())){
		case PERIOD:
			clazz = TimePeriodObject.class;
			break;
		case RELATIVE:
			clazz = RelativeTimeObject.class;
			break;
		case SINGLE:
			clazz = SingleTimeObject.class;
			break;
		case VERBAL:
			clazz = VerbalTimeObject.class;
			break;
		}
		
		return gson.fromJson(arg0, clazz);
	}    	
}

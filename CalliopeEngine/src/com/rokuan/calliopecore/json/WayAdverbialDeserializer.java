package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;
import com.rokuan.calliopecore.sentence.structure.way.AdditionalMode;
import com.rokuan.calliopecore.sentence.structure.way.ColorWayObject;
import com.rokuan.calliopecore.sentence.structure.way.LanguageWayObject;
import com.rokuan.calliopecore.sentence.structure.way.NominalWayObject;
import com.rokuan.calliopecore.sentence.structure.way.VerbalWayObject;
import com.rokuan.calliopecore.sentence.structure.way.WayAdverbial;

public class WayAdverbialDeserializer implements JsonDeserializer<WayAdverbial> {
	@Override
	public WayAdverbial deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		GsonBuilder builder = new GsonBuilder();
		
		builder.registerTypeAdapter(NominalGroup.class, new NominalGroupDeserializer());
		
		Gson gson = builder.create();
		Class<? extends WayAdverbial> clazz = null;
		
		switch(WayAdverbial.WayType.valueOf(arg0.getAsJsonObject().get("wayType").getAsString())){
		case CUSTOM:
			clazz = AdditionalMode.class;
			break;
		case LANGUAGE:
			clazz = LanguageWayObject.class;
			break;
		case NOMINAL:
			clazz = NominalWayObject.class;
			break;
		case VERBAL:
			clazz = VerbalWayObject.class;
			break;
		case COLOR:
			clazz = ColorWayObject.class;
			break;
		}
		
		return gson.fromJson(arg0, clazz);
	}   
}
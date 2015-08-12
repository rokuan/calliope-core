package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;

public class InterpretationObjectSerializer implements JsonSerializer<InterpretationObject> {

	@Override
	public JsonElement serialize(InterpretationObject arg0, Type arg1,
			JsonSerializationContext arg2) {
		Class<? extends InterpretationObject> clazz = InterpretationObject.getClassFromRequestType(arg0.getRequestType());
		/*GsonBuilder builder = new GsonBuilder();
		
		builder.registerTypeAdapter(INominalObject.class, new NominalGroupSerializer());
		builder.registerTypeAdapter(IPlaceObject.class, new PlaceAdverbialSerializer());
		builder.registerTypeAdapter(ITimeObject.class, new TimeAdverbialSerializer());
		builder.registerTypeAdapter(IWayObject.class, new WayAdverbialSerializer());
		builder.registerTypeAdapter(IPurposeObject.class, new PurposeAdverbialSerializer());*/
		GsonBuilder builder = FullGsonBuilder.getSerializationGsonBuilder();
		// TODO: ajouter les autres serializers
		
		return builder.create().toJsonTree(arg0, clazz);
	}
	
}

package com.rokuan.calliopecore.json;

import com.google.gson.GsonBuilder;
import com.rokuan.calliopecore.sentence.structure.InterpretationObject;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;

public class FullGsonBuilder {
	public static GsonBuilder getSerializationGsonBuilder(){
		GsonBuilder builder = new GsonBuilder();
		
		builder.excludeFieldsWithoutExposeAnnotation();
		builder.registerTypeAdapter(InterpretationObject.class, new InterpretationObjectSerializer());
		builder.registerTypeAdapter(INominalObject.class, new NominalGroupSerializer());
		builder.registerTypeAdapter(IPlaceObject.class, new PlaceAdverbialSerializer());
		builder.registerTypeAdapter(ITimeObject.class, new TimeAdverbialSerializer());
		builder.registerTypeAdapter(IWayObject.class, new WayAdverbialSerializer());
		builder.registerTypeAdapter(IPurposeObject.class, new PurposeAdverbialSerializer());
		
		return builder;
	}
	
	public static GsonBuilder getDeserializationGsonBuilder(){
		GsonBuilder builder = new GsonBuilder();
		
		builder.excludeFieldsWithoutExposeAnnotation();
		builder.registerTypeAdapter(InterpretationObject.class, new InterpretationObjectDeserializer());
		builder.registerTypeAdapter(INominalObject.class, new NominalGroupDeserializer());
		builder.registerTypeAdapter(IPlaceObject.class, new PlaceAdverbialDeserializer());
		builder.registerTypeAdapter(ITimeObject.class, new TimeAdverbialDeserializer());
		builder.registerTypeAdapter(IWayObject.class, new WayAdverbialDeserializer());
		builder.registerTypeAdapter(IPurposeObject.class, new PurposeAdverbialDeserializer());
		builder.registerTypeAdapter(CountObject.class, new CountObjectDeserializer());
		
		return builder;
	}
}

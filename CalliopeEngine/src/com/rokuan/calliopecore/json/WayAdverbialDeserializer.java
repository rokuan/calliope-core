package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial;

public class WayAdverbialDeserializer implements JsonDeserializer<IWayObject> {
	@Override
	public IWayObject deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		/*GsonBuilder builder = new GsonBuilder();

		builder.excludeFieldsWithoutExposeAnnotation();
		builder.registerTypeAdapter(INominalObject.class, new NominalGroupDeserializer());
		builder.registerTypeAdapter(IPlaceObject.class, new PlaceAdverbialDeserializer());
		builder.registerTypeAdapter(ITimeObject.class, new TimeAdverbialDeserializer());
		builder.registerTypeAdapter(IWayObject.class, new WayAdverbialDeserializer());*/
		GsonBuilder builder = FullGsonBuilder.getDeserializationGsonBuilder();
		Gson gson = builder.create();
		Class<? extends IWayObject> clazz = WayAdverbial.getClassFromWayType(WayAdverbial.WayType.valueOf(arg0.getAsJsonObject().get("wayType").getAsString()));		
		return gson.fromJson(arg0, clazz);
	}   
}
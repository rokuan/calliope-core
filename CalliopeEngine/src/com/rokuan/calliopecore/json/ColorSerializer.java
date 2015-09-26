package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.IColorInfo;

public class ColorSerializer implements JsonSerializer<IColorInfo> {

	@Override
	public JsonElement serialize(IColorInfo arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();
		obj.add("value", new JsonPrimitive(arg0.getValue()));
		obj.add("hexCode", new JsonPrimitive(arg0.getColorHexCode()));
		return obj;
	}

}

package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.IUnitInfo;

public class UnitSerializer implements JsonSerializer<IUnitInfo> {

	@Override
	public JsonElement serialize(IUnitInfo arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();
		obj.add("value", new JsonPrimitive(arg0.getValue()));
		obj.add("unitType", new JsonPrimitive(arg0.getUnitType().name()));
		return obj;
	}

}

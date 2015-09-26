package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.IPronoun;

public class PronounSerializer implements JsonSerializer<IPronoun>{

	@Override
	public JsonElement serialize(IPronoun arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();		
		obj.add("source", new JsonPrimitive(arg0.getSource().name()));		
		return obj;
	}

}

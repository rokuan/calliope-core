package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.ICharacterInfo;

public class CharacterSerializer implements JsonSerializer<ICharacterInfo> {

	@Override
	public JsonElement serialize(ICharacterInfo arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();
		obj.add("value", new JsonPrimitive(arg0.getValue()));
		obj.add("characterType", new JsonPrimitive(arg0.getCharacterType().name()));
		return obj;
	}

}

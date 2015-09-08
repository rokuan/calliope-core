package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.IVerb;
import com.rokuan.calliopecore.sentence.IVerbConjugation;

public class IVerbConjugationSerializer implements JsonSerializer<IVerbConjugation> {

	@Override
	public JsonElement serialize(IVerbConjugation arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();
		
		obj.add("tense", new JsonPrimitive(arg0.getTense().toString()));
		obj.add("form", new JsonPrimitive(arg0.getForm().toString()));
		obj.add("verb", new GsonBuilder().registerTypeAdapter(IVerb.class, new IVerbSerializer())
				.create()
				.toJsonTree(arg0.getVerb(), IVerb.class));
		
		return obj;
	}

}

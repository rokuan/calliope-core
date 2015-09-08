package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.IVerb;
import com.rokuan.calliopecore.sentence.Action.ActionType;

public class IVerbSerializer implements JsonSerializer<IVerb> {

	@Override
	public JsonElement serialize(IVerb arg0, Type arg1,
			JsonSerializationContext arg2) {
		JsonObject obj = new JsonObject();
		
		JsonArray actionsArray = new JsonArray();
		
		if(arg0.getActions() != null){
			for(ActionType act: arg0.getActions()){
				actionsArray.add(new JsonPrimitive(act.toString()));
			}
		}
		
		//obj.add("name", arg0.getName());
		obj.add("actions", actionsArray);
		
		return obj;
	}

}

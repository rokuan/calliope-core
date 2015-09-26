package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.Action.ActionType;
import com.rokuan.calliopecore.sentence.IVerb;

public class VerbDeserializer implements JsonDeserializer<IVerb>{

	@Override
	public IVerb deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject obj = arg0.getAsJsonObject();
		JsonArray actionsArray = obj.get("actions").getAsJsonArray();
		ActionType[] actionTypes = new ActionType[actionsArray.size()];
		
		for(int i=0; i<actionsArray.size(); i++){
			actionTypes[i] = ActionType.valueOf(actionsArray.get(i).getAsString());
		}
		
		final Set<ActionType> actions = new HashSet<ActionType>(Arrays.asList(actionTypes));
		
		return new IVerb() {			
			@Override
			public boolean hasAction(ActionType action) {
				return getActions() != null && getActions().contains(action);
			}
			
			@Override
			public Set<ActionType> getActions() {
				return actions;
			}
		};
	}

}

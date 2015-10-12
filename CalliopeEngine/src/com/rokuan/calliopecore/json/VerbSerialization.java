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
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.IVerb;
import com.rokuan.calliopecore.sentence.Action.ActionType;

public class VerbSerialization {
	private static final String JSON_VALUE_KEY = "value";
	private static final String JSON_ACTIONS_KEY = "actions";
	private static final String JSON_IS_FIELD_KEY = "is_field";
	private static final String JSON_FIELD_KEY = "field";
	
	public static class Serializer implements JsonSerializer<IVerb> {

		@Override
		public JsonElement serialize(IVerb arg0, Type arg1,
				JsonSerializationContext arg2) {
			JsonObject obj = new JsonObject();
			
			JsonArray actionsArray = new JsonArray();
			
			if(arg0.getActions() != null){
				for(ActionType act: arg0.getActions()){
					actionsArray.add(new JsonPrimitive(act.name()));
				}
			}
			
			obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
			obj.add(JSON_ACTIONS_KEY, actionsArray);
			obj.add(JSON_IS_FIELD_KEY, new JsonPrimitive(arg0.isAFieldAction()));
			obj.add(JSON_FIELD_KEY, new JsonPrimitive(arg0.getBoundField()));
			
			return obj;
		}

	}
	
	public static class Deserializer implements JsonDeserializer<IVerb> {

		@Override
		public IVerb deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			JsonObject obj = arg0.getAsJsonObject();
			JsonArray actionsArray = obj.get(JSON_ACTIONS_KEY).getAsJsonArray();
			ActionType[] actionTypes = new ActionType[actionsArray.size()];
			
			for(int i=0; i<actionsArray.size(); i++){
				actionTypes[i] = ActionType.valueOf(actionsArray.get(i).getAsString());
			}
			
			final String value = obj.get(JSON_VALUE_KEY).getAsString();
			final Set<ActionType> actions = new HashSet<ActionType>(Arrays.asList(actionTypes));
			final boolean isFieldBound = obj.get(JSON_IS_FIELD_KEY).getAsBoolean();
			final String field = obj.get(JSON_FIELD_KEY).getAsString();
			
			return new IVerb() {		
				@Override
				public String getValue(){
					return value;
				}
				
				@Override
				public boolean hasAction(ActionType action) {
					return getActions() != null && getActions().contains(action);
				}
				
				@Override
				public Set<ActionType> getActions() {
					return actions;
				}

				@Override
				public boolean isAFieldAction() {
					return isFieldBound;
				}

				@Override
				public String getBoundField() {
					return field;
				}
			};
		}

	}
}

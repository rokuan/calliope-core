package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.IAction;
import com.rokuan.calliopecore.sentence.IAction.ActionType;
import com.rokuan.calliopecore.sentence.IAction.Form;
import com.rokuan.calliopecore.sentence.IAction.Tense;

public class ActionSerialization {
	private static final String JSON_VALUE_KEY = "value";
	private static final String JSON_TENSE_KEY = "tense";
	private static final String JSON_FORM_KEY = "form";
	private static final String JSON_ACTION_KEY = "action";
	private static final String JSON_IS_FIELD_KEY = "is_field";
	private static final String JSON_FIELD_KEY = "field";
	private static final String JSON_IS_STATE_KEY = "is_state";
	private static final String JSON_STATE_KEY = "state";
	private static final String JSON_STATE_VALUE_KEY = "state_value";
	private static final String JSON_IS_TARGET_KEY = "is_target";
	
	public static class Serializer implements JsonSerializer<IAction> {

		@Override
		public JsonElement serialize(IAction arg0, Type arg1,
				JsonSerializationContext arg2) {
			JsonObject obj = new JsonObject();
			
			obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
			obj.add(JSON_TENSE_KEY, new JsonPrimitive(arg0.getTense().name()));
			obj.add(JSON_FORM_KEY, new JsonPrimitive(arg0.getForm().name()));
			obj.add(JSON_ACTION_KEY, new JsonPrimitive(arg0.getAction().name()));
			obj.add(JSON_IS_FIELD_KEY, new JsonPrimitive(arg0.isFieldBound()));
			obj.add(JSON_IS_STATE_KEY, new JsonPrimitive(arg0.isStateBound()));
			obj.add(JSON_IS_TARGET_KEY, new JsonPrimitive(arg0.isTargetAction()));
			
			if(arg0.isFieldBound()){
				obj.add(JSON_FIELD_KEY, new JsonPrimitive(arg0.getBoundField()));
			}
			
			if(arg0.isStateBound()){
				obj.add(JSON_STATE_KEY, new JsonPrimitive(arg0.getBoundState()));
				obj.add(JSON_STATE_VALUE_KEY, new JsonPrimitive(arg0.getState()));
			}
			
			return obj;
		}

	}
	
	public static class Deserializer implements JsonDeserializer<IAction> {

		@Override
		public IAction deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			JsonObject obj = arg0.getAsJsonObject();
			
			final String value = obj.get(JSON_VALUE_KEY).getAsString();
			final Tense tense = Tense.valueOf(obj.get(JSON_TENSE_KEY).getAsString());
			final Form form = Form.valueOf(obj.get(JSON_FORM_KEY).getAsString());
			final ActionType action = ActionType.valueOf(obj.get(JSON_ACTION_KEY).getAsString());
			final boolean fieldBound = obj.get(JSON_IS_FIELD_KEY).getAsBoolean();
			final String field = fieldBound ? obj.get(JSON_FIELD_KEY).getAsString() : null;
			final boolean stateBound = obj.get(JSON_IS_STATE_KEY).getAsBoolean();
			final String state = stateBound ? obj.get(JSON_STATE_KEY).getAsString() : null;
			final String stateValue = stateBound ? obj.get(JSON_STATE_VALUE_KEY).getAsString() : null;
			final boolean target = obj.get(JSON_IS_TARGET_KEY).getAsBoolean();
			
			return new IAction() {	
				@Override
				public String getValue(){
					return value;
				}
				
				@Override
				public Tense getTense() {
					return tense;
				}

				@Override
				public boolean isTargetAction() {
					return target;
				}

				@Override
				public Form getForm() {
					return form;
				}

				@Override
				public ActionType getAction() {
					return action;
				}

				@Override
				public boolean isFieldBound() {
					return fieldBound;
				}

				@Override
				public String getBoundField() {
					return field;
				}

				@Override
				public boolean isStateBound() {
					return stateBound;
				}

				@Override
				public String getBoundState() {
					return state;
				}

				@Override
				public String getState() {
					return stateValue;
				}
			};
		}
	}
}

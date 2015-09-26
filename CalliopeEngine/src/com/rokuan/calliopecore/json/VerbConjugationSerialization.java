package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.IVerb;
import com.rokuan.calliopecore.sentence.IVerbConjugation;
import com.rokuan.calliopecore.sentence.Action.ActionType;
import com.rokuan.calliopecore.sentence.IVerbConjugation.Form;
import com.rokuan.calliopecore.sentence.IVerbConjugation.Tense;

public class VerbConjugationSerialization {
	private static final String JSON_VALUE_KEY = "value";
	private static final String JSON_TENSE_KEY = "tense";
	private static final String JSON_FORM_KEY = "form";
	private static final String JSON_VERB_KEY = "verb";
	
	public static class Serializer implements JsonSerializer<IVerbConjugation> {

		@Override
		public JsonElement serialize(IVerbConjugation arg0, Type arg1,
				JsonSerializationContext arg2) {
			JsonObject obj = new JsonObject();
			
			obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
			obj.add(JSON_TENSE_KEY, new JsonPrimitive(arg0.getTense().name()));
			obj.add(JSON_FORM_KEY, new JsonPrimitive(arg0.getForm().name()));
			obj.add(JSON_VERB_KEY, new GsonBuilder().registerTypeAdapter(IVerb.class, new VerbSerialization.Serializer())
					.create()
					.toJsonTree(arg0.getVerb(), IVerb.class));
			
			return obj;
		}

	}
	
	public static class Deserializer implements JsonDeserializer<IVerbConjugation> {

		@Override
		public IVerbConjugation deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			JsonObject obj = arg0.getAsJsonObject();
			
			final String value = obj.get(JSON_VALUE_KEY).getAsString();
			final Tense tense = Tense.valueOf(obj.get(JSON_TENSE_KEY).getAsString());
			final Form form = Form.valueOf(obj.get(JSON_FORM_KEY).getAsString());
			final IVerb verb = new GsonBuilder().registerTypeAdapter(IVerb.class, new VerbSerialization.Deserializer())
					.create()
					.fromJson(obj.get(JSON_VERB_KEY), IVerb.class);
			
			return new IVerbConjugation() {	
				@Override
				public String getValue(){
					return value;
				}
				
				@Override
				public IVerb getVerb() {
					return verb;
				}
				
				@Override
				public Tense getTense() {
					return tense;
				}
				
				@Override
				public Form getForm() {
					return form;
				}
				
				@Override
				public boolean does(ActionType action) {
					return getVerb() != null && getVerb().hasAction(action);
				}
			};
		}

	}
}

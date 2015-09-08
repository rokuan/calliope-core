package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.Action.ActionType;
import com.rokuan.calliopecore.sentence.IVerb;
import com.rokuan.calliopecore.sentence.IVerbConjugation;
import com.rokuan.calliopecore.sentence.IVerbConjugation.Form;
import com.rokuan.calliopecore.sentence.IVerbConjugation.Tense;

public class IVerbConjugationDeserializer implements JsonDeserializer<IVerbConjugation> {

	@Override
	public IVerbConjugation deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		JsonObject obj = arg0.getAsJsonObject();
		final Tense tense = Tense.valueOf(obj.get("tense").getAsString());
		final Form form = Form.valueOf(obj.get("form").getAsString());
		final IVerb verb = new GsonBuilder().registerTypeAdapter(IVerb.class, new IVerbDeserializer())
				.create()
				.fromJson(obj.get("verb"), IVerb.class);
		
		return new IVerbConjugation() {			
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

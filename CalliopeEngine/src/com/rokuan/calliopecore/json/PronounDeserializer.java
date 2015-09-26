package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.IPronoun;
import com.rokuan.calliopecore.sentence.IPronoun.PronounSource;

public class PronounDeserializer implements JsonDeserializer<IPronoun>{

	@Override
	public IPronoun deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		final PronounSource source = PronounSource.valueOf(arg0.getAsJsonObject().get("source").getAsString());
		
		return new IPronoun() {			
			@Override
			public PronounSource getSource() {
				return source;
			}
		};
	}

}

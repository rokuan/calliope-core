package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.ITransportInfo;

public class TransportDeserializer implements JsonDeserializer<ITransportInfo> {

	@Override
	public ITransportInfo deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		// TODO Auto-generated method stub
		return null;
	}

}

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
import com.rokuan.calliopecore.sentence.ITransportInfo;
import com.rokuan.calliopecore.sentence.structure.data.way.TransportObject.TransportType;

public class TransportSerialization {
	private static final String JSON_VALUE_KEY = "value";
	private static final String JSON_TRANSPORT_TYPE_KEY = "transport_type";
	
	public static class Serializer implements JsonSerializer<ITransportInfo> {

		@Override
		public JsonElement serialize(ITransportInfo arg0, Type arg1,
				JsonSerializationContext arg2) {
			JsonObject obj = new JsonObject();
			obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
			obj.add(JSON_TRANSPORT_TYPE_KEY, new JsonPrimitive(arg0.getTransportType().name()));
			return obj;
		}

	}
	
	public static class Deserializer implements JsonDeserializer<ITransportInfo> {

		@Override
		public ITransportInfo deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			JsonObject obj = arg0.getAsJsonObject();
			
			final String value = obj.get(JSON_VALUE_KEY).getAsString();
			final TransportType transportType = TransportType.valueOf(obj.get(JSON_TRANSPORT_TYPE_KEY).getAsString());
			
			return new ITransportInfo() {
				@Override
				public String getValue() {
					return value;
				}
				
				@Override
				public TransportType getTransportType() {
					return transportType;
				}
			};
		}

	}
}

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
import com.rokuan.calliopecore.sentence.IPlacePreposition;
import com.rokuan.calliopecore.sentence.IPurposePreposition;
import com.rokuan.calliopecore.sentence.ITimePreposition;
import com.rokuan.calliopecore.sentence.IWayPreposition;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceContext;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial.PurposeContext;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeContext;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayContext;

public class PrepositionSerialization {
	private static final String JSON_PREP_VALUE_KEY = "value";
	private static final String JSON_PREP_CONTEXT_KEY = "context";
	
	public static class PlacePreposition {		
		public static class Serializer implements JsonSerializer<IPlacePreposition> {

			@Override
			public JsonElement serialize(IPlacePreposition arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject obj = new JsonObject();
				obj.add(JSON_PREP_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
				obj.add(JSON_PREP_CONTEXT_KEY, new JsonPrimitive(arg0.getContext().name()));
				return obj;
			}
			
		}
		
		public static class Deserializer implements JsonDeserializer<IPlacePreposition> {

			@Override
			public IPlacePreposition deserialize(JsonElement arg0, Type arg1,
					JsonDeserializationContext arg2) throws JsonParseException {
				JsonObject obj = arg0.getAsJsonObject();
				
				final String value = obj.get(JSON_PREP_VALUE_KEY).getAsString();
				final PlaceContext context = PlaceContext.valueOf(obj.get(JSON_PREP_CONTEXT_KEY).getAsString());
				
				return new IPlacePreposition() {					
					@Override
					public String getValue() {
						return value;
					}
					
					@Override
					public PlaceContext getContext() {
						return context;
					}
				};
			}
			
		}
	}
	
	public static class TimePreposition {		
		public static class Serializer implements JsonSerializer<ITimePreposition> {

			@Override
			public JsonElement serialize(ITimePreposition arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject obj = new JsonObject();
				obj.add(JSON_PREP_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
				obj.add(JSON_PREP_CONTEXT_KEY, new JsonPrimitive(arg0.getContext().name()));
				return obj;
			}
			
		}
		
		public static class Deserializer implements JsonDeserializer<ITimePreposition> {

			@Override
			public ITimePreposition deserialize(JsonElement arg0, Type arg1,
					JsonDeserializationContext arg2) throws JsonParseException {
				JsonObject obj = arg0.getAsJsonObject();
				
				final String value = obj.get(JSON_PREP_VALUE_KEY).getAsString();
				final TimeContext context = TimeContext.valueOf(obj.get(JSON_PREP_CONTEXT_KEY).getAsString());
				
				return new ITimePreposition() {					
					@Override
					public String getValue() {
						return value;
					}
					
					@Override
					public TimeContext getContext() {
						return context;
					}
				};
			}
			
		}
	}
	
	public static class WayPreposition {		
		public static class Serializer implements JsonSerializer<IWayPreposition> {

			@Override
			public JsonElement serialize(IWayPreposition arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject obj = new JsonObject();
				obj.add(JSON_PREP_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
				obj.add(JSON_PREP_CONTEXT_KEY, new JsonPrimitive(arg0.getContext().name()));
				return obj;
			}
			
		}
		
		public static class Deserializer implements JsonDeserializer<IWayPreposition> {

			@Override
			public IWayPreposition deserialize(JsonElement arg0, Type arg1,
					JsonDeserializationContext arg2) throws JsonParseException {
				JsonObject obj = arg0.getAsJsonObject();
				
				final String value = obj.get(JSON_PREP_VALUE_KEY).getAsString();
				final WayContext context = WayContext.valueOf(obj.get(JSON_PREP_CONTEXT_KEY).getAsString());
				
				return new IWayPreposition() {					
					@Override
					public String getValue() {
						return value;
					}
					
					@Override
					public WayContext getContext() {
						return context;
					}
				};
			}
			
		}
	}
	
	public static class PurposePreposition {		
		public static class Serializer implements JsonSerializer<IPurposePreposition> {

			@Override
			public JsonElement serialize(IPurposePreposition arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject obj = new JsonObject();
				obj.add(JSON_PREP_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
				obj.add(JSON_PREP_CONTEXT_KEY, new JsonPrimitive(arg0.getContext().name()));
				return obj;
			}
			
		}
		
		public static class Deserializer implements JsonDeserializer<IPurposePreposition> {

			@Override
			public IPurposePreposition deserialize(JsonElement arg0, Type arg1,
					JsonDeserializationContext arg2) throws JsonParseException {
				JsonObject obj = arg0.getAsJsonObject();
				
				final String value = obj.get(JSON_PREP_VALUE_KEY).getAsString();
				final PurposeContext context = PurposeContext.valueOf(obj.get(JSON_PREP_CONTEXT_KEY).getAsString());
				
				return new IPurposePreposition() {					
					@Override
					public String getValue() {
						return value;
					}
					
					@Override
					public PurposeContext getContext() {
						return context;
					}
				};
			}
			
		}
	}
}

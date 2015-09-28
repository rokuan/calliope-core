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
import com.rokuan.calliopecore.sentence.ICustomData;
import com.rokuan.calliopecore.sentence.ICustomMode;
import com.rokuan.calliopecore.sentence.ICustomObject;
import com.rokuan.calliopecore.sentence.ICustomPerson;
import com.rokuan.calliopecore.sentence.ICustomPlace;

public class CustomDataSerialization {
	private static final String JSON_VALUE_KEY = "value";
	private static final String JSON_DATA_CODE_KEY = "dataCode";

	private static class CustomData {
		private static class Serializer implements JsonSerializer<ICustomData> {

			@Override
			public JsonElement serialize(ICustomData arg0, Type arg1,
					JsonSerializationContext arg2) {
				JsonObject obj = new JsonObject();
				obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
				obj.add(JSON_DATA_CODE_KEY, new JsonPrimitive(arg0.getCode()));
				return obj;
			}

		}

		private static class Deserializer implements JsonDeserializer<ICustomData> {

			@Override
			public ICustomData deserialize(JsonElement arg0, Type arg1,
					JsonDeserializationContext arg2) throws JsonParseException {
				JsonObject obj = arg0.getAsJsonObject();

				final String value = obj.get(JSON_VALUE_KEY).getAsString();
				final String dataCode = obj.get(JSON_DATA_CODE_KEY).getAsString();

				return new ICustomData() {				
					@Override
					public String getValue() {
						return value;
					}

					@Override
					public String getCode() {
						return dataCode;
					}
				};
			}

		}
	}

	public static class CustomObject {
		public static class Serializer implements JsonSerializer<ICustomObject> {

			@Override
			public JsonElement serialize(ICustomObject arg0, Type arg1,
					JsonSerializationContext arg2) {
				return new GsonBuilder().registerTypeAdapter(ICustomData.class, new CustomData.Serializer())
						.create()
						.toJsonTree(arg0, ICustomData.class);
			}

		}

		public static class Deserializer implements JsonDeserializer<ICustomObject> {

			@Override
			public ICustomObject deserialize(JsonElement arg0, Type arg1,
					JsonDeserializationContext arg2) throws JsonParseException {
				final ICustomData data = new GsonBuilder().registerTypeAdapter(ICustomData.class, new CustomData.Deserializer())
						.create()
						.fromJson(arg0, ICustomData.class);
						
				return new ICustomObject() {					
					@Override
					public String getValue() {
						return data.getValue();
					}
					
					@Override
					public String getCode() {
						return data.getCode();
					}
				};
			}
			
		}
	}

	public static class CustomPlace {
		public static class Serializer implements JsonSerializer<ICustomPlace> {

			@Override
			public JsonElement serialize(ICustomPlace arg0, Type arg1,
					JsonSerializationContext arg2) {
				return new GsonBuilder().registerTypeAdapter(ICustomData.class, new CustomData.Serializer())
						.create()
						.toJsonTree(arg0, ICustomData.class);
			}

		}

		public static class Deserializer implements JsonDeserializer<ICustomPlace> {

			@Override
			public ICustomPlace deserialize(JsonElement arg0, Type arg1,
					JsonDeserializationContext arg2) throws JsonParseException {
				final ICustomData data = new GsonBuilder().registerTypeAdapter(ICustomData.class, new CustomData.Deserializer())
						.create()
						.fromJson(arg0, ICustomData.class);
						
				return new ICustomPlace() {					
					@Override
					public String getValue() {
						return data.getValue();
					}
					
					@Override
					public String getCode() {
						return data.getCode();
					}
				};
			}
			
		}
	}

	public static class CustomPerson {
		public static class Serializer implements JsonSerializer<ICustomPerson> {

			@Override
			public JsonElement serialize(ICustomPerson arg0, Type arg1,
					JsonSerializationContext arg2) {
				return new GsonBuilder().registerTypeAdapter(ICustomData.class, new CustomData.Serializer())
						.create()
						.toJsonTree(arg0, ICustomData.class);
			}

		}

		public static class Deserializer implements JsonDeserializer<ICustomPerson> {

			@Override
			public ICustomPerson deserialize(JsonElement arg0, Type arg1,
					JsonDeserializationContext arg2) throws JsonParseException {
				final ICustomData data = new GsonBuilder().registerTypeAdapter(ICustomData.class, new CustomData.Deserializer())
						.create()
						.fromJson(arg0, ICustomData.class);
						
				return new ICustomPerson() {					
					@Override
					public String getValue() {
						return data.getValue();
					}
					
					@Override
					public String getCode() {
						return data.getCode();
					}
				};
			}
			
		}
	}

	public static class CustomMode {
		public static class Serializer implements JsonSerializer<ICustomMode> {

			@Override
			public JsonElement serialize(ICustomMode arg0, Type arg1,
					JsonSerializationContext arg2) {
				return new GsonBuilder().registerTypeAdapter(ICustomData.class, new CustomData.Serializer())
						.create()
						.toJsonTree(arg0, ICustomData.class);
			}

		}

		public static class Deserializer implements JsonDeserializer<ICustomMode> {

			@Override
			public ICustomMode deserialize(JsonElement arg0, Type arg1,
					JsonDeserializationContext arg2) throws JsonParseException {
				final ICustomData data = new GsonBuilder().registerTypeAdapter(ICustomData.class, new CustomData.Deserializer())
						.create()
						.fromJson(arg0, ICustomData.class);
						
				return new ICustomMode() {					
					@Override
					public String getValue() {
						return data.getValue();
					}
					
					@Override
					public String getCode() {
						return data.getCode();
					}
				};
			}
			
		}
	}
}

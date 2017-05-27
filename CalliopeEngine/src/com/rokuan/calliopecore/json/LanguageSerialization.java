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
import com.rokuan.calliopecore.sentence.ILanguageInfo;

public class LanguageSerialization {
    private static final String JSON_VALUE_KEY = "value";
    private static final String JSON_LANGUAGE_CODE_KEY = "language_code";

    public static class Serializer implements JsonSerializer<ILanguageInfo> {
        @Override
        public JsonElement serialize(ILanguageInfo arg0, Type arg1, JsonSerializationContext arg2) {
            JsonObject obj = new JsonObject();
            obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
            obj.add(JSON_LANGUAGE_CODE_KEY, new JsonPrimitive(arg0.getLanguageCode()));
            return obj;
        }
    }

    public static class Deserializer implements JsonDeserializer<ILanguageInfo> {
        @Override
        public ILanguageInfo deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            JsonObject obj = arg0.getAsJsonObject();

            final String value = obj.get(JSON_VALUE_KEY).getAsString();
            final String languageCode = obj.get(JSON_LANGUAGE_CODE_KEY).getAsString();

            return new ILanguageInfo() {
                @Override
                public String getValue() {
                    return value;
                }

                @Override
                public String getLanguageCode() {
                    return languageCode;
                }
            };
        }
    }
}

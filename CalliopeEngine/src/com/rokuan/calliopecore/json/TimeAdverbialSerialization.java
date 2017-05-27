package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial;

public class TimeAdverbialSerialization {
    private static final String JSON_TIME_TYPE_KEY = "time_type";

    public static class Serializer implements JsonSerializer<ITimeObject> {
        @Override
        public JsonElement serialize(ITimeObject arg0, Type arg1, JsonSerializationContext arg2) {
            JsonElement result;
            Class<? extends ITimeObject> clazz = TimeAdverbial.getClassFromTimeType(arg0.getTimeType());
            GsonBuilder builder = FullGsonBuilder.getSerializationGsonBuilder();

            result = builder.create().toJsonTree(arg0, clazz);
            result.getAsJsonObject().add(JSON_TIME_TYPE_KEY, new JsonPrimitive(arg0.getTimeType().name()));

            return result;
        }
    }

    public static class Deserializer implements JsonDeserializer<ITimeObject> {
        @Override
        public ITimeObject deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            GsonBuilder builder = FullGsonBuilder.getDeserializationGsonBuilder();
            Gson gson = builder.create();
            Class<? extends ITimeObject> clazz = TimeAdverbial.getClassFromTimeType(TimeAdverbial.TimeType.valueOf(arg0.getAsJsonObject().get(JSON_TIME_TYPE_KEY).getAsString()));
            return gson.fromJson(arg0, clazz);
        }
    }
}

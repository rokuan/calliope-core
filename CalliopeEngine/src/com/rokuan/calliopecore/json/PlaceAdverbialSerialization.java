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
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial;

public class PlaceAdverbialSerialization {
    private static final String JSON_PLACE_TYPE_KEY = "place_type";

    public static class Serializer implements JsonSerializer<IPlaceObject> {
        @Override
        public JsonElement serialize(IPlaceObject arg0, Type arg1, JsonSerializationContext arg2) {
            JsonElement result;
            Class<? extends IPlaceObject> clazz = PlaceAdverbial.getClassFromPlaceType(arg0.getPlaceType());
            GsonBuilder builder = FullGsonBuilder.getSerializationGsonBuilder();

            result = builder.create().toJsonTree(arg0, clazz);
            result.getAsJsonObject().add(JSON_PLACE_TYPE_KEY, new JsonPrimitive(arg0.getPlaceType().name()));

            return result;
        }
    }

    public static class Deserializer implements JsonDeserializer<IPlaceObject> {
        @Override
        public IPlaceObject deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            GsonBuilder builder = FullGsonBuilder.getDeserializationGsonBuilder();
            Gson gson = builder.create();
            Class<? extends IPlaceObject> clazz = PlaceAdverbial.getClassFromPlaceType(PlaceAdverbial.PlaceType.valueOf(arg0.getAsJsonObject().get(JSON_PLACE_TYPE_KEY).getAsString()));
            return gson.fromJson(arg0, clazz);
        }
    }
}

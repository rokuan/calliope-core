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
import com.rokuan.calliopecore.sentence.IPlaceInfo;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject.PlaceCategory;

public class PlaceSerialization {
    private static final String JSON_VALUE_KEY = "value";
    private static final String JSON_PLACE_CATEGORY_KEY = "place_category";

    public static class Serializer implements JsonSerializer<IPlaceInfo> {
        @Override
        public JsonElement serialize(IPlaceInfo arg0, Type arg1, JsonSerializationContext arg2) {
            JsonObject obj = new JsonObject();
            obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
            obj.add(JSON_PLACE_CATEGORY_KEY, new JsonPrimitive(arg0.getPlaceCategory().name()));
            return obj;
        }
    }

    public static class Deserializer implements JsonDeserializer<IPlaceInfo> {
        @Override
        public IPlaceInfo deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            JsonObject obj = arg0.getAsJsonObject();

            final String value = obj.get(JSON_VALUE_KEY).getAsString();
            final PlaceCategory placeCategory = PlaceCategory.valueOf(obj.get(JSON_PLACE_CATEGORY_KEY).getAsString());

            return new IPlaceInfo() {
                @Override
                public String getValue() {
                    return value;
                }

                @Override
                public PlaceCategory getPlaceCategory() {
                    return placeCategory;
                }
            };
        }
    }
}

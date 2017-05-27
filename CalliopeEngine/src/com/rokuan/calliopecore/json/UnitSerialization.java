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
import com.rokuan.calliopecore.sentence.IUnitInfo;
import com.rokuan.calliopecore.sentence.structure.data.nominal.UnitObject.UnitType;

public class UnitSerialization {
    private static final String JSON_VALUE_KEY = "value";
    private static final String JSON_UNIT_TYPE_KEY = "unit_type";

    public static class Serializer implements JsonSerializer<IUnitInfo> {
        @Override
        public JsonElement serialize(IUnitInfo arg0, Type arg1, JsonSerializationContext arg2) {
            JsonObject obj = new JsonObject();
            obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
            obj.add(JSON_UNIT_TYPE_KEY, new JsonPrimitive(arg0.getUnitType().name()));
            return obj;
        }
    }

    public static class Deserializer implements JsonDeserializer<IUnitInfo> {
        @Override
        public IUnitInfo deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            JsonObject obj = arg0.getAsJsonObject();

            final String value = obj.get(JSON_VALUE_KEY).getAsString();
            final UnitType unitType = UnitType.valueOf(obj.get(JSON_UNIT_TYPE_KEY).getAsString());

            return new IUnitInfo() {
                @Override
                public String getValue() {
                    return value;
                }

                @Override
                public UnitType getUnitType() {
                    return unitType;
                }
            };
        }
    }
}

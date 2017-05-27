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
import com.rokuan.calliopecore.sentence.IAdjectiveInfo;
import com.rokuan.calliopecore.sentence.IAdjectiveInfo.AdjectiveValue;

public class AdjectiveSerialization {
    private static final String JSON_VALUE_KEY = "value";
    private static final String JSON_ADJECTIVE_TYPE_KEY = "adjective_type";
    private static final String JSON_IS_FIELD_KEY = "is_field";
    private static final String JSON_FIELD_KEY = "field";
    private static final String JSON_IS_STATE_KEY = "is_state";
    private static final String JSON_STATE_KEY = "state";
    private static final String JSON_STATE_VALUE_KEY = "state_value";

    public static class Serializer implements JsonSerializer<IAdjectiveInfo> {
        @Override
        public JsonElement serialize(IAdjectiveInfo arg0, Type arg1, JsonSerializationContext arg2) {
            JsonObject obj = new JsonObject();
            obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
            obj.add(JSON_ADJECTIVE_TYPE_KEY, new JsonPrimitive(arg0.getAdjectiveType().name()));
            obj.add(JSON_IS_FIELD_KEY, new JsonPrimitive(arg0.isFieldBound()));
            obj.add(JSON_IS_STATE_KEY, new JsonPrimitive(arg0.isStateBound()));

            if (arg0.isFieldBound()) {
                obj.add(JSON_FIELD_KEY, new JsonPrimitive(arg0.getBoundField()));
            }

            if (arg0.isStateBound()) {
                obj.add(JSON_STATE_KEY, new JsonPrimitive(arg0.getBoundState()));
                obj.add(JSON_STATE_VALUE_KEY, new JsonPrimitive(arg0.getState()));
            }

            return obj;
        }
    }

    public static class Deserializer implements JsonDeserializer<IAdjectiveInfo> {
        @Override
        public IAdjectiveInfo deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            JsonObject obj = arg0.getAsJsonObject();

            final String value = obj.get(JSON_VALUE_KEY).getAsString();
            final AdjectiveValue adjType = AdjectiveValue.valueOf(obj.get(JSON_ADJECTIVE_TYPE_KEY).getAsString());
            final boolean fieldBound = obj.get(JSON_IS_FIELD_KEY).getAsBoolean();
            final String field = fieldBound ? obj.get(JSON_FIELD_KEY).getAsString() : null;
            final boolean stateBound = obj.get(JSON_IS_STATE_KEY).getAsBoolean();
            final String state = stateBound ? obj.get(JSON_STATE_KEY).getAsString() : null;
            final String stateValue = stateBound ? obj.get(JSON_STATE_VALUE_KEY).getAsString() : null;

            return new IAdjectiveInfo() {
                @Override
                public String getValue() {
                    return value;
                }

                @Override
                public AdjectiveValue getAdjectiveType() {
                    return adjType;
                }

                @Override
                public boolean isStateBound() {
                    return stateBound;
                }

                @Override
                public String getBoundState() {
                    return state;
                }

                @Override
                public String getState() {
                    return stateValue;
                }

                @Override
                public boolean isFieldBound() {
                    return fieldBound;
                }

                @Override
                public String getBoundField() {
                    return field;
                }
            };
        }
    }
}

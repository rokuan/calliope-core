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
import com.rokuan.calliopecore.sentence.ICharacterInfo;
import com.rokuan.calliopecore.sentence.structure.data.nominal.CharacterObject.CharacterType;

public class CharacterSerialization {
    private static final String JSON_VALUE_KEY = "value";
    private static final String JSON_CHARACTER_TYPE_KEY = "character_type";

    public static class Serializer implements JsonSerializer<ICharacterInfo> {
        @Override
        public JsonElement serialize(ICharacterInfo arg0, Type arg1, JsonSerializationContext arg2) {
            JsonObject obj = new JsonObject();
            obj.add(JSON_VALUE_KEY, new JsonPrimitive(arg0.getValue()));
            obj.add(JSON_CHARACTER_TYPE_KEY, new JsonPrimitive(arg0.getCharacterType().name()));
            return obj;
        }
    }

    public static class Deserializer implements JsonDeserializer<ICharacterInfo> {
        @Override
        public ICharacterInfo deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            JsonObject obj = arg0.getAsJsonObject();

            final String value = obj.get(JSON_VALUE_KEY).getAsString();
            final CharacterType characterType = CharacterType.valueOf(obj.get(JSON_CHARACTER_TYPE_KEY).getAsString());

            return new ICharacterInfo() {
                @Override
                public String getValue() {
                    return value;
                }

                @Override
                public CharacterType getCharacterType() {
                    return characterType;
                }
            };
        }
    }
}

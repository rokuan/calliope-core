package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.structure.data.count.AllItemsObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.count.FixedItemObject;
import com.rokuan.calliopecore.sentence.structure.data.count.IntervalObject;
import com.rokuan.calliopecore.sentence.structure.data.count.LimitedItemsObject;
import com.rokuan.calliopecore.sentence.structure.data.count.MultipleItemsObject;
import com.rokuan.calliopecore.sentence.structure.data.count.QuantityObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject.CountType;

public class CountObjectSerialization {
	private static final String JSON_COUNT_TYPE_KEY = "countType"; 
	
	public static class Deserializer implements JsonDeserializer<CountObject> {
		@Override
		public CountObject deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			Gson gson = new GsonBuilder().create();
			CountType countType = CountType.valueOf(arg0.getAsJsonObject().get(JSON_COUNT_TYPE_KEY).getAsString());
			Class<? extends CountObject> clazz = null;

			switch(countType){
			case ALL:
				clazz = AllItemsObject.class;
				break;
			case FIXED:
				clazz = FixedItemObject.class;
				break;
			case MULTIPLE:
				clazz = MultipleItemsObject.class;
				break;
			case LIMIT:
				clazz = LimitedItemsObject.class;
				break;
			case QUANTITY:
				clazz = QuantityObject.class;
				break;
			case INTERVAL:
				clazz = IntervalObject.class;
				break;
			}

			return gson.fromJson(arg0, clazz);
		}
	}
}

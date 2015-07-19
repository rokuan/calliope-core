package com.rokuan.calliopecore.sentence.structure.data.count;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.sentence.Type.Pronoun;

/**
 * Created by LEBEAU Christophe on 01/03/2015.
 */
public abstract class CountObject {
	public enum Range {
		FIRST,
		LAST
		//FIXED
	}

	public enum CountType {
		ALL,
		LIMIT,
		INTERVAL,
		MULTIPLE,
		FIXED,
		QUANTITY
	}

	public enum ArticleType {
		NONE,
		DEFINITE,
		INDEFINITE,
		DEMONSTRATIVE,
		POSSESSIVE
	}

	private CountType countType = CountType.ALL;
	public ArticleType definition = ArticleType.NONE;
	public Pronoun possessiveTarget = Pronoun.UNDEFINED;

	// TODO: ajouter les intervalles du type "du 5eme au dernier"
	protected CountObject(CountType ty){
		countType = ty;
	}

	public CountType getType(){
		return countType;
	}

	public static class CountObjectDeserializer implements JsonDeserializer<CountObject> {
		@Override
		public CountObject deserialize(JsonElement arg0, Type arg1,
				JsonDeserializationContext arg2) throws JsonParseException {
			Gson gson = new GsonBuilder().create();
			String countType = arg0.getAsJsonObject().get("countType").getAsString();
			Class<? extends CountObject> clazz = null;

			switch(CountType.valueOf(countType)){
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

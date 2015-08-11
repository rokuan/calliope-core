package com.rokuan.calliopecore.json;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.rokuan.calliopecore.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.AdditionalPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.LocationObject;
import com.rokuan.calliopecore.sentence.structure.data.place.NamedPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.NominalPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial;
import com.rokuan.calliopecore.sentence.structure.nominal.CityObject;
import com.rokuan.calliopecore.sentence.structure.nominal.CountryObject;


public class PlaceAdverbialDeserializer implements JsonDeserializer<IPlaceObject> {
	@Override
	public IPlaceObject deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		GsonBuilder builder = new GsonBuilder();
		
		Gson gson = builder.create();
		Class<? extends IPlaceObject> clazz = null;
		
		switch(PlaceAdverbial.PlaceType.valueOf(arg0.getAsJsonObject().get("placeType").getAsString())){
		case CUSTOM:
			clazz = AdditionalPlaceObject.class;
			break;
		case NOMINAL:
			clazz = NominalPlaceObject.class;
			break;
		case NAMED_PLACE:
			clazz = NamedPlaceObject.class;
			break;
		case LOCATION:
			clazz = LocationObject.class;
			break;
		case CITY:
			clazz = CityObject.class;
			break;
		case COUNTRY:
			clazz = CountryObject.class;
			break;
		default:
			break;
		}
		
		return gson.fromJson(arg0, clazz);
	}
}
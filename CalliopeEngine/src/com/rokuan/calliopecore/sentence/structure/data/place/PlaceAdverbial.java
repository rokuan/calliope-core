package com.rokuan.calliopecore.sentence.structure.data.place;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.CityObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.CountryObject;

public abstract class PlaceAdverbial implements IPlaceObject {
	public enum PlaceContext {
		BEHIND,
		ON,
		UNDER,
		BEFORE,
		AFTER,
		LEFT,
		RIGHT,
		TOP,
		UP,
		BOTTOM,
		DOWN,
		CENTER,
		NORTH,
		WEST,
		SOUTH,
		EAST,
		NORTH_WEST,
		NORTH_EAST,
		SOUTH_WEST,
		SOUTH_EAST,
		AT,
		TO,
		AGAINST,
		WHERE,
		NEAR,
		IN,
		BETWEEN,
		ALONG,
		FRONT,
		AMONG,
		ABOVE,
		AROUND
	}
	
	public enum PlaceType {
		LOCATION,
		CITY,
		COUNTRY,
		NAMED_PLACE,
		PLACE_TYPE,
		NOMINAL,
		CUSTOM,
		ADDRESS
	}

	@Expose
	private PlaceContext location;
	
	@Override
	public PlaceContext getPlacePreposition(){
		return location;
	}
	
	@Override
	public void setPlacePreposition(PlaceContext prep){
		location = prep;
	}
	
	public static Class<? extends IPlaceObject> getClassFromPlaceType(PlaceType ty){
		Class<? extends IPlaceObject> clazz = null;
		
		switch(ty){
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
		case ADDRESS:
			clazz = AddressObject.class;
			break;
		case PLACE_TYPE:
			clazz = PlaceObject.class;
			break;
		}
		
		return clazz;
	}
}

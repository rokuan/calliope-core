package com.rokuan.calliopecore.sentence.structure.data.place;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.content.IPlaceObject;

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
		NOMINAL,
		CUSTOM
	}

	@Expose
	public PlaceContext location;
	
	@Override
	public PlaceContext getPlacePreposition(){
		return location;
	}
	
	@Override
	public void setPlacePreposition(PlaceContext prep){
		location = prep;
	}
}

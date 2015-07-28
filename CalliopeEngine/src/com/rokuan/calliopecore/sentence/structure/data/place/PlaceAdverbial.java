package com.rokuan.calliopecore.sentence.structure.data.place;

import com.google.gson.annotations.Expose;

public abstract class PlaceAdverbial {
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
		STATE,
		MONUMENT,
		NOMINAL,
		CUSTOM
	}

	@Expose
	private PlaceType placeType;

	@Expose
	public PlaceContext location;
	
	protected PlaceAdverbial(PlaceType ty) {
		placeType = ty;
	}
	
	public PlaceType getPlaceType(){
		return placeType;
	}
}

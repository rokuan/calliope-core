package com.rokuan.calliopecore.sentence.structure.data.place;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;

public abstract class PlaceObject extends NominalGroup {
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
	
	public enum TimeType {
    	SINGLE,
    	RELATIVE,
    	PERIOD,
    	VERBAL
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
	
	protected PlaceObject(PlaceType ty) {
		super(GroupType.PLACE);
		placeType = ty;
	}
	
	public PlaceType getPlaceType(){
		return placeType;
	}
}

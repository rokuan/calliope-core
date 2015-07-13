package com.rokuan.calliopecore.sentence.structure.data.place;

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
		BOTTOM,
		AT,
		TO,
		AGAINST,
		WHERE,
		NEAR
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
	
	private PlaceType type; 
	public PlaceContext location;
	//public NominalGroup content;
	
	protected PlaceObject(PlaceType ty) {
		super(GroupType.PLACE);
		type = ty;
	}
	
	public PlaceType getPlaceType(){
		return type;
	}
}

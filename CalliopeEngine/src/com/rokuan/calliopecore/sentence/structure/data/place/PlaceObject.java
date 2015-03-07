package com.rokuan.calliopecore.sentence.structure.data.place;

public abstract class PlaceObject {
	public enum PlaceType {
		STATE,
		MONUMENT
	}
	
	private PlaceType type;
	
	public PlaceObject(PlaceType ty){
		type = ty;
	}
	
	public PlaceType getType(){
		return type;
	}
}

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
		WHERE
	}
	
	/*public enum PlaceType {
		STATE,
		MONUMENT
	}
	
	private PlaceType type;
	
	public PlaceObject(PlaceType ty){
		type = ty;
	}
	
	public PlaceType getType(){
		return type;
	}*/
	
	public PlaceContext location;
	public NominalGroup content;
	
	protected PlaceObject(GroupType ty) {
		super(ty);
	}
}

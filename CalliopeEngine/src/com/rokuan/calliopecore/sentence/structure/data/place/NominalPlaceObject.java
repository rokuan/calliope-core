package com.rokuan.calliopecore.sentence.structure.data.place;

import com.rokuan.calliopecore.sentence.structure.data.nominal.NameObject;

public class NominalPlaceObject extends PlaceAdverbial {
	public PlaceContext preposition;	
	public NameObject content;
	
	@Override
	public PlaceType getPlaceType() {
		return PlaceType.NOMINAL;
	}
}

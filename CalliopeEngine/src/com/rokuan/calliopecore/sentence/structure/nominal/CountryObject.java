package com.rokuan.calliopecore.sentence.structure.nominal;

import com.rokuan.calliopecore.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceContext;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;

public class CountryObject extends NominalGroup implements IPlaceObject {
	private PlaceContext placePreposition;
	
	@Override
	public GroupType getGroupType() {
		return GroupType.COUNTRY;
	}

	@Override
	public PlaceType getPlaceType() {
		return PlaceType.COUNTRY;
	}

	@Override
	public PlaceContext getPlacePreposition() {
		return placePreposition;
	}

	@Override
	public void setPlacePreposition(PlaceContext prep) {
		placePreposition = prep;
	}
}

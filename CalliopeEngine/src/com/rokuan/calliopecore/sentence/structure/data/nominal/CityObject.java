package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.CityInfo;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceContext;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;

public class CityObject extends NominalGroup implements IPlaceObject {
	private PlaceContext placePreposition;
	public CityInfo city;

	@Override
	public GroupType getGroupType() {
		return GroupType.CITY;
	}

	@Override
	public PlaceType getPlaceType() {
		return PlaceType.CITY;
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

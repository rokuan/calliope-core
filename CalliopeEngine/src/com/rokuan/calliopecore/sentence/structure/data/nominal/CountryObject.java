package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.CountryInfo;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceContext;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;

public class CountryObject extends NominalGroup implements IPlaceObject {
	@Expose
	private PlaceContext placePreposition;
	
	@Expose
	public CountryInfo country;
	
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

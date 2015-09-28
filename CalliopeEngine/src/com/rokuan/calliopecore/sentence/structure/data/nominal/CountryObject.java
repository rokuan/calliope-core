package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.ICountryInfo;
import com.rokuan.calliopecore.sentence.IPlacePreposition;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;

public class CountryObject extends NominalGroup implements IPlaceObject {
	@Expose
	private IPlacePreposition placePreposition;
	
	@Expose
	public ICountryInfo country;
	
	@Override
	public GroupType getGroupType() {
		return GroupType.COUNTRY;
	}

	@Override
	public PlaceType getPlaceType() {
		return PlaceType.COUNTRY;
	}

	@Override
	public IPlacePreposition getPlacePreposition() {
		return placePreposition;
	}

	@Override
	public void setPlacePreposition(IPlacePreposition prep) {
		placePreposition = prep;
	}
}

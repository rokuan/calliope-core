package com.rokuan.calliopecore.sentence.structure.data.place;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.CityInfo;
import com.rokuan.calliopecore.sentence.CountryInfo;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NominalGroup.GroupType;

public class LocationObject extends PlaceAdverbial implements INominalObject {
	@Expose
	public CityInfo city;
	@Expose
	public CountryInfo country;
	
	@Override
	public PlaceType getPlaceType() {
		return PlaceType.LOCATION;
	}

	@Override
	public GroupType getGroupType() {
		return GroupType.LOCATION;
	}
}

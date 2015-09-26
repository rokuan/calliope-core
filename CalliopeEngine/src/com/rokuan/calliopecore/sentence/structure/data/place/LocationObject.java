package com.rokuan.calliopecore.sentence.structure.data.place;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.ICityInfo;
import com.rokuan.calliopecore.sentence.ICountryInfo;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NominalGroup.GroupType;

public class LocationObject extends PlaceAdverbial implements INominalObject {
	@Expose
	public ICityInfo city;
	
	@Expose
	public ICountryInfo country;
	
	@Override
	public PlaceType getPlaceType() {
		return PlaceType.LOCATION;
	}

	@Override
	public GroupType getGroupType() {
		return GroupType.LOCATION;
	}
}

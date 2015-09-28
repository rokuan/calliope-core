package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.ICityInfo;
import com.rokuan.calliopecore.sentence.IPlacePreposition;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;

public class CityObject extends NominalGroup implements IPlaceObject {
	@Expose
	private IPlacePreposition placePreposition;
	
	@Expose
	public ICityInfo city;

	@Override
	public GroupType getGroupType() {
		return GroupType.CITY;
	}

	@Override
	public PlaceType getPlaceType() {
		return PlaceType.CITY;
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

package com.rokuan.calliopecore.sentence.structure.data.place;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup.GroupType;


public class NamedPlaceObject extends PlaceAdverbial implements INominalObject {
	@Expose
	public String name;
	
	@Expose
	public String city;
	
	@Expose
	public String country;

	@Override
	public PlaceType getPlaceType() {
		return PlaceType.NAMED_PLACE;
	}

	@Override
	public GroupType getGroupType() {
		return GroupType.NAMED_PLACE;
	}
}

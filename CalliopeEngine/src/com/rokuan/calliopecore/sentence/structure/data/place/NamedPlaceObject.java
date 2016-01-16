package com.rokuan.calliopecore.sentence.structure.data.place;

import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NominalGroup.GroupType;

public class NamedPlaceObject extends PlaceAdverbial implements INominalObject {
	public String name;	
	public String city;	
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

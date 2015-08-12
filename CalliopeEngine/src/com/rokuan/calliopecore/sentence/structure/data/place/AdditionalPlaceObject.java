package com.rokuan.calliopecore.sentence.structure.data.place;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.CustomPlace;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NominalGroup.GroupType;

public class AdditionalPlaceObject extends PlaceAdverbial implements INominalObject {
	@Expose
	public CustomPlace place;

	@Override
	public PlaceType getPlaceType() {
		return PlaceType.CUSTOM;
	}

	@Override
	public GroupType getGroupType() {
		return GroupType.ADDITIONAL_PLACE;
	}
}

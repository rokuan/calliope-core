package com.rokuan.calliopecore.sentence.structure.data.place;

import com.rokuan.calliopecore.sentence.ICustomPlace;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NominalGroup.GroupType;

public class AdditionalPlace extends PlaceAdverbial implements INominalObject {
	public ICustomPlace place;

	@Override
	public PlaceType getPlaceType() {
		return PlaceType.CUSTOM;
	}

	@Override
	public GroupType getGroupType() {
		return GroupType.ADDITIONAL_PLACE;
	}
}

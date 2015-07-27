package com.rokuan.calliopecore.sentence.structure.data.place;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.CustomPlace;

public class AdditionalPlaceObject extends PlaceObject {
	@Expose
	public CustomPlace place;
	
	public AdditionalPlaceObject() {
		super(PlaceType.CUSTOM);
	}
}

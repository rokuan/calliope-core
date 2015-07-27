package com.rokuan.calliopecore.sentence.structure.data.place;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.structure.nominal.ComplementObject;

public class NominalPlaceObject extends PlaceObject {
	@Expose
	public ComplementObject content;
	
	public NominalPlaceObject(GroupType ty) {
		super(PlaceType.NOMINAL);
	}
}

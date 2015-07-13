package com.rokuan.calliopecore.sentence.structure.data.place;

import com.rokuan.calliopecore.sentence.structure.nominal.ComplementObject;

public class NominalPlaceObject extends PlaceObject {
	public ComplementObject content;
	
	public NominalPlaceObject(GroupType ty) {
		super(PlaceType.NOMINAL);
	}
}

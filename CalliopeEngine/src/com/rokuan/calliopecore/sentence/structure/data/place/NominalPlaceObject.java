package com.rokuan.calliopecore.sentence.structure.data.place;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.structure.nominal.ComplementObject;

public class NominalPlaceObject extends PlaceAdverbial {
	@Expose
	public PlaceContext preposition;
	
	@Expose
	public ComplementObject content;
	
	public NominalPlaceObject() {
		super(PlaceType.NOMINAL);
	}
}

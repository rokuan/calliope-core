package com.rokuan.calliopecore.sentence.structure.data.place;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.CityInfo;
import com.rokuan.calliopecore.sentence.CountryInfo;

public class StateObject extends PlaceAdverbial {
	@Expose
	public CityInfo city;
	@Expose
	public CountryInfo country;
	
	public StateObject(){
		super(PlaceType.STATE);
	}
}

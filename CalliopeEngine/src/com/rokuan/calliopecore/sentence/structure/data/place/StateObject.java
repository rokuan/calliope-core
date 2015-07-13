package com.rokuan.calliopecore.sentence.structure.data.place;

import com.rokuan.calliopecore.sentence.CityInfo;
import com.rokuan.calliopecore.sentence.CountryInfo;

public class StateObject extends PlaceObject {
	public CityInfo city;
	public CountryInfo country;
	
	public StateObject(){
		super(PlaceType.STATE);
	}
}

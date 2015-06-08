package com.rokuan.calliopecore.sentence.structure.data.place;

import com.rokuan.calliopecore.sentence.CityInfo;
import com.rokuan.calliopecore.sentence.CountryInfo;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;

public class StateObject extends NominalGroup {
	public CityInfo city;
	public CountryInfo country;
	
	public StateObject(){
		super(GroupType.STATE);
	}
}

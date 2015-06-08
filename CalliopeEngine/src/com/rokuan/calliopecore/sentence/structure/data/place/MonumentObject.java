package com.rokuan.calliopecore.sentence.structure.data.place;

import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;

public class MonumentObject extends NominalGroup {
	public String name;
	public String city;
	public String country;
	
	public MonumentObject() {
		super(GroupType.PLACE);
	}
}

package com.rokuan.calliopecore.sentence.structure.data.place;

import com.google.gson.annotations.Expose;


public class MonumentObject extends PlaceObject {
	@Expose
	public String name;
	
	@Expose
	public String city;
	
	@Expose
	public String country;
	
	public MonumentObject() {
		super(PlaceType.MONUMENT);
	}
}

package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "cities")
public final class CityInfo {
	@DatabaseField(id = true)
	private int id;	
	@DatabaseField
	private String name;
	@DatabaseField(uniqueCombo = true)
	private double latitude;
	@DatabaseField(uniqueCombo = true)
	private double longitude;
	
	public CityInfo(){
		
	}
	
	public CityInfo(String n, double lat, double lng){
		name = n;
		latitude = lat;
		longitude = lng;
	}
	
	public String getName() {
		return name;
	}
	
	public double getLatitude(){
		return latitude;
	}
	
	public double getLongitude(){
		return longitude;
	}
}

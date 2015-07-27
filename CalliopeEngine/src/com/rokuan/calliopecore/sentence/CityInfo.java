package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "cities")
public final class CityInfo {
	public static final String CITY_FIELD_NAME = "name";
	public static final String LATITUDE_FIELD_NAME = "lat";
	public static final String LONGITUDE_FIELD_NAME = "lng";
	
	
	@DatabaseField(generatedId = true)
	private int id;
	
	@Expose
	@DatabaseField(columnName = CITY_FIELD_NAME, uniqueCombo = true, index = true)
	private String name;
	
	@Expose
	@DatabaseField(columnName = LATITUDE_FIELD_NAME, uniqueCombo = true)
	private double latitude;
	
	@Expose
	@DatabaseField(columnName = LONGITUDE_FIELD_NAME, uniqueCombo = true)
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

package com.rokuan.calliopecore.sentence;

public final class CityInfo {
	private String name;
	private double latitude;
	private double longitude;
	
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

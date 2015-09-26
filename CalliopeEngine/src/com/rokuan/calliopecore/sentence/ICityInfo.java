package com.rokuan.calliopecore.sentence;

public interface ICityInfo {
	public class Location {
		private double latitude;
		private double longitude;
		
		public Location(double lat, double lng){
			latitude = lat;
			longitude = lng;
		}
		
		public double getLatitude(){
			return latitude;
		}
		
		public double getLongitude(){
			return longitude;
		}
	}
	
	String getValue();
	
	Location getLocation();
}

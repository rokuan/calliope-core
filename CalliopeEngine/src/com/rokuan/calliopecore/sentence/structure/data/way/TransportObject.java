package com.rokuan.calliopecore.sentence.structure.data.way;

import com.google.gson.annotations.Expose;

public class TransportObject extends WayAdverbial {
	public enum TransportType {
		WALK,
		BUS,
		CAR,
		TRAIN,
		PLANE,
		BOAT,
		BIKE
	}
	
	@Expose
	public TransportType transportType = TransportType.WALK;
	
	public TransportObject(){
		
	}
	
	@Override
	public WayType getWayType() {
		return WayType.TRANSPORT;
	}
}

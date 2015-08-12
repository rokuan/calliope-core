package com.rokuan.calliopecore.sentence.structure.data.way;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.CustomMode;


public class AdditionalMode extends WayAdverbial {
	@Expose
	public CustomMode mode;

	@Override
	public WayType getWayType() {
		return WayType.CUSTOM;
	}

	@Override
	public WayContext getWayPreposition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWayPreposition(WayContext prep) {
		// TODO Auto-generated method stub
		
	}	
}

package com.rokuan.calliopecore.sentence.structure.way;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.CustomMode;


public class AdditionalMode extends WayAdverbial {
	@Expose
	public CustomMode mode;

	@Override
	public WayType getWayType() {
		return WayType.CUSTOM;
	}	
}

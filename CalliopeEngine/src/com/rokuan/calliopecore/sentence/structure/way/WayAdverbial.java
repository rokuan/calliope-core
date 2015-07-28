package com.rokuan.calliopecore.sentence.structure.way;

import com.google.gson.annotations.Expose;

public abstract class WayAdverbial {
	enum WayType {
		CUSTOM,
		VERBAL,
		NOMINAL,
		LANGUAGE
	}
	
	enum WayContext {
		BY
	}
	
	@Expose
	private WayType wayType;

	protected WayAdverbial(WayType ty){
		wayType = ty;
	}

	public WayType getWayType() {
		return wayType;
	}
}

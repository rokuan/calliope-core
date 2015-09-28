package com.rokuan.calliopecore.sentence.structure.data.way;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.ICustomMode;


public class AdditionalMode extends WayAdverbial {
	@Expose
	public ICustomMode mode;

	@Override
	public WayType getWayType() {
		return WayType.CUSTOM;
	}
}

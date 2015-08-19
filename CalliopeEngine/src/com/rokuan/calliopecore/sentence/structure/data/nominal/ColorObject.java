package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.ColorInfo;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayContext;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;

public class ColorObject extends NominalGroup implements IWayObject {
	@Expose
	private WayContext wayPreposition;
	
	@Expose
	public ColorInfo color;

	@Override
	public GroupType getGroupType() {
		return GroupType.COLOR;
	}

	@Override
	public WayType getWayType() {
		return WayType.COLOR;
	}

	@Override
	public WayContext getWayPreposition() {
		return wayPreposition;
	}

	@Override
	public void setWayPreposition(WayContext prep) {
		wayPreposition = prep;
	}
}

package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.ColorInfo;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayContext;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;

public class ColorObject extends NominalGroup implements IWayObject {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWayPreposition(WayContext prep) {
		// TODO Auto-generated method stub
		
	}
}
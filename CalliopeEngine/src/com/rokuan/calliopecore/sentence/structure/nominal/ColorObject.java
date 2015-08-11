package com.rokuan.calliopecore.sentence.structure.nominal;

import com.rokuan.calliopecore.content.IWayObject;
import com.rokuan.calliopecore.sentence.ColorInfo;
import com.rokuan.calliopecore.sentence.structure.way.WayAdverbial.WayType;

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
}

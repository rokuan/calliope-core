package com.rokuan.calliopecore.sentence.structure.nominal;

import com.rokuan.calliopecore.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.way.WayAdverbial.WayType;


public class VerbalGroup extends NominalGroup implements IWayObject {
	@Override
	public GroupType getGroupType() {
		return GroupType.VERB;
	}

	@Override
	public WayType getWayType() {
		return WayType.VERBAL;
	}
}

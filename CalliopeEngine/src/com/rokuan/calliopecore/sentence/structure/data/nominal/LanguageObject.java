package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.LanguageInfo;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayContext;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;

public class LanguageObject extends NominalGroup implements IWayObject {
	@Expose
	public LanguageInfo language;

	@Override
	public WayType getWayType() {
		return WayType.LANGUAGE;
	}

	@Override
	public GroupType getGroupType() {
		return GroupType.LANGUAGE;
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

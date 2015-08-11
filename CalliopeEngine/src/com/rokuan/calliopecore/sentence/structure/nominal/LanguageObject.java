package com.rokuan.calliopecore.sentence.structure.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.content.IWayObject;
import com.rokuan.calliopecore.sentence.LanguageInfo;
import com.rokuan.calliopecore.sentence.structure.way.WayAdverbial.WayType;

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
}

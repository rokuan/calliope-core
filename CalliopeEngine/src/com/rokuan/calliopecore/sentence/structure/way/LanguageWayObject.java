package com.rokuan.calliopecore.sentence.structure.way;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.LanguageInfo;

public class LanguageWayObject extends WayAdverbial {
	@Expose
	public LanguageInfo language;

	public LanguageWayObject() {
		super(WayType.LANGUAGE);
	}
}

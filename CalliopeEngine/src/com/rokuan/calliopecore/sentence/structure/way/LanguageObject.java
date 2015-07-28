package com.rokuan.calliopecore.sentence.structure.way;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.LanguageInfo;

public class LanguageObject extends WayAdverbial {
	@Expose
	public LanguageInfo language;

	public LanguageObject() {
		super(WayType.LANGUAGE);
	}
}

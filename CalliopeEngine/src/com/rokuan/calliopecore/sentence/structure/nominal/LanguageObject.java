package com.rokuan.calliopecore.sentence.structure.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.LanguageInfo;

public class LanguageObject extends NominalGroup {
	@Expose
	public LanguageInfo language;

	public LanguageObject() {
		super(GroupType.LANGUAGE);
		// TODO Auto-generated constructor stub
	}
}

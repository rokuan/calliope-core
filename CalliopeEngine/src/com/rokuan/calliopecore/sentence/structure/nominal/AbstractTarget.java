package com.rokuan.calliopecore.sentence.structure.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.Type.SourcePronoun;

public class AbstractTarget extends NominalGroup {
	@Expose
	public SourcePronoun source;

	public AbstractTarget(SourcePronoun src) {
		source = src;
	}

	@Override
	public GroupType getGroupType() {
		return GroupType.ABSTRACT;
	}
}

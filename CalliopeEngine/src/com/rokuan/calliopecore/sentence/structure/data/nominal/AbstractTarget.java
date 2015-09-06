package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.IPronoun;

public class AbstractTarget extends NominalGroup {
	@Expose
	public IPronoun source;

	public AbstractTarget(IPronoun src) {
		source = src;
	}

	@Override
	public GroupType getGroupType() {
		return GroupType.ABSTRACT;
	}
}

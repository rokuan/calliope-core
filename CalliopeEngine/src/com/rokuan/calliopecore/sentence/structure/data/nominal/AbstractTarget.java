package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.IPronoun;

public class AbstractTarget extends NominalGroup {
	public final IPronoun source;

	public AbstractTarget(IPronoun src) {
		source = src;
	}

	@Override
	public GroupType getGroupType() {
		return GroupType.ABSTRACT;
	}
}

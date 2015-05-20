package com.rokuan.calliopecore.sentence.structure.nominal;

import com.rokuan.calliopecore.sentence.Type.SourcePronoun;

public class AbstractTarget extends NominalGroup {
	public SourcePronoun source;

	public AbstractTarget(SourcePronoun src) {
		super(GroupType.ABSTRACT);
		source = src;
	}
}

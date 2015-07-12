package com.rokuan.calliopecore.sentence.structure.data.time;

import com.rokuan.calliopecore.sentence.structure.nominal.VerbalGroup;

public class VerbalTimeObject extends TimeObject {
	public DateContext preposition;
	public VerbalGroup condition;

	public VerbalTimeObject() {
		super(TimeType.VERBAL);
	}
}

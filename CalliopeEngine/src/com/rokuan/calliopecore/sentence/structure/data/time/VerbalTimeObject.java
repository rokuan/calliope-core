package com.rokuan.calliopecore.sentence.structure.data.time;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.structure.nominal.VerbalGroup;

public class VerbalTimeObject extends TimeAdverbial {
	@Expose
	public DateContext preposition;

	@Expose
	public VerbalGroup condition;

	public VerbalTimeObject() {
		super(TimeType.VERBAL);
	}
}

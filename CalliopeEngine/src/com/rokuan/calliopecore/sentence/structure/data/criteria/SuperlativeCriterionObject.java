package com.rokuan.calliopecore.sentence.structure.data.criteria;

import com.google.gson.annotations.Expose;

public class SuperlativeCriterionObject extends CriterionObject {
	@Expose
	public ComparisonType compare = ComparisonType.LESS;

	@Expose
	public String criterion;

	public SuperlativeCriterionObject() {
		super(CriterionType.SUPERLATIVE);
	}
}

package com.rokuan.calliopecore.sentence.structure.data.criteria;

public class SuperlativeCriterionObject extends CriterionObject {
	public ComparisonType compare = ComparisonType.LESS;
	public String criterion;

	public SuperlativeCriterionObject() {
		super(CriterionType.SUPERLATIVE);
	}
}

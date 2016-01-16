package com.rokuan.calliopecore.sentence.structure.data.criteria;

public class FieldCriterionObject extends CriterionObject {
	public String field;
	public ComparisonType compare = ComparisonType.LESS;
	public String criterion;
	
	public FieldCriterionObject(){
		super(CriterionType.FIELD_MATCH);
	}
}

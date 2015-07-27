package com.rokuan.calliopecore.sentence.structure.data.criteria;

import com.google.gson.annotations.Expose;

public class FieldCriterionObject extends CriterionObject {
	@Expose
	public String field;

	@Expose
	public ComparisonType compare = ComparisonType.LESS;

	@Expose
	public String criterion;
	
	public FieldCriterionObject(){
		super(CriterionType.FIELD_MATCH);
	}
}

package com.rokuan.calliopecore.sentence.structure.data.criteria;

public abstract class CriterionObject {
	public enum CriterionType {
		SUPERLATIVE,
		FIELD_MATCH
	}
	
	public enum ComparisonType {
		LESS,
		MORE
	}

	private CriterionType criterionType;
	
	public CriterionObject(CriterionType ty){
		criterionType = ty;
	}
	
	public CriterionType getType(){
		return criterionType;
	}
	
	public static ComparisonType parseComparisonType(String v){
		if(v.equals("plus")){
			return ComparisonType.MORE;
		} else {
			return ComparisonType.LESS;
		}
	}
}

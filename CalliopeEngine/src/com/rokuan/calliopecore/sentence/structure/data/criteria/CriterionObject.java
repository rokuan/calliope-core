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
	
	private CriterionType type;
	
	public CriterionObject(CriterionType ty){
		type = ty;
	}
	
	public CriterionType getType(){
		return type;
	}
	
	public static ComparisonType parseComparisonType(String v){
		if(v.equals("plus")){
			return ComparisonType.MORE;
		} else {
			return ComparisonType.LESS;
		}
	}
}

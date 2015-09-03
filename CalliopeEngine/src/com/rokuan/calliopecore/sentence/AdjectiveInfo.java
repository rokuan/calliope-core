package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;

public class AdjectiveInfo {
	public enum AdjectiveValue {
		UNDEFINED,
		NEW,
		OLD,
		SMALL,
		LARGE,
		PREVIOUS,
		CURRENT,
		NEXT,		
		EASY,
		HARD
	}
	
	@Expose
	private AdjectiveValue valueType = AdjectiveValue.UNDEFINED;
	
	@Expose
	private String text;
	
	public AdjectiveInfo(String aText, AdjectiveValue aType){
		
	}
	
	public String getText(){
		return text;
	}
	
	public AdjectiveValue getType(){
		return valueType;
	}	
}

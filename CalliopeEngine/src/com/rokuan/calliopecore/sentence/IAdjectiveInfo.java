package com.rokuan.calliopecore.sentence;

public interface IAdjectiveInfo {
	public enum AdjectiveValue {
		SMALL,
		MEDIUM,
		BIG,
		OLD,
		NEW,
		UNDEFINED
	}
	
	String getValue();
	
	AdjectiveValue getAdjectiveType();
}

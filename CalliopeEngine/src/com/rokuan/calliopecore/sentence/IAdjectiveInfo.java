package com.rokuan.calliopecore.sentence;

public interface IAdjectiveInfo extends IValue {
	public enum AdjectiveValue {
		SMALL,
		MEDIUM,
		BIG,
		OLD,
		NEW,
		UNDEFINED
	}
	
	AdjectiveValue getAdjectiveType();
}

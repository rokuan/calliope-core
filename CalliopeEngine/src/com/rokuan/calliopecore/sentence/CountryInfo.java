package com.rokuan.calliopecore.sentence;

public final class CountryInfo {
	private String name;
	private String code;
	
	public CountryInfo(String n, String c){
		name = n;
		code = c;
	}
	
	public String getName() {
		return name;
	}
	public String getCode() {
		return code;
	}
}

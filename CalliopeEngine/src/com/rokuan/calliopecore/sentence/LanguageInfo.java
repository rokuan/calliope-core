package com.rokuan.calliopecore.sentence;

public final class LanguageInfo {
	private String name;
	private String code;
	
	public LanguageInfo(String n, String c){
		name = n;
		code = c;
	}
	
	public String getName(){
		return name;
	}
	
	public String getCode(){
		return code;
	}
}

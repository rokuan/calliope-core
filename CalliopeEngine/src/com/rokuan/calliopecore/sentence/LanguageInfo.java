package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "languages")
public final class LanguageInfo {
	@DatabaseField(id = true)
	private String code;
	@DatabaseField(unique = true)
	private String name;
	
	public LanguageInfo(){
		
	}
	
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

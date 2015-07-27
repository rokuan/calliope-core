package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "languages")
public final class LanguageInfo {
	public static final String CODE_FIELD_NAME = "code";
	public static final String LANGUAGE_FIELD_NAME = "name";

	@Expose
	@DatabaseField(columnName = LANGUAGE_FIELD_NAME, id = true)
	private String name;

	@Expose
	@DatabaseField(columnName = CODE_FIELD_NAME)
	private String code;
	
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

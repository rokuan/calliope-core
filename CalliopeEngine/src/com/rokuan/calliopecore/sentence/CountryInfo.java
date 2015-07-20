package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "countries")
public final class CountryInfo {
	public static final String COUNTRY_FIELD_NAME = "name";
	public static final String CODE_FIELD_NAME = "code";
	
	@DatabaseField(columnName = COUNTRY_FIELD_NAME, id = true)
	private String name;
	@DatabaseField(columnName = CODE_FIELD_NAME, unique = true)
	private String code;

	public CountryInfo(){
		
	}
	
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

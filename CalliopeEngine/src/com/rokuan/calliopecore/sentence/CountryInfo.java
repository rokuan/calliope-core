package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "countries")
public final class CountryInfo {
	public static final String COUNTRY_FIELD_NAME = "country_name";
	public static final String CODE_FIELD_NAME = "country_code";
	
	@DatabaseField(generatedId = true)
	private int id;

	@Expose
	@DatabaseField(columnName = COUNTRY_FIELD_NAME, uniqueIndex = true)
	private String name;

	@Expose
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

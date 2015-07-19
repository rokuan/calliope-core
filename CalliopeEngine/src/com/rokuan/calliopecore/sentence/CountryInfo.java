package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "countries")
public final class CountryInfo {
	@DatabaseField(id = true)
	private String name;
	@DatabaseField(unique = true)
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

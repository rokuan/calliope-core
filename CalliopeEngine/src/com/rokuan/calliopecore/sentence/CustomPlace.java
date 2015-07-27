package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "places")
public class CustomPlace {
	public static final String PLACE_FIELD_NAME = "name";
	public static final String CODE_FIELD_NAME = "code";
	
	@DatabaseField(id = true)
	private int id;
	@DatabaseField(columnName = PLACE_FIELD_NAME, unique = true, uniqueIndex = true)
	private String name;
	@DatabaseField(columnName = CODE_FIELD_NAME)
	private String code;
	
	public CustomPlace(){
		
	}
	
	public CustomPlace(String n, String c){
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

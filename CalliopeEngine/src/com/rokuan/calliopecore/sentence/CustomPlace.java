package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "places")
public class CustomPlace {
	@DatabaseField(id = true)
	private int id;
	@DatabaseField(unique = true)
	private String name;
	@DatabaseField(unique = true)
	private String code;
	
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

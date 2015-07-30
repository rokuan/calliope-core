package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "people")
public class CustomPerson {
	public static final String PERSON_FIELD_NAME = "name";
	public static final String CODE_FIELD_NAME = "code";
	
	@Expose
	@DatabaseField(columnName = PERSON_FIELD_NAME, id = true)
	private String name;
	
	@Expose
	@DatabaseField(columnName = CODE_FIELD_NAME)
	private String code;
	
	public CustomPerson(){
		
	}
	
	public CustomPerson(String n, String c){
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

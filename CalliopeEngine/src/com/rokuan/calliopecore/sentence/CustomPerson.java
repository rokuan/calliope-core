package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "people")
public class CustomPerson {
	public static final String PERSON_FIELD_NAME = "name";
	
	@Expose
	@DatabaseField(columnName = PERSON_FIELD_NAME, id = true)
	private String name;
	
	public CustomPerson(){
		
	}
	
	public CustomPerson(String n){
		name = n;
	}
}

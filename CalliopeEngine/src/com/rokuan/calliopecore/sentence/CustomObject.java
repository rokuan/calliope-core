package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "objects")
public class CustomObject {
	public static final String OBJECT_FIELD_NAME = "content";
	public static final String CODE_FIELD_NAME = "code";
	
	@DatabaseField(id = true)
	private int id;

	@Expose
	@DatabaseField(columnName = OBJECT_FIELD_NAME, unique = true, uniqueIndex = true)
	private String content;

	@Expose
	@DatabaseField(columnName = CODE_FIELD_NAME)
	private String code;
	
	public CustomObject(){
		
	}
	
	public CustomObject(String con, String cod){
		content = con;
		code = cod;
	}

	public String getContent() {
		return content;
	}

	public String getCode() {
		return code;
	}

	public int getId() {
		return id;
	}
}

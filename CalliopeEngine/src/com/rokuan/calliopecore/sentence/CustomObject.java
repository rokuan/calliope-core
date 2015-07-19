package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "objects")
public class CustomObject {
	@DatabaseField(id = true)
	private int id;
	@DatabaseField
	private String content;
	@DatabaseField(unique = true)
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

package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "modes")
public class CustomMode {
	public static final String MODE_FIELD_NAME = "name";
	public static final String CODE_FIELD_NAME = "code";
	
	@Expose
	@DatabaseField(columnName = MODE_FIELD_NAME, id = true)
	private String content;
	
	@Expose
	@DatabaseField(columnName = CODE_FIELD_NAME)
	private String code;
	
	public CustomMode(){
		
	}
	
	public CustomMode(String text, String c){
		content = text;
		code = c;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}

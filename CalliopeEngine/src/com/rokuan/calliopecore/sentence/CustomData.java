package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;

public abstract class CustomData {
	public static final String DATA_FIELD_NAME = "data_name";
	public static final String CODE_FIELD_NAME = "data_code";
	
	@DatabaseField(generatedId = true)
	private String id;
	
	@Expose
	@DatabaseField(columnName = DATA_FIELD_NAME, uniqueIndex = true)
	private String name;
	
	@Expose
	@DatabaseField(columnName = CODE_FIELD_NAME)
	private String code;
	
	protected CustomData(){
		
	}
	
	protected CustomData(String dataName, String dataCode){
		name = dataName;
		code = dataCode;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
}

package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "names")
public class NameInfo {
	public static final String VALUE_FIELD_NAME = "name_value";
	public static final String TAG_FIELD_NAME = "name_tag";
	
	@DatabaseField(generatedId = true)
	private int id;
	
	@Expose
	@DatabaseField(columnName = VALUE_FIELD_NAME, uniqueIndex = true)
	private String value;
	
	@Expose
	@DatabaseField(columnName = TAG_FIELD_NAME)
	private String tag;
	
	public NameInfo(){
		
	}
	
	public NameInfo(String nameValue, String nameTag){
		value = nameValue;
		tag = nameTag;
	}
	
	public String getValue(){
		return value;
	}
	
	public String getTag(){
		return tag;
	}
}

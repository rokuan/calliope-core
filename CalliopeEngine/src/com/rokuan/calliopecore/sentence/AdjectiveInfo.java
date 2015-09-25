package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "adjectives")
public class AdjectiveInfo {
	public static final String TYPE_FIELD_NAME = "adjective_type";
	public static final String VALUE_FIELD_NAME = "adjective_value";
	
	public enum AdjectiveValue {
		UNDEFINED,
		NEW,
		OLD,
		SMALL,
		LARGE,
		PREVIOUS,
		CURRENT,
		NEXT,		
		EASY,
		HARD
	}
	
	@DatabaseField(generatedId = true)
	private int id;
	
	@Expose
	@DatabaseField(columnName = TYPE_FIELD_NAME)
	private AdjectiveValue adjectiveType = AdjectiveValue.UNDEFINED;
	
	@Expose
	@DatabaseField(columnName = VALUE_FIELD_NAME, uniqueIndex = true)
	private String text;
	
	public AdjectiveInfo(String aText, AdjectiveValue aType){
		text = aText;
		adjectiveType = aType;
	}
	
	public String getText(){
		return text;
	}
	
	public AdjectiveValue getType(){
		return adjectiveType;
	}	
}

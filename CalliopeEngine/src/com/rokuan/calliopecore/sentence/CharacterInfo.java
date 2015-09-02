package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.nominal.CharacterObject.CharacterType;

@DatabaseTable(tableName = "characters")
public class CharacterInfo {
	public static final String CHARACTER_FIELD_NAME = "character";
	public static final String TYPE_FIELD_NAME = "character_type";
	
	@Expose
	@DatabaseField(columnName = CHARACTER_FIELD_NAME, id = true)
	private String name;
	
	@Expose
	@DatabaseField(columnName = TYPE_FIELD_NAME)
	private CharacterType characterType;
	
	public CharacterInfo(){
		
	}
	
	public CharacterInfo(String cName, CharacterType cType){
		name = cName;
		characterType = cType;
	}
	
	public String getName(){
		return name;
	}
	
	public CharacterType getCharacterType(){
		return characterType;
	}
}

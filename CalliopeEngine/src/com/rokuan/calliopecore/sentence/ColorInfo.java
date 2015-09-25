package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "colors")
public class ColorInfo {
	public static final String COLOR_FIELD_NAME = "color_name";
	public static final String HEX_FIELD_NAME = "color_hexcode";
	
	@DatabaseField(generatedId = true)
	private int id;
	
	@Expose
	@DatabaseField(columnName = COLOR_FIELD_NAME, uniqueIndex = true)
	private String name;
	
	@Expose
	@DatabaseField(columnName = HEX_FIELD_NAME)
	private String hexColor;
	
	public ColorInfo(){
		
	}
	
	public ColorInfo(String colorName, String colorHexCode){
		name = colorName;
		hexColor = colorHexCode;
	}
	
	public String getName(){
		return name;
	}
	
	public String getHexColorCode(){
		return hexColor;
	}
}

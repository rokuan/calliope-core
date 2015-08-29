package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.nominal.UnitObject.UnitType;

@DatabaseTable(tableName = "units")
public class UnitInfo {
	public static final String UNIT_FIELD_NAME = "name";
	public static final String TYPE_FIELD_NAME = "unit_type";
	
	//@Expose
	@DatabaseField(columnName = UNIT_FIELD_NAME, id = true)
	private String name;
	
	//@Expose
	@DatabaseField(columnName = TYPE_FIELD_NAME)
	private UnitType unitType;
	
	public UnitInfo(){
		
	}
	
	public UnitInfo(String uName, UnitType uType){
		name = uName;
		unitType = uType;
	}
	
	public String getName(){
		return name;
	}
	
	public UnitType getUnitType(){
		return unitType;
	}
}

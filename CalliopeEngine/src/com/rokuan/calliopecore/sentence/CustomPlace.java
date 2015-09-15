package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "places")
public class CustomPlace extends CustomData {
	public CustomPlace(){
		
	}
	
	public CustomPlace(String name, String code){
		super(name, code);
	}
}

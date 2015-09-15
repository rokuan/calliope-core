package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "people")
public class CustomPerson extends CustomData {	
	public CustomPerson(){
		
	}
	
	public CustomPerson(String name, String code){
		super(name, code);
	}
}

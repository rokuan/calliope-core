package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "objects")
public class CustomObject extends CustomData {
	public CustomObject(){
		
	}
	
	public CustomObject(String name, String code){
		super(name, code);
	}
}

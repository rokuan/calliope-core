package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "modes")
public class CustomMode extends CustomData {	
	public CustomMode(){
		
	}
	
	public CustomMode(String text, String code){
		super(text, code);
	}
}

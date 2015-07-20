package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeObject.DateContext;

@DatabaseTable(tableName = "time_prepositions")
public class TimePreposition {
	public static final String VALUE_FIELD_NAME = "value";
	public static final String PREPOSITION_FIELD_NAME = "preposition"; 
	
	@DatabaseField(columnName = VALUE_FIELD_NAME, id = true)
	private String name;
	@DatabaseField(columnName = PREPOSITION_FIELD_NAME)
	private DateContext timePreposition;
	
	public TimePreposition(){
		
	}
	
	public TimePreposition(String v, DateContext prep){
		name = v;
		timePreposition = prep;
	}

	public String getName() {
		return name;
	}

	public DateContext getTimePreposition() {
		return timePreposition;
	}
}

package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayContext;


@DatabaseTable(tableName = "way_prepositions")
public class WayPreposition {
	public static final String VALUE_FIELD_NAME = "value";
	public static final String PREPOSITION_FIELD_NAME = "preposition"; 	

	@Expose
	@DatabaseField(columnName = VALUE_FIELD_NAME, id = true)
	private String name;

	@Expose
	@DatabaseField(columnName = PREPOSITION_FIELD_NAME)
	private WayContext wayPreposition;
	
	public WayPreposition(){
		
	}
	
	public WayPreposition(String v, WayContext prep){
		name = v;
		wayPreposition = prep;
	}

	public String getName() {
		return name;
	}

	public WayContext getValue() {
		return wayPreposition;
	}
}

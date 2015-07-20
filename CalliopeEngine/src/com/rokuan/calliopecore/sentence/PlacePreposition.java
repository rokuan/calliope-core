package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject.PlaceContext;

@DatabaseTable(tableName = "place_prepositions")
public class PlacePreposition {
	public static final String VALUE_FIELD_NAME = "value";
	public static final String PREPOSITION_FIELD_NAME = "preposition"; 
	
	@DatabaseField(columnName = VALUE_FIELD_NAME, id = true)
	private String name;
	@DatabaseField(columnName = PREPOSITION_FIELD_NAME)
	private PlaceContext placePreposition;
	
	public PlacePreposition(){
		
	}
	
	public PlacePreposition(String v, PlaceContext prep){
		name = v;
		placePreposition = prep;
	}

	public String getName() {
		return name;
	}

	public PlaceContext getPlacePreposition() {
		return placePreposition;
	}
}

package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject.PlaceCategory;

@DatabaseTable(tableName = "places")
public class PlaceInfo {
	public static final String PLACE_FIELD_NAME = "place_name";
	public static final String TYPE_FIELD_NAME = "place_type";
	
	//@Expose
	@DatabaseField(columnName = PLACE_FIELD_NAME, id = true)
	private String name;
	
	//@Expose
	@DatabaseField(columnName = TYPE_FIELD_NAME)
	private PlaceCategory placeCategory;
	
	public PlaceInfo() {

	}
	
	public PlaceInfo(String pName, PlaceCategory pCategory){
		name = pName;
		placeCategory = pCategory;
	}
	
	public String getName(){
		return name;
	}
	
	public PlaceCategory getPlaceCategory(){
		return placeCategory;
	}
}

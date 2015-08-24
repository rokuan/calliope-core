package com.rokuan.calliopecore.sentence;

import java.util.HashSet;
import java.util.Set;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceContext;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;

@DatabaseTable(tableName = "place_prepositions")
public class PlacePreposition {	
	public static final String VALUE_FIELD_NAME = "value";
	public static final String PREPOSITION_FIELD_NAME = "preposition";
	public static final String FOLLOWERS_FIELD_NAME = "followers";

	//@Expose
	@DatabaseField(columnName = VALUE_FIELD_NAME, id = true)
	private String name;

	//@Expose
	@DatabaseField(columnName = PREPOSITION_FIELD_NAME)
	private PlaceContext placePreposition;
	
	//@Expose
	@DatabaseField(columnName = FOLLOWERS_FIELD_NAME, dataType = DataType.SERIALIZABLE)
	private HashSet<PlaceType> followersTypes = new HashSet<PlaceType>();
	
	public PlacePreposition(){
		
	}
	
	public PlacePreposition(String v, PlaceContext prep){
		name = v;
		placePreposition = prep;
	}
	
	public PlacePreposition(String v, PlaceContext prep, Set<PlaceType> types){
		this(v, prep);
		followersTypes.addAll(types);
	}

	public String getName() {
		return name;
	}

	public PlaceContext getValue() {
		return placePreposition;
	}
	
	public boolean canBeFollowedBy(PlaceType ty){
		return followersTypes.contains(ty);
	}
}

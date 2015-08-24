package com.rokuan.calliopecore.sentence;

import java.util.HashSet;
import java.util.Set;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayContext;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;

@DatabaseTable(tableName = "way_prepositions")
public class WayPreposition extends Preposition {
	public static final String VALUE_FIELD_NAME = "value";
	public static final String PREPOSITION_FIELD_NAME = "preposition";
	public static final String FOLLOWERS_FIELD_NAME = "followers";

	//@Expose
	@DatabaseField(columnName = VALUE_FIELD_NAME, id = true)
	private String name;

	//@Expose
	@DatabaseField(columnName = PREPOSITION_FIELD_NAME)
	private WayContext wayPreposition;
	
	@DatabaseField(columnName = FOLLOWERS_FIELD_NAME, dataType = DataType.SERIALIZABLE)
	private HashSet<WayType> followersTypes = new HashSet<WayType>();
	
	public WayPreposition(){
		
	}
	
	public WayPreposition(String v, WayContext prep){
		name = v;
		wayPreposition = prep;
	}
	
	public WayPreposition(String v, WayContext prep, Set<WayType> types){
		this(v, prep);
		followersTypes.addAll(types);
	}

	public String getName() {
		return name;
	}

	public WayContext getValue() {
		return wayPreposition;
	}

	public boolean canBeFollowedBy(WayType ty) {
		return followersTypes.contains(ty);
	}
}

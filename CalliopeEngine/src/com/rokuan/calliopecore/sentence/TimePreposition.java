package com.rokuan.calliopecore.sentence;

import java.util.HashSet;
import java.util.Set;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.DateContext;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeType;

@DatabaseTable(tableName = "time_prepositions")
public class TimePreposition extends Preposition {
	public static final String VALUE_FIELD_NAME = "value";
	public static final String PREPOSITION_FIELD_NAME = "preposition"; 
	public static final String FOLLOWERS_FIELD_NAME = "followers";

	//@Expose
	@DatabaseField(columnName = VALUE_FIELD_NAME, id = true)
	private String name;

	//@Expose
	@DatabaseField(columnName = PREPOSITION_FIELD_NAME)
	private DateContext timePreposition;
	
	@DatabaseField(columnName = FOLLOWERS_FIELD_NAME, dataType = DataType.SERIALIZABLE)
	private HashSet<TimeType> followersTypes = new HashSet<TimeType>();
	
	public TimePreposition(){
		
	}
	
	public TimePreposition(String v, DateContext prep){
		name = v;
		timePreposition = prep;
	}
	
	public TimePreposition(String v, DateContext prep, Set<TimeType> types){
		this(v, prep);
		followersTypes.addAll(types);
	}

	public String getName() {
		return name;
	}

	public DateContext getValue() {
		return timePreposition;
	}

	public boolean canBeFollowedBy(TimeType ty) {
		return followersTypes.contains(ty);
	}
}

package com.rokuan.calliopecore.sentence;

import java.util.HashSet;
import java.util.Set;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial.PurposeContext;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial.PurposeType;

@DatabaseTable(tableName = "purpose_prepositions")
public class PurposePreposition extends Preposition {
	public static final String VALUE_FIELD_NAME = "value";
	public static final String PREPOSITION_FIELD_NAME = "preposition";
	public static final String FOLLOWERS_FIELD_NAME = "followers";

	//@Expose
	@DatabaseField(columnName = VALUE_FIELD_NAME, id = true)
	private String name;

	//@Expose
	@DatabaseField(columnName = PREPOSITION_FIELD_NAME)
	private PurposeContext purposePreposition;
	
	@DatabaseField(columnName = FOLLOWERS_FIELD_NAME, dataType = DataType.SERIALIZABLE)
	private HashSet<PurposeType> followersTypes = new HashSet<PurposeType>();
	
	public PurposePreposition(){
		
	}
	
	public PurposePreposition(String v, PurposeContext prep){
		name = v;
		purposePreposition = prep;
	}
	
	public PurposePreposition(String v, PurposeContext prep, Set<PurposeType> types){
		this(v, prep);
		followersTypes.addAll(types);
	}
	
	public PurposePreposition(String v, PurposeContext prep, PurposeType... types){
		this(v, prep);

		for(int i=0; i<types.length; i++){
			followersTypes.add(types[i]);
		}
	}

	public String getName() {
		return name;
	}

	public PurposeContext getValue() {
		return purposePreposition;
	}
	
	public boolean canBeFollowedBy(PurposeType ty){
		return followersTypes.contains(ty);
	}
}

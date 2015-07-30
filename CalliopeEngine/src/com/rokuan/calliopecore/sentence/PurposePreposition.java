package com.rokuan.calliopecore.sentence;

import com.google.gson.annotations.Expose;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial.PurposeContext;

@DatabaseTable(tableName = "purpose_prepositions")
public class PurposePreposition {
	public static final String VALUE_FIELD_NAME = "value";
	public static final String PREPOSITION_FIELD_NAME = "preposition"; 	

	@Expose
	@DatabaseField(columnName = VALUE_FIELD_NAME, id = true)
	private String name;

	@Expose
	@DatabaseField(columnName = PREPOSITION_FIELD_NAME)
	private PurposeContext purposePreposition;
	
	public PurposePreposition(){
		
	}
	
	public PurposePreposition(String v, PurposeContext prep){
		name = v;
		purposePreposition = prep;
	}

	public String getName() {
		return name;
	}

	public PurposeContext getPurposePreposition() {
		return purposePreposition;
	}
}

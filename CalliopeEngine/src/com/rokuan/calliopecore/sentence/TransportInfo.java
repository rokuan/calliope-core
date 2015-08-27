package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.way.TransportObject.TransportType;

@DatabaseTable(tableName = "transports")
public class TransportInfo {
	public static final String VALUE_FIELD_NAME = "name";
	public static final String TRANSPORT_FIELD_NAME = "transport_type";
	
	//@Expose
	@DatabaseField(columnName = VALUE_FIELD_NAME, id = true)
	private String name;
	
	//@Expose
	@DatabaseField(columnName = TRANSPORT_FIELD_NAME)
	private TransportType transportType;
	
	public TransportInfo(){
		
	}
	
	public TransportInfo(String tName, TransportType tType){
		name = tName;
		transportType = tType;
	}
	
	public String getName(){
		return name;
	}
	
	public TransportType getTransportType(){
		return transportType;
	}
}

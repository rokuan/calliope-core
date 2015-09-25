package com.rokuan.calliopecore.sentence;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.rokuan.calliopecore.sentence.structure.data.way.TransportObject.TransportType;

@DatabaseTable(tableName = "transports")
public class TransportInfo {
	public static final String TRANSPORT_FIELD_NAME = "transport_name";
	public static final String TYPE_FIELD_NAME = "transport_type";
	
	@DatabaseField(generatedId = true)
	private int id;
	
	//@Expose
	@DatabaseField(columnName = TRANSPORT_FIELD_NAME, id = true)
	private String name;
	
	//@Expose
	@DatabaseField(columnName = TYPE_FIELD_NAME)
	private TransportType transportType;
	
	TransportInfo(){
		
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

package com.rokuan.calliopecore.test;

import org.junit.Test;

import com.google.gson.GsonBuilder;
import com.rokuan.calliopecore.json.NominalGroupSerializer;
import com.rokuan.calliopecore.sentence.CityInfo;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.place.LocationObject;

public class SerializerTest {
	@Test
	public void nominalGroupTest(){
		LocationObject location = new LocationObject();
		location.city = new CityInfo("Paris", -2, 48);
		
		INominalObject nominal = location;
		
		GsonBuilder builder = new GsonBuilder();
		
		builder.registerTypeAdapter(INominalObject.class, new NominalGroupSerializer());
		
		System.out.println(builder.create().toJson(nominal, INominalObject.class));
	}
}

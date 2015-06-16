package com.rokuan.calliopecore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.CityInfo;
import com.rokuan.calliopecore.sentence.CountryInfo;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.PlaceConverter;
import com.rokuan.calliopecore.sentence.structure.data.place.MonumentObject;
import com.rokuan.calliopecore.sentence.structure.data.place.StateObject;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup.GroupType;

public class PlaceParseTest {

	@Test
	public void testCountryParse(){
		WordBuffer words = new WordBuffer();
		Word france = new Word("France", WordType.COUNTRY);
		
		france.setCountryInfo(new CountryInfo("France", "FR"));
		
		words.add(new Word("en", WordType.PREPOSITION_IN));
		words.add(france);

		NominalGroup place = PlaceConverter.parsePlaceObject(words);

		assertEquals(place.getType(), GroupType.STATE);

		StateObject state = (StateObject)place;

		assertEquals(state.city, null);
		assertEquals(state.country.getName(), "France");
	}

	@Test
	public void testCityAndCountryParse(){
		WordBuffer words = new WordBuffer();
		Word paris = new Word("Paris", WordType.CITY);
		Word france = new Word("France", WordType.COUNTRY);

		paris.setCityInfo(new CityInfo("Paris", 48.8564528, 2.3524282));
		france.setCountryInfo(new CountryInfo("France", "FR"));
		
		words.add(new Word("à", WordType.PREPOSITION_AT));
		words.add(paris);
		words.add(new Word("en", WordType.PREPOSITION_IN));
		words.add(france);

		NominalGroup place = PlaceConverter.parsePlaceObject(words);

		assertEquals(place.getType(), GroupType.STATE);

		StateObject state = (StateObject)place;

		assertEquals(state.city.getName(), "Paris");
		assertEquals(state.country.getName(), "France");
	}
	
	@Test
	public void testMonumentParse(){
		WordBuffer words = new WordBuffer();
		
		words.add(new Word("à", WordType.PREPOSITION_AT));
		words.add(new Word("la", WordType.DEFINITE_ARTICLE));
		words.add(new Word("Tour", WordType.PROPER_NAME));
		words.add(new Word("Eiffel", WordType.PROPER_NAME));
		
		NominalGroup place = PlaceConverter.parsePlaceObject(words);
		
		assertEquals(place.getType(), GroupType.PLACE);
		
		MonumentObject monument = (MonumentObject)place;
		
		assertEquals(monument.name, "Tour Eiffel");
	}
	
	@Test
	public void testCommonPlaceParse(){
		WordBuffer words = new WordBuffer();
		
		words.add(new Word("à", WordType.PREPOSITION_AT));
		words.add(new Word("la", WordType.DEFINITE_ARTICLE));
		words.add(new Word("Mairie", WordType.PLACE_TYPE, WordType.PROPER_NAME, WordType.COMMON_NAME));
		words.add(new Word("de", WordType.PREPOSITION_OF));
		words.add(new Word("Paris", WordType.PROPER_NAME, WordType.CITY));
		
		NominalGroup place = PlaceConverter.parsePlaceObject(words);
		
		assertEquals(place.getType(), GroupType.PLACE);
		
		MonumentObject monument = (MonumentObject)place;
		
		assertEquals(monument.name, "Mairie");
		assertEquals(monument.city, "Paris");
	}

}

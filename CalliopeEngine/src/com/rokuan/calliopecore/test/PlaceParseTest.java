package com.rokuan.calliopecore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.PlaceConverter;
import com.rokuan.calliopecore.sentence.structure.data.place.MonumentObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject.PlaceType;
import com.rokuan.calliopecore.sentence.structure.data.place.StateObject;

public class PlaceParseTest {

	@Test
	public void testCountryParse(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("en", WordType.PREPOSITION_IN));
		words.add(new Word("France", WordType.PROPER_NAME));

		PlaceObject place = PlaceConverter.parsePlaceObject(words);

		assertEquals(place.getType(), PlaceType.STATE);

		StateObject state = (StateObject)place;

		assertEquals(state.city, null);
		assertEquals(state.country, "France");
	}

	@Test
	public void testCityAndCountryParse(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("à", WordType.PREPOSITION_AT));
		words.add(new Word("Paris", WordType.PROPER_NAME));
		words.add(new Word("en", WordType.PREPOSITION_IN));
		words.add(new Word("France", WordType.PROPER_NAME));

		PlaceObject place = PlaceConverter.parsePlaceObject(words);

		assertEquals(place.getType(), PlaceType.STATE);

		StateObject state = (StateObject)place;

		assertEquals(state.city, "Paris");
		assertEquals(state.country, "France");
	}
	
	@Test
	public void testMonumentParse(){
		WordBuffer words = new WordBuffer();
		
		words.add(new Word("à", WordType.PREPOSITION_AT));
		words.add(new Word("la", WordType.DEFINITE_ARTICLE));
		words.add(new Word("Tour", WordType.PROPER_NAME));
		words.add(new Word("Eiffel", WordType.PROPER_NAME));
		
		PlaceObject place = PlaceConverter.parsePlaceObject(words);
		
		assertEquals(place.getType(), PlaceType.MONUMENT);
		
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
		
		PlaceObject place = PlaceConverter.parsePlaceObject(words);
		
		assertEquals(place.getType(), PlaceType.MONUMENT);
		
		MonumentObject monument = (MonumentObject)place;
		
		assertEquals(monument.name, "Mairie");
		assertEquals(monument.city, "Paris");
	}

}

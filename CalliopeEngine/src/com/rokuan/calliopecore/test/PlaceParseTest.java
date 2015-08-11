package com.rokuan.calliopecore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rokuan.calliopecore.content.IPlaceObject;
import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.CityInfo;
import com.rokuan.calliopecore.sentence.CountryInfo;
import com.rokuan.calliopecore.sentence.CustomPlace;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.PlaceConverter;
import com.rokuan.calliopecore.sentence.structure.data.place.AdditionalPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.LocationObject;
import com.rokuan.calliopecore.sentence.structure.data.place.NamedPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceContext;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;


public class PlaceParseTest {
	@Test
	public void testCityParse(){
		WordBuffer words = new WordBuffer();
		Word paris = new Word("Paris", WordType.CITY, WordType.PROPER_NAME);

		paris.setCityInfo(new CityInfo("Paris", 48.8564528, 2.3524282));
		
		words.add(new Word("à", WordType.PREPOSITION_AT));
		words.add(paris);

		IPlaceObject place = PlaceConverter.parsePlaceAdverbial(words);

		assertEquals(place.getPlaceType(), PlaceType.LOCATION);

		LocationObject state = (LocationObject)place;

		assertEquals(state.city.getName(), "Paris");
		assertNull(state.country);
	}
	
	@Test
	public void testCountryParse(){
		WordBuffer words = new WordBuffer();
		Word france = new Word("France", WordType.COUNTRY);
		
		france.setCountryInfo(new CountryInfo("France", "FR"));
		
		words.add(new Word("en", WordType.PREPOSITION_IN));
		words.add(france);

		IPlaceObject place = PlaceConverter.parsePlaceAdverbial(words);

		assertEquals(place.getPlaceType(), PlaceType.LOCATION);

		LocationObject state = (LocationObject)place;

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

		IPlaceObject place = PlaceConverter.parsePlaceAdverbial(words);

		assertEquals(place.getPlaceType(), PlaceType.LOCATION);

		LocationObject state = (LocationObject)place;

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
		
		IPlaceObject place = PlaceConverter.parsePlaceAdverbial(words);		
		NamedPlaceObject monument = (NamedPlaceObject)place;
		
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
		
		IPlaceObject place = PlaceConverter.parsePlaceAdverbial(words);		
		NamedPlaceObject monument = (NamedPlaceObject)place;
		
		assertEquals(monument.name, "Mairie");
		assertEquals(monument.city, "Paris");
	}

	@Test
	public void testPlaceWithPreposition(){
		WordBuffer words = new WordBuffer();
		String placeName = "Mont Compote Energie";
		Word near = new Word("à proximité de", WordType.PLACE_PREPOSITION);
		near.setPlacePreposition(PlaceContext.NEAR);
		Word mountCompoteEnergie = new Word(placeName, WordType.ADDITIONAL_PLACE);
		mountCompoteEnergie.setCustomPlace(new CustomPlace(placeName, "MOUNT_COMP"));
		
		words.add(near);
		words.add(new Word("le", WordType.DEFINITE_ARTICLE));
		words.add(mountCompoteEnergie);
		
		IPlaceObject place = PlaceConverter.parsePlaceAdverbial(words);
		
		assertEquals(place.getPlaceType(), PlaceType.CUSTOM);
		
		AdditionalPlaceObject customPlace = (AdditionalPlaceObject)place;
		
		assertEquals(customPlace.location, PlaceContext.NEAR);
		assertEquals(customPlace.place.getName(), placeName);
	}	
}

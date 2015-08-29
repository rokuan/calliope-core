package com.rokuan.calliopecore.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.CityInfo;
import com.rokuan.calliopecore.sentence.PlacePreposition;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.PlaceConverter;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceContext;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;

public class PatternTrapTest {
	@Test
	public void parseCityAndWay(){
		WordBuffer words = new WordBuffer();
		Word paris = new Word("Paris", WordType.CITY);
		Word to = new Word("à", WordType.PLACE_PREPOSITION);

		paris.setCityInfo(new CityInfo("Paris", 48.8564528, 2.3524282));
		to.setPlacePreposition(new PlacePreposition("à", PlaceContext.TO, PlaceType.CITY));

		words.add(to);
		words.add(paris);
		words.add(new Word("en", WordType.PREPOSITION));
		words.add(new Word("voiture", WordType.MEAN_OF_TRANSPORT, WordType.COMMON_NAME));

		assertTrue(words.syntaxStartsWith(PlaceConverter.CITY_PATTERN));
	}
}

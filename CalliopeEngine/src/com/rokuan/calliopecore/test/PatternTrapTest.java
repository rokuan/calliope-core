package com.rokuan.calliopecore.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.CityInfo;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.PlaceConverter;

public class PatternTrapTest {
	@Test
	public void parseCityAndWay(){
		WordBuffer words = new WordBuffer();
		Word paris = new Word("Paris", WordType.CITY);

		paris.setCityInfo(new CityInfo("Paris", 48.8564528, 2.3524282));

		words.add(new Word("à", WordType.PREPOSITION_AT));
		words.add(paris);
		words.add(new Word("en", WordType.PREPOSITION));
		words.add(new Word("voiture", WordType.MEAN_OF_TRANSPORT, WordType.COMMON_NAME));

		assertTrue(words.syntaxStartsWith(PlaceConverter.WORLD_PLACE_PATTERN));
	}
}

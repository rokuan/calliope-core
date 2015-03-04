package com.rokuan.calliopecore.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.DateConverter;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeObject;
import com.rokuan.calliopecore.sentence.structure.data.time.TimePeriodObject;

public class DateParseTest {

	@Test
	public void testParseFromToDate(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("du", Word.WordType.PREPOSITION_FROM));
		words.add(new Word("1", Word.WordType.NUMBER));
		words.add(new Word("janvier", Word.WordType.DATE_MONTH));
		words.add(new Word("2012", WordType.NUMBER));
		words.add(new Word("au", Word.WordType.PREPOSITION_TO));
		words.add(new Word("7", Word.WordType.NUMBER));
		words.add(new Word("mars", Word.WordType.DATE_MONTH));
		words.add(new Word("2015", Word.WordType.NUMBER));

		TimeObject dateObj = DateConverter.parseDateObject(words);

		assert (dateObj != null);

		assertEquals(dateObj.getType(), TimeObject.TimeType.PERIOD);

		TimePeriodObject period = (TimePeriodObject)dateObj;

		System.out.println(period.from);
		System.out.println(period.to);
	}

	@Test
	public void testParseBetweenDate(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("entre", Word.WordType.PREPOSITION_BETWEEN));
		words.add(new Word("le", Word.WordType.DEFINITE_ARTICLE));
		words.add(new Word("1er", Word.WordType.NUMERICAL_POSITION));
		words.add(new Word("mai", Word.WordType.DATE_MONTH));
		words.add(new Word("2012", WordType.NUMBER));
		words.add(new Word("et", Word.WordType.PREPOSITION_AND));
		words.add(new Word("le", Word.WordType.DEFINITE_ARTICLE));
		words.add(new Word("4", Word.WordType.NUMBER));
		words.add(new Word("septembre", Word.WordType.DATE_MONTH));
		words.add(new Word("2013", Word.WordType.NUMBER));

		TimeObject dateObj = DateConverter.parseDateObject(words);

		assert (dateObj != null);

		assertEquals(dateObj.getType(), TimeObject.TimeType.PERIOD);

		TimePeriodObject period = (TimePeriodObject)dateObj;

		System.out.println(period.from);
		System.out.println(period.to);
	}
}

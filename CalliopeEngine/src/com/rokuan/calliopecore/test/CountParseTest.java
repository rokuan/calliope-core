package com.rokuan.calliopecore.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.NumberConverter;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.count.FixedItemObject;
import com.rokuan.calliopecore.sentence.structure.data.count.LimitedItemsObject;
import com.rokuan.calliopecore.sentence.structure.data.count.MultipleItemsObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject.CountType;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject.Range;

public class CountParseTest {
	@Test
	public void testSimplePosition() {
		WordBuffer words = new WordBuffer();
		words.add(new Word("le", WordType.DEFINITE_ARTICLE));
		words.add(new Word("premier", WordType.NUMERICAL_POSITION));

		CountObject count = NumberConverter.parseCountObject(words); 

		assert (count != null);

		//assertEquals(count.count, 1);
		assertEquals(count.getType(), CountType.FIXED);

		FixedItemObject fixed = (FixedItemObject)count;

		assertEquals(fixed.position, 1);
	}

	@Test
	public void testRangeForFirst(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("les", WordType.DEFINITE_ARTICLE));
		words.add(new Word("5", WordType.NUMBER));
		words.add(new Word("premiers", WordType.NUMERICAL_POSITION));

		CountObject count = NumberConverter.parseCountObject(words);

		assert (count != null);

		assertEquals(count.getType(), CountType.LIMIT);

		LimitedItemsObject limit = (LimitedItemsObject)count;

		assertEquals(limit.range, Range.FIRST);
		assertEquals(limit.count, 5);
	}

	@Test
	public void testRangeForLast(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("les", WordType.DEFINITE_ARTICLE));
		words.add(new Word("7", WordType.NUMBER));
		words.add(new Word("derniers", WordType.NUMERICAL_POSITION));

		CountObject count = NumberConverter.parseCountObject(words);

		assert (count != null);

		assertEquals(count.getType(), CountType.LIMIT);

		LimitedItemsObject limit = (LimitedItemsObject)count;

		assertEquals(limit.range, Range.LAST);
		assertEquals(limit.count, 7);
	}
	
	@Test
	public void testMutiple1(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("numéros", WordType.COMMON_NAME));
		words.add(new Word("4", WordType.NUMBER));
		words.add(new Word("5", WordType.NUMBER));
		words.add(new Word("et", WordType.PREPOSITION_AND));
		words.add(new Word("15", WordType.NUMBER));
		
		CountObject count = NumberConverter.parseSuffixCountObject(words);
		
		assert(count != null);
		
		assertEquals(count.getType(), CountType.MULTIPLE);
		
		MultipleItemsObject multiple = (MultipleItemsObject)count;
		
		assertEquals(multiple.items.length, 3);
		
		Integer[] trueElements = { 4, 5, 15 };
		
		assertArrayEquals(trueElements, multiple.items);
	}
	
	@Test
	public void testMutiple2(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("les", WordType.DEFINITE_ARTICLE));
		words.add(new Word("5ème", WordType.NUMERICAL_POSITION));
		words.add(new Word("8ème", WordType.NUMERICAL_POSITION));
		words.add(new Word("et", WordType.PREPOSITION_AND));
		words.add(new Word("19ème", WordType.NUMERICAL_POSITION));
		
		CountObject count = NumberConverter.parseCountObject(words);
		
		assert(count != null);
		
		assertEquals(count.getType(), CountType.MULTIPLE);
		
		MultipleItemsObject multiple = (MultipleItemsObject)count;
		
		assertEquals(multiple.items.length, 3);
		
		Integer[] trueElements = { 5, 8, 19 };
		
		assertArrayEquals(trueElements, multiple.items);
	}
}

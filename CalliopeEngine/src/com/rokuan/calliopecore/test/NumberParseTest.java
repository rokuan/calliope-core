package com.rokuan.calliopecore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.NumberConverter;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject.CountType;
import com.rokuan.calliopecore.sentence.structure.data.count.CountObject.Range;

public class NumberParseTest {

	@Test
	public void testSimplePosition() {
		WordBuffer words = new WordBuffer();
		words.add(new Word("premier", WordType.NUMERICAL_POSITION));

		CountObject count = NumberConverter.parseCountObject(words); 

		assert (count != null);

		assertEquals(count.count, 1);
		assertEquals(count.countType, CountType.LIMIT);
		assertEquals(count.position, 1);
		assertEquals(count.range, Range.FIXED);
	}

	@Test
	public void testRangeForFirst(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("5", WordType.NUMBER));
		words.add(new Word("premiers", WordType.NUMERICAL_POSITION));

		CountObject count = NumberConverter.parseCountObject(words);

		assert (count != null);

		assertEquals(count.count, 5);
		assertEquals(count.countType, CountType.LIMIT);
		assertEquals(count.range, Range.FIRST);
	}

	@Test
	public void testRangeForLast(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("7", WordType.NUMBER));
		words.add(new Word("derniers", WordType.NUMERICAL_POSITION));

		CountObject count = NumberConverter.parseCountObject(words);

		assert (count != null);

		assertEquals(count.count, 7);
		assertEquals(count.countType, CountType.LIMIT);
		assertEquals(count.range, Range.LAST);
	}
}

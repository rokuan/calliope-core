package com.rokuan.calliopecore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.DateConverter;

public class SyntaxTest {

	@Test
	public void test() {
		WordBuffer words = new WordBuffer();
		words.add(new Word("du", Word.WordType.PREPOSITION_FROM));
		words.add(new Word("1", Word.WordType.NUMBER));
		words.add(new Word("janvier", Word.WordType.DATE_MONTH));
		words.add(new Word("2012", WordType.NUMBER));
		words.add(new Word("au", Word.WordType.PREPOSITION_TO));
		words.add(new Word("7", Word.WordType.NUMBER));
		words.add(new Word("mars", Word.WordType.DATE_MONTH));
		words.add(new Word("2015", Word.WordType.NUMBER));

		// PREPOSITION_FROM (NUMBER | NUMERICAL_POSITION) (DATE_MONTH NUMBER?)? PREPOSITION_TO NUMBER DATE_MONTH NUMBER
		assertEquals(true, WordPattern.syntaxStartsWith(words,
				WordPattern.simple(Word.WordType.PREPOSITION_FROM),
				WordPattern.or(WordPattern.simple(Word.WordType.NUMERICAL_POSITION), WordPattern.simple(Word.WordType.NUMBER)),
				WordPattern.optional(WordPattern.sequence(WordPattern.simple(WordType.DATE_MONTH), WordPattern.optional(WordPattern.simple(WordType.NUMBER)))),
				WordPattern.simple(Word.WordType.PREPOSITION_TO),
				WordPattern.or(WordPattern.simple(Word.WordType.NUMBER), WordPattern.simple(Word.WordType.NUMERICAL_POSITION)),
				WordPattern.simple(Word.WordType.DATE_MONTH),
				WordPattern.optional(WordPattern.simple(Word.WordType.NUMBER))
				));
	}

	@Test
	public void testSequence(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("du", Word.WordType.PREPOSITION_FROM));
		words.add(new Word("1", Word.WordType.NUMBER));
		words.add(new Word("janvier", Word.WordType.DATE_MONTH));
		words.add(new Word("2012", WordType.NUMBER));
		words.add(new Word("au", Word.WordType.PREPOSITION_TO));
		words.add(new Word("7", Word.WordType.NUMBER));
		words.add(new Word("mars", Word.WordType.DATE_MONTH));
		words.add(new Word("2015", Word.WordType.NUMBER));

		// PREPOSITION_FROM (NUMBER | NUMERICAL_POSITION) (DATE_MONTH NUMBER?)? PREPOSITION_TO NUMBER DATE_MONTH NUMBER
		assertEquals(true, WordPattern.syntaxStartsWith(words,
				WordPattern.sequence(
						WordPattern.simple(Word.WordType.PREPOSITION_FROM),
						WordPattern.or(WordPattern.simple(Word.WordType.NUMERICAL_POSITION), WordPattern.simple(Word.WordType.NUMBER)),
						WordPattern.optional(WordPattern.sequence(WordPattern.simple(WordType.DATE_MONTH), WordPattern.optional(WordPattern.simple(WordType.NUMBER)))),
						WordPattern.simple(Word.WordType.PREPOSITION_TO),
						WordPattern.or(WordPattern.simple(Word.WordType.NUMBER), WordPattern.simple(Word.WordType.NUMERICAL_POSITION)),
						WordPattern.simple(Word.WordType.DATE_MONTH),
						WordPattern.optional(WordPattern.simple(Word.WordType.NUMBER))
						))
				);
	}
	
	@Test
	public void testIsADateDataFromTo(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("du", Word.WordType.PREPOSITION_FROM));
		words.add(new Word("1", Word.WordType.NUMBER));
		words.add(new Word("janvier", Word.WordType.DATE_MONTH));
		words.add(new Word("2012", WordType.NUMBER));
		words.add(new Word("au", Word.WordType.PREPOSITION_TO));
		words.add(new Word("7", Word.WordType.NUMBER));
		words.add(new Word("mars", Word.WordType.DATE_MONTH));
		words.add(new Word("2015", Word.WordType.NUMBER));
		
		assertEquals(true, DateConverter.isADateData(words));
	}
	
	@Test
	public void testIsADateDataBetween(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("entre", Word.WordType.PREPOSITION_BETWEEN));
		words.add(new Word("le", Word.WordType.DEFINITE_ARTICLE));
		words.add(new Word("1", Word.WordType.NUMBER));
		words.add(new Word("janvier", Word.WordType.DATE_MONTH));
		words.add(new Word("2012", WordType.NUMBER));
		words.add(new Word("et", Word.WordType.PREPOSITION_AND));
		words.add(new Word("le", Word.WordType.DEFINITE_ARTICLE));
		words.add(new Word("7", Word.WordType.NUMBER));
		words.add(new Word("mars", Word.WordType.DATE_MONTH));
		words.add(new Word("2015", Word.WordType.NUMBER));
		
		assertEquals(true, DateConverter.isADateData(words));
	}
	
	@Test
	public void testIsADateDataFixed(){		
		WordBuffer words = new WordBuffer();
		words.add(new Word("le", Word.WordType.DEFINITE_ARTICLE));
		words.add(new Word("17", Word.WordType.NUMBER));
		words.add(new Word("janvier", Word.WordType.DATE_MONTH));
		words.add(new Word("2012", WordType.NUMBER));
		//words.add(new Word("et", Word.WordType.PREPOSITION_AND));
		words.add(new Word("�", Word.WordType.PREPOSITION_AT));
		words.add(new Word("7", Word.WordType.NUMBER));
		words.add(new Word("heure", Word.WordType.DATE_UNIT));
		words.add(new Word("15", Word.WordType.NUMBER));
		
		assertEquals(true, DateConverter.isADateData(words));
	}
}

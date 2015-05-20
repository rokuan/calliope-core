package com.rokuan.calliopecore.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;

public class PatternTest {

	@Test
	public void testSequence(){
		WordBuffer words = new WordBuffer();
		WordPattern sequence = WordPattern.sequence(WordPattern.simple(WordType.DEFINITE_ARTICLE), WordPattern.simple(WordType.COMMON_NAME));

		assertEquals(false, words.syntaxStartsWith(sequence));

		words.add(new Word("le", WordType.DEFINITE_ARTICLE));
		words.add(new Word("chocolat", WordType.COMMON_NAME));
		words.add(new Word("est", WordType.VERB));
		words.add(new Word("bon", WordType.ADJECTIVE));

		assertEquals(true, words.syntaxStartsWith(sequence));
	}

	@Test
	public void testOptional(){
		WordBuffer words = new WordBuffer();
		WordPattern optional = WordPattern.optional(WordPattern.simple(WordType.QUANTITY));

		assertFalse(words.syntaxStartsWith(optional));

		words.add(new Word("toutes", WordType.QUANTITY));
		words.add(new Word("les", WordType.DEFINITE_ARTICLE));
		words.add(new Word("oranges", WordType.COMMON_NAME));
		words.add(new Word("sont", WordType.VERB));
		words.add(new Word("oranges", WordType.ADJECTIVE));

		assertTrue(words.syntaxStartsWith(optional));
	}

	@Test
	public void testSimple(){
		WordBuffer words = new WordBuffer();
		WordPattern simple = WordPattern.simple(WordType.PROPER_NAME);

		assertEquals(false, words.syntaxStartsWith(simple));

		words.add(new Word("Calliope", WordType.PROPER_NAME));
		words.add(new Word("est", WordType.VERB));
		words.add(new Word("un", WordType.INDEFINITE_ARTICLE));
		words.add(new Word("programme", WordType.COMMON_NAME));

		assertEquals(true, words.syntaxStartsWith(simple));
	}

	@Test
	public void testList(){
		WordBuffer words = new WordBuffer();
		WordPattern list = WordPattern.nonEmptyList(
				WordPattern.sequence(WordPattern.simple(WordType.INDEFINITE_ARTICLE), WordPattern.simple(WordType.COMMON_NAME)));

		assertEquals(false, words.syntaxStartsWith(list));

		words.add(new Word("un", WordType.INDEFINITE_ARTICLE));
		words.add(new Word("oiseau", WordType.COMMON_NAME));
		words.add(new Word("un", WordType.INDEFINITE_ARTICLE));
		words.add(new Word("enfant", WordType.COMMON_NAME));
		words.add(new Word("une", WordType.INDEFINITE_ARTICLE));
		words.add(new Word("chèvre", WordType.COMMON_NAME));

		assertEquals(true, words.syntaxStartsWith(list));
	}

	@Test
	public void testSeparatedList(){
		WordBuffer words = new WordBuffer();
		WordPattern separatedList = WordPattern.separatedNonEmptyList(
				WordPattern.sequence(WordPattern.simple(WordType.DEFINITE_ARTICLE), WordPattern.simple(WordType.COMMON_NAME)),
				WordPattern.optional(WordPattern.simple(WordType.PREPOSITION_AND))
				);

		assertEquals(false, words.syntaxStartsWith(separatedList));

		words.add(new Word("la", WordType.DEFINITE_ARTICLE));
		words.add(new Word("farine", WordType.COMMON_NAME));
		words.add(new Word("les", WordType.DEFINITE_ARTICLE));
		words.add(new Word("oeufs", WordType.COMMON_NAME));
		words.add(new Word("et", WordType.PREPOSITION_AND));
		words.add(new Word("le", WordType.DEFINITE_ARTICLE));
		words.add(new Word("sucre", WordType.COMMON_NAME));

		assertEquals(true, words.syntaxStartsWith(separatedList));
	}
}

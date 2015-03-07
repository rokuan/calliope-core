package com.rokuan.calliopecore.test;

import org.junit.Test;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;

public class InterpretationParseTest {
	
	@Test
	public void testOrder(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("affiche", WordType.VERB));
		words.add(new Word("moi", WordType.PERSONAL_PRONOUN));
	}
	
	@Test
	public void testTemperatureRequest(){
		WordBuffer words = new WordBuffer();
		words.add(new Word("quelle", WordType.INTERROGATIVE_PRONOUN));
		words.add(new Word("est", WordType.AUXILIARY, WordType.VERB));
		words.add(new Word("la", WordType.DEFINITE_ARTICLE));
		words.add(new Word("température", WordType.COMMON_NAME));
		words.add(new Word("à", WordType.PREPOSITION_AT));
		words.add(new Word("Paris", WordType.PROPER_NAME));
		words.add(new Word("en", WordType.PREPOSITION_IN));
		words.add(new Word("France", WordType.PROPER_NAME));
	}
}

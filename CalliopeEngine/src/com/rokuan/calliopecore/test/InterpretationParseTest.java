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
	
}

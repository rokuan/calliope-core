package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.VerbalGroup;

public class PurposeConverter {
	public static final WordPattern VERBAL_PURPOSE_PATTERN = WordPattern.sequence(
			WordPattern.simple(WordType.PURPOSE_PREPOSITION),
			WordPattern.optional(WordPattern.simple(WordType.TARGET_PRONOUN)),
			WordPattern.simple(WordType.VERB)
			);
	
	public static boolean isAPurposeAdverbial(WordBuffer words){
		return words.syntaxStartsWith(VERBAL_PURPOSE_PATTERN);
	}
	
	public static IPurposeObject parsePurposeAdverbial(WordBuffer words){
		IPurposeObject result = null;
		
		if(words.syntaxStartsWith(VERBAL_PURPOSE_PATTERN)){
			VerbalGroup verbal = new VerbalGroup();
			
			verbal.setPurposePreposition(words.getCurrentElement().getPurposePreposition());
			words.consume();
			
			result = verbal;
		}
		
		return result;
	}
}

package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial;
import com.rokuan.calliopecore.sentence.structure.data.purpose.VerbalPurposeObject;

public class PurposeConverter {	
	/*public static final WordPattern conjugatedPurposePattern = WordPattern.sequence(
			WordPattern.simple(WordType.ANY, "afin"),
			WordPattern.optional(WordPattern.simple(WordType.ANY, "que")),
			WordPattern.optional(WordPattern.simple(WordType.TARGET_PRONOUN)),
			WordPattern.simple(WordType.VERB)
			);*/
	public static final WordPattern VERBAL_PURPOSE_PATTERN = WordPattern.sequence(
			WordPattern.simple(WordType.PURPOSE_PREPOSITION),
			WordPattern.optional(WordPattern.simple(WordType.TARGET_PRONOUN)),
			WordPattern.simple(WordType.VERB)
			);
	
	public static boolean isAPurposeAdverbial(WordBuffer words){
		return words.syntaxStartsWith(VERBAL_PURPOSE_PATTERN);
	}
	
	public static PurposeAdverbial parsePurposeAdverbial(WordBuffer words){
		PurposeAdverbial result = null;
		
		if(words.syntaxStartsWith(VERBAL_PURPOSE_PATTERN)){
			VerbalPurposeObject verbal = new VerbalPurposeObject();
			
			verbal.purposePreposition = words.getCurrentElement().getPurposePreposition();
			words.consume();
			
			
			result = verbal;
		}
		
		return result;
	}
}

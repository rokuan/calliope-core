package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.VerbalGroup;

public class PurposeConverter {
	// TODO: mettre le bon pattern pour le verb + rajouter le pattern pour les sujets
	public static final WordPattern VERBAL_PURPOSE_PATTERN = WordPattern.sequence(
			WordPattern.simpleWord(WordType.PURPOSE_PREPOSITION),
			WordPattern.optional(WordPattern.simpleWord(WordType.TARGET_PRONOUN)),
			WordPattern.simpleWord(WordType.VERB)
			);
	
	public static boolean isAPurposeAdverbial(WordBuffer words){
		return words.syntaxStartsWith(VERBAL_PURPOSE_PATTERN);
	}
	
	public static IPurposeObject parsePurposeAdverbial(WordBuffer words){
		IPurposeObject result = null;
		
		if(words.syntaxStartsWith(VERBAL_PURPOSE_PATTERN)){
			VerbalGroup verbal = new VerbalGroup();
			
			verbal.setPurposePreposition(words.getCurrentElement().getPurposePreposition().getValue());
			words.consume();
			
			result = verbal;
		}
		
		return result;
	}
}

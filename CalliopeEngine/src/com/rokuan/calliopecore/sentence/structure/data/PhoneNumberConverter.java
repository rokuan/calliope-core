package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word;
import com.rokuan.calliopecore.sentence.Word.WordType;

/**
 * Created by LEBEAU Christophe on 02/03/2015.
 */
public class PhoneNumberConverter {
	// TOCHECK: des numeros de telephone avec 1 nombre ? (911)
	public static final WordPattern PHONE_NUMBER_PATTERN = WordPattern.sequence(
			WordPattern.or(WordPattern.simple(WordType.ANY, "au"), WordPattern.simple(WordType.DEFINITE_ARTICLE, "le")),
			WordPattern.nonEmptyList(WordPattern.simple(WordType.NUMBER)));

	public static boolean isAPhoneNumber(WordBuffer words){
		//return words.syntaxStartsWith(Word.WordType.NUMBER, Word.WordType.NUMBER);
		return words.syntaxStartsWith(PHONE_NUMBER_PATTERN);
	}

	public static String parsePhoneNumber(WordBuffer words){
		StringBuilder phoneNumber = new StringBuilder();

		if(words.syntaxStartsWith(PHONE_NUMBER_PATTERN)){
			if(words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE) || words.getCurrentElement().isOfType(WordType.PREPOSITION_AT)){
				words.consume();
			}
			
			while(words.isIntoBounds() && words.getCurrentElement().isOfType(Word.WordType.NUMBER)){
				phoneNumber.append(words.getCurrentElement().getValue());
				words.consume();
			}
		}

		return phoneNumber.toString();
	}
}

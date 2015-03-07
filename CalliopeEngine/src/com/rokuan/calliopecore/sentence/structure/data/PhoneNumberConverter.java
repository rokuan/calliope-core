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
	public static final WordPattern phoneNumberPattern = WordPattern.sequence(
			WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE)),
			WordPattern.simple(WordType.NUMBER),
			WordPattern.simple(WordType.NUMBER));

	public static boolean isAPhoneNumber(WordBuffer words){
		//return words.syntaxStartsWith(Word.WordType.NUMBER, Word.WordType.NUMBER);
		return words.syntaxStartsWith(phoneNumberPattern);
	}

	public static String parsePhoneNumber(WordBuffer words){
		StringBuilder phoneNumber = new StringBuilder();

		if(words.syntaxStartsWith(phoneNumberPattern)){
			if(words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE)){
				words.next();
			}
			
			while(words.getCurrentElement().isOfType(Word.WordType.NUMBER)){
				phoneNumber.append(words.getCurrentElement().getValue());
				words.next();
			}
		}

		return phoneNumber.toString();
	}
}

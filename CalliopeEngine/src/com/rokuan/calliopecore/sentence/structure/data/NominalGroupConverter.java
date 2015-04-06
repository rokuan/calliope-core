package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Type;
import com.rokuan.calliopecore.sentence.Type.Pronoun;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.NominalGroup;
import com.rokuan.calliopecore.sentence.structure.PronounTarget;

public class NominalGroupConverter {
	// NominalGroup
	public static final WordPattern pronounPattern = WordPattern.simple(WordType.PERSONAL_PRONOUN);
	public static final WordPattern abstractTargetPattern = WordPattern.simple(WordType.POSSESSIVE_ADJECTIVE); 
	public static final WordPattern objectPattern = WordPattern.sequence(
			
			WordPattern.simple(WordType.POSSESSIVE_PRONOUN),
			WordPattern.or(WordPattern.simple(WordType.NUMBER))
			);
	public static final WordPattern placePattern = PlaceConverter.placePattern;		
	
	// ComplementObject
	public static final WordPattern directObjectPattern = WordPattern.sequence(
			WordPattern.optional(NumberConverter.countPattern),
			WordPattern.simple(WordType.COMMON_NAME));
	
	public static boolean isANominalGroup(WordBuffer words){
		return words.syntaxStartsWith(pronounPattern);
				/*|| words.syntaxStartsWith(abstractTargetPattern)
				|| words.syntaxStartsWith(objectPattern);*/
	}
	
	public static NominalGroup parseNominalGroup(WordBuffer words){
		if(words.syntaxStartsWith(pronounPattern)){
			Pronoun pronoun = Type.parseSubjectPronoun(words.getCurrentElement().getValue());
			words.consume();
			return new PronounTarget(pronoun);
		} else if(words.syntaxStartsWith(objectPattern)){
			
		}
		
		return null;
	}
	
	public static boolean isADirectObject(WordBuffer words){
		return words.syntaxStartsWith(directObjectPattern);
	}
	
	public static ComplementObject parseDirectObject(WordBuffer words){
		ComplementObject obj = new ComplementObject();
		
		if(words.syntaxStartsWith(directObjectPattern)){
			if(NumberConverter.isACountData(words)){
				obj.count = NumberConverter.parseCountObject(words);
			}
			
			obj.object = words.getCurrentElement().getValue();
			words.consume();
		}
		
		return obj;
	}
}

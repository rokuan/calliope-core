package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;

public class PlaceConverter {
	public static final WordPattern countryPattern = WordPattern.sequence(WordPattern.or(
			// TODO:
			), WordPattern.simple(WordType.PROPER_NAME));
	
	public static final WordPattern cityPattern = WordPattern.sequence(
					WordPattern.simple(WordType.PREPOSITION_AT), 
					WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE)), 
					WordPattern.simple(WordType.PROPER_NAME));
	
	public static final WordPattern worldPlacePattern = WordPattern.sequence(WordPattern.optional(cityPattern), countryPattern);
					
			// A Paris en France
			// A Mexico au Mexique
			// A ... a la ...
}

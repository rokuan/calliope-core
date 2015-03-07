package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.StateObject;

public class PlaceConverter {
	public static final WordPattern placePattern = WordPattern.sequence(
			//WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE)),
			WordPattern.simple(WordType.DEFINITE_ARTICLE),
			WordPattern.optional(WordPattern.simple(WordType.COMMON_NAME)),
			WordPattern.simple(WordType.PROPER_NAME),
			WordPattern.optional(WordPattern.simple(WordType.PROPER_NAME)),
			WordPattern.optional(WordPattern.sequence(WordPattern.simple(WordType.PREPOSITION_OF), WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE))))
			);
	
	//private static final WordPattern locationPrepositionPattern = WordPattern.sequence(WordPattern.)
	
	public static final WordPattern countryPattern = WordPattern.sequence(WordPattern.or(
			WordPattern.sequence(WordPattern.simple(WordType.PREPOSITION_AT), WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE))),
			WordPattern.sequence(WordPattern.simple(WordType.PREPOSITION_IN))
			), WordPattern.simple(WordType.PROPER_NAME));
	
	public static final WordPattern cityPattern = WordPattern.sequence(
					WordPattern.simple(WordType.PREPOSITION_AT), 
					//WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE)), 
					WordPattern.simple(WordType.PROPER_NAME));
	
	public static final WordPattern worldPlacePattern = WordPattern.sequence(WordPattern.optional(cityPattern), countryPattern);
					
			// A Paris en France
			// A Mexico au Mexique
			// A ... a la ...
	
	public static boolean isAPlaceData(WordBuffer words){
		return words.syntaxStartsWith(placePattern)
				|| words.syntaxStartsWith(worldPlacePattern);
	}
	
	public static PlaceObject parsePlaceObject(WordBuffer words){
		PlaceObject result = null;
		
		if(words.syntaxStartsWith(placePattern)){
			
		} else if(words.syntaxStartsWith(worldPlacePattern)){
			StateObject state = new StateObject();
			
			if(words.syntaxStartsWith(cityPattern, countryPattern)){
				words.consume();
				// TODO: parser les noms de ville en plusieurs mots (while)
				state.city = words.getCurrentElement().getValue();
				words.consume();
			}
			
			words.next();
			
			while(!words.getCurrentElement().isOfType(WordType.PROPER_NAME)){
				words.consume();
			}
			
			StringBuilder countryName = null;
			
			do{
				if(countryName == null){
					countryName = new StringBuilder();
				} else {
					countryName.append(' ');
				}

				countryName.append(words.getCurrentElement().getValue());
				words.consume();
			}while(words.hasNext() && words.getCurrentElement().isOfType(WordType.PROPER_NAME));
			
			state.country = countryName.toString();
			
			result = state;
		}
		
		return result;
	}
}

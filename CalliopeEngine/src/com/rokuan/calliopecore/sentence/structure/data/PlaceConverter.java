package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.place.MonumentObject;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.StateObject;

public class PlaceConverter {
	/*public static final WordPattern placePattern = WordPattern.sequence(
			//WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE)),
			WordPattern.simple(WordType.DEFINITE_ARTICLE),
			WordPattern.optional(WordPattern.simple(WordType.COMMON_NAME)),
			WordPattern.simple(WordType.PROPER_NAME),
			WordPattern.optional(WordPattern.simple(WordType.PROPER_NAME)),
			WordPattern.optional(WordPattern.sequence(WordPattern.simple(WordType.PREPOSITION_OF), WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE))))
			);*/
	// le musée du Louvre, la Grande Muraille de Chine 
	public static final WordPattern placePattern = WordPattern.sequence(
			WordPattern.or(
					WordPattern.sequence(WordPattern.simple(WordType.PREPOSITION_AT, "à"), WordPattern.simple(WordType.DEFINITE_ARTICLE, "la")),
					WordPattern.simple(WordType.PREPOSITION_AT, "au*")),
					//WordPattern.simple(WordType.DEFINITE_ARTICLE),
					WordPattern.optional(WordPattern.simple(WordType.PLACE_TYPE)),
					WordPattern.optional(WordPattern.nonEmptyList(WordPattern.simple(WordType.PROPER_NAME))),
					WordPattern.optional(WordPattern.sequence(WordPattern.simple(WordType.PREPOSITION_OF), 
							WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE)),
							WordPattern.optional(WordPattern.or(
									WordPattern.simple(WordType.COUNTRY),
									WordPattern.simple(WordType.CITY),
									WordPattern.nonEmptyList(WordPattern.simple(WordType.PROPER_NAME))
									))
							)));

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

		// TODO: gerer les locations pleines (Le musee du Louvre a Paris en France)
		if(words.syntaxStartsWith(placePattern)){
			MonumentObject monument = new MonumentObject();
			StringBuilder buffer = new StringBuilder();

			if(words.getCurrentElement().isOfType(WordType.PREPOSITION_AT)){
				words.consume();
			}

			if(words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE)){
				words.consume();
			}

			if(words.getCurrentElement().isOfType(WordType.PLACE_TYPE)){
				buffer.append(words.getCurrentElement().getValue());
				buffer.append(' ');
				words.consume();
			}

			if(words.getCurrentElement().isOfType(WordType.PROPER_NAME)){
				do {
					buffer.append(words.getCurrentElement().getValue());
					buffer.append(' ');
					words.consume();
				} while(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.PROPER_NAME));
			}

			if(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.PREPOSITION_OF)){
				words.consume();	// PREPOSITION_OF				
				
				if(words.getCurrentElement().isOfType(WordType.CITY)){ 
					monument.city = words.getCurrentElement().getValue();
					words.consume();
				} else if(words.getCurrentElement().isOfType(WordType.COUNTRY)){
					monument.city = words.getCurrentElement().getValue();
					words.consume();
				} else {
					buffer.append("de ");

					do {
						buffer.append(words.getCurrentElement().getValue());
						buffer.append(' ');
						words.consume();
					} while(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.PROPER_NAME));
				}
			}

			monument.name = buffer.toString().trim();

			result = monument;
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

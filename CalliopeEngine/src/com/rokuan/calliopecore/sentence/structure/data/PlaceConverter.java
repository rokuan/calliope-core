package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.AdditionalPlaceObject;
import com.rokuan.calliopecore.sentence.structure.data.place.LocationObject;
import com.rokuan.calliopecore.sentence.structure.data.place.NamedPlaceObject;

public class PlaceConverter {
	// le mus�e du Louvre, la Grande Muraille de Chine 
	public static final WordPattern PLACE_PATTERN = WordPattern.sequence(
			WordPattern.or(
					WordPattern.sequence(WordPattern.simple(WordType.PREPOSITION_AT, "�"), WordPattern.simple(WordType.DEFINITE_ARTICLE, "la|l")),
					WordPattern.simple(WordType.PREPOSITION_AT, "au(x?)")),
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

	public static final WordPattern COUNTRY_PATTERN = WordPattern.sequence(WordPattern.or(
			WordPattern.sequence(WordPattern.simple(WordType.PREPOSITION_AT), WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE))),
			WordPattern.sequence(WordPattern.simple(WordType.ANY, "en"))
			), WordPattern.simple(WordType.COUNTRY));

	public static final WordPattern CITY_PATTERN = WordPattern.sequence(
			WordPattern.simple(WordType.PREPOSITION_AT), 
			//WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE)), 
			WordPattern.simple(WordType.CITY));

	public static final WordPattern WORLD_PLACE_PATTERN = WordPattern.or(
			WordPattern.sequence(CITY_PATTERN, COUNTRY_PATTERN),
			/*WordPattern.sequence(WordPattern.optional(CITY_PATTERN), COUNTRY_PATTERN),
			WordPattern.sequence(CITY_PATTERN, WordPattern.optional(COUNTRY_PATTERN))*/
			CITY_PATTERN,
			COUNTRY_PATTERN
			);
	
	// devant l'Opera de Wasawakini
	// jusqu'aux Chutes Fanis
	public static final WordPattern ADDITIONAL_PLACE_PATTERN = WordPattern.sequence(
			WordPattern.or(
					WordPattern.sequence(WordPattern.simple(WordType.PLACE_PREPOSITION), WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE))),
					WordPattern.simple(new WordType[]{ WordType.PLACE_PREPOSITION, WordType.CONTRACTED })
					),
					WordPattern.simple(WordType.ADDITIONAL_PLACE)
			);

	// A Paris en France
	// A Mexico au Mexique
	// A ... a la ...

	public static boolean isAPlaceAdverbial(WordBuffer words){
		return words.syntaxStartsWith(ADDITIONAL_PLACE_PATTERN)
				|| words.syntaxStartsWith(WORLD_PLACE_PATTERN)
				|| words.syntaxStartsWith(PLACE_PATTERN);
	}

	public static IPlaceObject parsePlaceAdverbial(WordBuffer words){
		IPlaceObject result = null;

		// TODO: gerer les locations pleines (Le musee du Louvre a Paris en France)
		if(words.syntaxStartsWith(ADDITIONAL_PLACE_PATTERN)){
			AdditionalPlaceObject custom = new AdditionalPlaceObject();
			
			if(words.getCurrentElement().isOfType(WordType.PLACE_PREPOSITION)){
				custom.location = words.getCurrentElement().getPlacePreposition();
				words.consume();
			}
			
			if(words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE)){
				words.consume();
			}
			
			custom.place = words.getCurrentElement().getCustomPlace();
			words.consume();
			result = custom;
		} else if(words.syntaxStartsWith(WORLD_PLACE_PATTERN)){
			LocationObject state = new LocationObject();
			
			if(words.syntaxStartsWith(CITY_PATTERN)){
				while(!words.getCurrentElement().isOfType(WordType.CITY)){
					words.consume();					
				}
				
				state.city = words.getCurrentElement().getCityInfo();
				words.consume();
			}
			
			if(words.syntaxStartsWith(COUNTRY_PATTERN)){
				while(!words.getCurrentElement().isOfType(WordType.COUNTRY)){
					words.consume();
				}
				
				state.country = words.getCurrentElement().getCountryInfo();
				words.consume();
			}

			result = state;
		} else if(words.syntaxStartsWith(PLACE_PATTERN)){
			NamedPlaceObject monument = new NamedPlaceObject();
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
		}

		return result;
	}
}

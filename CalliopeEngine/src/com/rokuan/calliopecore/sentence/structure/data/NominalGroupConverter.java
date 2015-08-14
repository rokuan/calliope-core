package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Type;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.AdditionalObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.AdditionalPerson;
import com.rokuan.calliopecore.sentence.structure.data.nominal.ColorObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NominalGroup;
import com.rokuan.calliopecore.sentence.structure.data.nominal.PronounTarget;

public class NominalGroupConverter {
	// NominalGroup
	public static final WordPattern ABSTRACT_TARGET_PATTERN = WordPattern.simple(WordType.POSSESSIVE_ADJECTIVE); 
	/*public static final WordPattern OBJECT_PATTERN = WordPattern.sequence(			
			WordPattern.simple(WordType.POSSESSIVE_PRONOUN),
			WordPattern.or(WordPattern.simple(WordType.NUMBER))
			);*/
	public static final WordPattern PLACE_PATTERN = PlaceConverter.PLACE_PATTERN;		
	
	// ComplementObject
	public static final WordPattern DIRECT_OBJECT_PATTERN = WordPattern.sequence(
			WordPattern.optional(CountConverter.COUNT_PATTERN),
			WordPattern.simple(WordType.COMMON_NAME),
			WordPattern.optional(CountConverter.MULTIPLE_ITEMS_PATTERN),
			WordPattern.optional(CriterionConverter.CRITERIA_PATTERN));	
	
	public static final WordPattern CUSTOM_OBJECT_PATTERN = WordPattern.sequence(
			WordPattern.optional(CountConverter.COUNT_PATTERN),
			WordPattern.simple(WordType.OBJECT));
	public static final WordPattern CUSTOM_PERSON_PATTERN = WordPattern.simple(WordType.PERSON);

	public static final WordPattern NAME_PATTERN = WordPattern.or(
			WordPattern.simple(WordType.OBJECT),
			WordPattern.simple(WordType.COMMON_NAME)
			);
	
	public static final WordPattern TO_PATTERN = WordPattern.or(
			WordPattern.sequence(WordPattern.simple(WordType.PREPOSITION_AT, "à"), WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE, "la"))),
			WordPattern.simple(WordType.PREPOSITION_AT, "au.*")
			);
	
	public static final WordPattern FIRST_NAME_SEQUENCE_PATTERN = WordPattern.nonEmptyList(WordPattern.simple(WordType.FIRSTNAME));
	public static final WordPattern LAST_NAME_SEQUENCE_PATTERN = WordPattern.nonEmptyList(WordPattern.simple(WordType.PROPER_NAME));
	
	public static final WordPattern PERSON_PATTERN = WordPattern.or(
			WordPattern.simple(WordType.PERSON),
			LAST_NAME_SEQUENCE_PATTERN,
			FIRST_NAME_SEQUENCE_PATTERN,
			WordPattern.sequence(FIRST_NAME_SEQUENCE_PATTERN, LAST_NAME_SEQUENCE_PATTERN),
			WordPattern.sequence(LAST_NAME_SEQUENCE_PATTERN, FIRST_NAME_SEQUENCE_PATTERN)
			);
	
	public static final WordPattern INDIRECT_OBJECT_PATTERN = WordPattern.sequence(TO_PATTERN,
			WordPattern.or(PERSON_PATTERN)	// TODO: ajouter le cas groupe nominal
					);
	
	//private static final WordPattern CHARACTER_PATTERN = WordPattern.simple(WordType.PERSON);
	private static final WordPattern PRONOUN_PATTERN = WordPattern.simple(WordType.PERSONAL_PRONOUN);
	private static final WordPattern COLOR_PATTERN = WordPattern.sequence(WordPattern.simple(WordType.DEFINITE_ARTICLE), WordPattern.simple(WordType.COLOR));
	// TODO: ajouter les adjectifs
	private static final WordPattern OBJECT_PATTERN = WordPattern.sequence(CountConverter.COUNT_PATTERN, WordPattern.simple(WordType.OBJECT));
	private static final WordPattern COMMON_NAME_PATTERN = WordPattern.sequence(CountConverter.COUNT_PATTERN, WordPattern.simple(WordType.COMMON_NAME));
	//private static final WordPattern LANGUAGE_PATTERN = WordPattern.sequence(WordPattern.simple(WordType.DEFINITE_ARTICLE), WordPattern.simple(WordType.LANGUAGE));

	//private static final WordPattern CUSTOM_PLACE_PATTERN = WordPattern.sequence(WordPattern.simple(WordType.DEFINITE_ARTICLE), WordPattern.simple(WordType.ADDITIONAL_PLACE));
	
	
	public static final WordPattern SUBJECT_PATTERN = WordPattern.or(
			OBJECT_PATTERN,
			PlaceConverter.CITY_ONLY_PATTERN,
			PlaceConverter.COUNTRY_ONLY_PATTERN,
			PERSON_PATTERN,
			PRONOUN_PATTERN,
			DateConverter.FIXED_DATE_ONLY_PATTERN,
			COLOR_PATTERN,
			WayConverter.LANGUAGE_ONLY_PATTERN,
			COMMON_NAME_PATTERN,
			PlaceConverter.ADDITIONAL_PLACE_ONLY_PATTERN,
			PlaceConverter.PLACE_ONLY_PATTERN
			// TODO: ajouter le pattern pour les groupes verbaux
			); 
			
	
	public static boolean isASubject(WordBuffer words){
		return words.syntaxStartsWith(SUBJECT_PATTERN);
	}
	
	public static INominalObject parseSubject(WordBuffer words){
		INominalObject result = null;
		
		if(words.syntaxStartsWith(OBJECT_PATTERN)){
			// TODO:
		} else if(words.syntaxStartsWith(PlaceConverter.CITY_ONLY_PATTERN)){
			result = PlaceConverter.parseNominalPlaceObject(words);
		} else if(words.syntaxStartsWith(PlaceConverter.COUNTRY_ONLY_PATTERN)){
			result = PlaceConverter.parseNominalPlaceObject(words);
		} else if(words.syntaxStartsWith(PERSON_PATTERN)){
			// TODO:
		} else if(words.syntaxStartsWith(PRONOUN_PATTERN)){
			PronounTarget pronoun = new PronounTarget(Type.parseSubjectPronoun(words.getCurrentElement().getValue()));
			words.consume();
			result = pronoun;
		} else if(words.syntaxStartsWith(DateConverter.FIXED_DATE_ONLY_PATTERN)){
			result = DateConverter.parseNominalDateObject(words);
		} else if(words.syntaxStartsWith(COLOR_PATTERN)){
			ColorObject color = new ColorObject();
			
			color.color = words.getCurrentElement().getColorInfo();
			words.consume();
			
			result = color;
		} else if(words.syntaxStartsWith(WayConverter.LANGUAGE_ONLY_PATTERN)){
			result = WayConverter.parseNominalWayObject(words);
		} else if(words.syntaxStartsWith(COMMON_NAME_PATTERN)){
			// TODO:
		} else if(words.syntaxStartsWith(PlaceConverter.WORLD_LOCATION_PATTERN)){
			result = PlaceConverter.parseNominalPlaceObject(words);
		} else if(words.syntaxStartsWith(PlaceConverter.PLACE_ONLY_PATTERN)){
			result = PlaceConverter.parseNominalPlaceObject(words);
		}
		
		return result;
	}
	
	public static boolean isADirectObject(WordBuffer words){
		return words.syntaxStartsWith(CUSTOM_OBJECT_PATTERN)
				|| words.syntaxStartsWith(DIRECT_OBJECT_PATTERN) 
				|| words.syntaxStartsWith(PERSON_PATTERN);
	}
	
	public static INominalObject parseDirectObject(WordBuffer words){
		INominalObject result = null;
		
		if(words.syntaxStartsWith(CUSTOM_OBJECT_PATTERN)){
			AdditionalObject custom = new AdditionalObject();
			
			if(CountConverter.isACountData(words)){
				custom.count = CountConverter.parseCountObject(words);
			}
			
			custom.object = words.getCurrentElement().getCustomObject();
			words.consume();
			
			result = custom;
		} else if(words.syntaxStartsWith(CUSTOM_PERSON_PATTERN)){
			AdditionalPerson custom = new AdditionalPerson();			
			custom.person = words.getCurrentElement().getCustomPerson();
			result = custom;
		} else if(words.syntaxStartsWith(DIRECT_OBJECT_PATTERN)){
			ComplementObject obj = new ComplementObject();
			
			if(CountConverter.isACountData(words)){
				obj.count = CountConverter.parseCountObject(words);
			}
			
			// TODO: parser les adjectifs et autres
			obj.object = words.getCurrentElement().getValue();
			words.consume();
			
			if(CountConverter.isASuffixCountData(words)){
				obj.count = CountConverter.parseSuffixCountObject(words);
			}
			
			if(CriterionConverter.isACriterionData(words)){
				obj.criteria = CriterionConverter.parseCriterionData(words);
			} else if(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.PREPOSITION_OF)){
				words.next();
				
				if(NominalGroupConverter.isADirectObject(words)){
					words.previous();
					words.consume();
					
					try{
						obj.of = (ComplementObject)NominalGroupConverter.parseDirectObject(words);
					}catch(Exception e){
						System.out.println(e);
					}
				} else {
					words.previous();
				}
			}
			
			result = obj;
		} else if(words.syntaxStartsWith(PERSON_PATTERN)){
			ComplementObject obj = new ComplementObject();			
			obj.object = parsePerson(words);
			result = obj;
		}
		
		return result;
	}
	
	public static boolean isAnIndirectObject(WordBuffer words){
		return words.syntaxStartsWith(INDIRECT_OBJECT_PATTERN);
	}
	
	public static NominalGroup parseIndirectObject(WordBuffer words){
		ComplementObject obj = new ComplementObject();
		
		if(words.syntaxStartsWith(INDIRECT_OBJECT_PATTERN)){
			//StringBuilder name = new StringBuilder();
			
			words.consume();	// PREPOSITION_AT
			
			if(words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE)){
				words.consume();
			}
			
			//obj.object = name.toString().trim();
			obj.object = parsePerson(words);
		}
		
		return obj;
	}
	
	private static String parsePerson(WordBuffer words){
		StringBuilder name = new StringBuilder();
		
		if(words.getCurrentElement().isOfType(WordType.FIRSTNAME)){
			while(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.FIRSTNAME)){
				name.append(words.getCurrentElement().getValue());
				name.append(' ');
				words.consume();
			}
			
			while(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.PROPER_NAME)){
				name.append(words.getCurrentElement().getValue());
				name.append(' ');
				words.consume();
			}
		} else if(words.getCurrentElement().isOfType(WordType.PROPER_NAME)){
			while(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.PROPER_NAME)){
				name.append(words.getCurrentElement().getValue());
				name.append(' ');
				words.consume();
			}
			
			while(words.isIntoBounds() && words.getCurrentElement().isOfType(WordType.FIRSTNAME)){
				name.append(words.getCurrentElement().getValue());
				name.append(' ');
				words.consume();
			}
		}
		
		return name.toString().trim();
	}
}

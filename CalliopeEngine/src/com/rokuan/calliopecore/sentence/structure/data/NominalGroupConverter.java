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
import com.rokuan.calliopecore.sentence.structure.data.nominal.PhoneNumberObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.PronounTarget;

public class NominalGroupConverter {
	// NominalGroup
	public static final WordPattern ABSTRACT_TARGET_PATTERN = WordPattern.simpleWord(WordType.POSSESSIVE_ADJECTIVE);
	public static final WordPattern PLACE_PATTERN = PlaceConverter.PLACE_PATTERN;		

	// ComplementObject
	public static final WordPattern DIRECT_OBJECT_PATTERN = WordPattern.sequence(
			WordPattern.optional(CountConverter.COUNT_PATTERN),
			WordPattern.simpleWord(WordType.COMMON_NAME),
			WordPattern.optional(CountConverter.MULTIPLE_ITEMS_PATTERN),
			WordPattern.optional(CriterionConverter.CRITERIA_PATTERN));	

	public static final WordPattern CUSTOM_OBJECT_PATTERN = WordPattern.sequence(
			WordPattern.optional(CountConverter.COUNT_PATTERN),
			WordPattern.simpleWord(WordType.OBJECT));
	public static final WordPattern CUSTOM_PERSON_PATTERN = WordPattern.simpleWord(WordType.PERSON);

	public static final WordPattern NAME_PATTERN = WordPattern.or(
			WordPattern.simpleWord(WordType.OBJECT),
			WordPattern.simpleWord(WordType.COMMON_NAME)
			);

	public static final WordPattern FIRST_NAME_SEQUENCE_PATTERN = WordPattern.nonEmptyList(WordPattern.simpleWord(WordType.FIRSTNAME));
	public static final WordPattern LAST_NAME_SEQUENCE_PATTERN = WordPattern.nonEmptyList(WordPattern.simpleWord(WordType.PROPER_NAME));

	/*public static final WordPattern PERSON_PATTERN = WordPattern.or(
			WordPattern.simpleWord(WordType.PERSON)
			// TODO: trouver un moyen de parser les noms non enregistres (ex: Ludwig Van Beethoven)
			//, LAST_NAME_SEQUENCE_PATTERN,
			//FIRST_NAME_SEQUENCE_PATTERN,
			//WordPattern.sequence(FIRST_NAME_SEQUENCE_PATTERN, LAST_NAME_SEQUENCE_PATTERN),
			//WordPattern.sequence(LAST_NAME_SEQUENCE_PATTERN, FIRST_NAME_SEQUENCE_PATTERN)
			);*/
	public static final WordPattern PERSON_PATTERN = WordPattern.simpleWord(WordType.PERSON);
	

	private static final WordPattern PRONOUN_PATTERN = WordPattern.simpleWord(WordType.PERSONAL_PRONOUN);
	private static final WordPattern COLOR_PATTERN = WordPattern.sequence(WordPattern.simpleWord(WordType.DEFINITE_ARTICLE), WordPattern.simpleWord(WordType.COLOR));
	// TODO: ajouter les adjectifs
	private static final WordPattern OBJECT_PATTERN = WordPattern.sequence(CountConverter.COUNT_PATTERN, WordPattern.simpleWord(WordType.OBJECT));
	private static final WordPattern COMMON_NAME_PATTERN = WordPattern.sequence(CountConverter.COUNT_PATTERN, WordPattern.simpleWord(WordType.COMMON_NAME));

	// TODO: 
	private static final WordPattern PHONE_NUMBER_PATTERN = WordPattern.sequence(
			WordPattern.simpleWord(WordType.DEFINITE_ARTICLE),
			WordPattern.simpleWord(WordType.NUMBER),
			WordPattern.simpleWord(WordType.NUMBER),
			WordPattern.simpleWord(WordType.NUMBER),
			WordPattern.simpleWord(WordType.NUMBER),
			WordPattern.simpleWord(WordType.NUMBER)); 
	
	private static final WordPattern PERSON_SECOND_OBJECT_PATTERN = WordPattern.sequence(
			WordPattern.simpleWord(WordType.PREPOSITION_OF),
			PERSON_PATTERN);			
	private static final WordPattern COMPLEMENT_SECOND_OBJECT_PATTERN = WordPattern.sequence(
			WordPattern.simpleWord(WordType.PREPOSITION_OF),
			WordPattern.optional(CountConverter.COUNT_PATTERN),
			WordPattern.simpleWord(WordType.COMMON_NAME));
	private static final WordPattern CUSTOM_OBJECT_SECOND_OBJECT_PATTERN = WordPattern.sequence(
			WordPattern.simpleWord(WordType.PREPOSITION_OF),
			CUSTOM_OBJECT_PATTERN);
	
	private static final WordPattern NOMINAL_SECOND_OBJECT_PATTERN = WordPattern.or(
			COMPLEMENT_SECOND_OBJECT_PATTERN,
			CUSTOM_OBJECT_SECOND_OBJECT_PATTERN,
			PERSON_SECOND_OBJECT_PATTERN);
	
	public static final WordPattern SUBJECT_PATTERN = WordPattern.or(
			OBJECT_PATTERN,
			PHONE_NUMBER_PATTERN,
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
	
	public static final WordPattern TO_PATTERN = WordPattern.or(
			WordPattern.sequence(WordPattern.simpleWord(WordType.PREPOSITION_AT, "à"), WordPattern.optional(WordPattern.simpleWord(WordType.DEFINITE_ARTICLE, "la"))),
			WordPattern.simpleWord(WordType.PREPOSITION_AT, "au.*")
			);

	private static final WordPattern INDIRECT_PERSON_PATTERN = WordPattern.sequence(
			WordPattern.simpleWord("à"),
			WordPattern.simpleWord(WordType.PERSON));
	private static final WordPattern INDIRECT_PERSON_TYPE_PATTERN = WordPattern.sequence(
			WordPattern.or(WordPattern.simpleWord("à"), WordPattern.simpleWord(WordType.DEFINITE_ARTICLE, "la"),
					WordPattern.simpleWord("au")),
			WordPattern.simpleWord(WordType.PERSON_TYPE));
	public static final WordPattern INDIRECT_OBJECT_PATTERN = WordPattern.or(
			INDIRECT_PERSON_PATTERN,
			INDIRECT_PERSON_TYPE_PATTERN);

	public static boolean isASubject(WordBuffer words){
		return words.syntaxStartsWith(SUBJECT_PATTERN);
	}

	public static INominalObject parseSubject(WordBuffer words){
		INominalObject result = null;

		if(words.syntaxStartsWith(OBJECT_PATTERN)){
			result = parseAdditionalObject(words);
		} else if(words.syntaxStartsWith(PHONE_NUMBER_PATTERN)){
			PhoneNumberObject phoneNumber = new PhoneNumberObject();
			StringBuilder builder = new StringBuilder();

			words.consume();	// DEFINITE_ARTICLE			

			while(words.getCurrentElement().isOfType(WordType.NUMBER)){
				int number = Integer.parseInt(words.getCurrentElement().getValue());

				if(number < 100 && number > 0){
					builder.append(String.format("%2d", number));
				} else {
					builder.append(number);
				}
				
				words.consume();
			}
			
			phoneNumber.number = builder.toString();

			result = phoneNumber;
		} else if(words.syntaxStartsWith(PlaceConverter.CITY_ONLY_PATTERN)){
			result = PlaceConverter.parseNominalPlaceObject(words);
		} else if(words.syntaxStartsWith(PlaceConverter.COUNTRY_ONLY_PATTERN)){
			result = PlaceConverter.parseNominalPlaceObject(words);
		} else if(words.syntaxStartsWith(PERSON_PATTERN)){
			result = parseAdditionalPerson(words);
		} else if(words.syntaxStartsWith(PRONOUN_PATTERN)){
			PronounTarget pronoun = new PronounTarget(Type.parseSubjectPronoun(words.getCurrentElement().getValue()));
			words.consume();
			result = pronoun;
		} else if(words.syntaxStartsWith(DateConverter.FIXED_DATE_ONLY_PATTERN)){
			result = DateConverter.parseNominalDateObject(words);
		} else if(words.syntaxStartsWith(COLOR_PATTERN)){
			words.consume();
			
			ColorObject color = new ColorObject();

			color.color = words.getCurrentElement().getColorInfo();
			words.consume();

			result = color;
		} else if(words.syntaxStartsWith(WayConverter.LANGUAGE_ONLY_PATTERN)){
			result = WayConverter.parseNominalWayObject(words);
		} else if(words.syntaxStartsWith(COMMON_NAME_PATTERN)){
			result = parseComplementObject(words);
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
		} else if(words.syntaxStartsWith(PERSON_PATTERN)){
			result = parseAdditionalPerson(words);
		}/* else if(words.syntaxStartsWith(CUSTOM_PERSON_PATTERN)){
			AdditionalPerson custom = new AdditionalPerson();			
			custom.person = words.getCurrentElement().getCustomPerson();
			result = custom;
		}*/ else if(words.syntaxStartsWith(DIRECT_OBJECT_PATTERN)){
			ComplementObject obj = parseComplementObject(words);			
			result = obj;
		}

		return result;
	}

	public static boolean isAnIndirectObject(WordBuffer words){
		return words.syntaxStartsWith(INDIRECT_OBJECT_PATTERN);
	}

	public static INominalObject parseIndirectObject(WordBuffer words){
		INominalObject result = null;

		if(words.syntaxStartsWith(INDIRECT_PERSON_PATTERN)){
			words.consume();	// PREPOSITION_AT

			if(words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE)){
				words.consume();
			}

			result = parseAdditionalPerson(words);
		} else if(words.syntaxStartsWith(INDIRECT_PERSON_TYPE_PATTERN)){
			ComplementObject compl = new ComplementObject();
			
			// TODO: affecter la quantité (/possession)
			words.consume();	// PREPOSITION_AT
			
			if(words.getCurrentElement().isOfType(WordType.DEFINITE_ARTICLE)){
				words.consume();	// DEFINITE_ARTICLE
			}

			// TODO: voir s'il faut creer un type pour les mots en rapport avec la famille (cousin/tante/soeur/...)
			compl.object = words.getCurrentElement().getValue();
			words.consume();
			
			result = compl;
		}

		return result;
	}
	
	public static boolean isANominalSecondObject(WordBuffer words){
		return words.syntaxStartsWith(NOMINAL_SECOND_OBJECT_PATTERN);
	}
	
	public static INominalObject parseNominalSecondObject(WordBuffer words){
		INominalObject result = null;
		
		if(words.syntaxStartsWith(CUSTOM_OBJECT_SECOND_OBJECT_PATTERN)){
			words.consume();
			result = parseAdditionalObject(words);
		} else if(words.syntaxStartsWith(PERSON_SECOND_OBJECT_PATTERN)){
			words.consume();
			result = parseAdditionalPerson(words);
		} else if(words.syntaxStartsWith(COMPLEMENT_SECOND_OBJECT_PATTERN)){
			words.consume();
			result = parseComplementObject(words);
		} 
		
		// TODO: ajouter les autres
		
		return result;
	}

	private static AdditionalObject parseAdditionalObject(WordBuffer words){
		AdditionalObject obj = new AdditionalObject();
		
		if(CountConverter.isACountData(words)){
			obj.count = CountConverter.parseCountObject(words);
		}
		
		obj.object = words.getCurrentElement().getCustomObject();
		words.consume();
		
		return obj;
	}
	
	private static ComplementObject parseComplementObject(WordBuffer words){
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

		return obj;
	}

	private static AdditionalPerson parseAdditionalPerson(WordBuffer words){
		AdditionalPerson person = new AdditionalPerson();

		person.person = words.getCurrentElement().getCustomPerson();
		words.consume();

		return person;
	}
	
	/*private static String parsePerson(WordBuffer words){
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
	}*/
}

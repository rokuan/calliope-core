package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Type;
import com.rokuan.calliopecore.sentence.Type.Pronoun;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.nominal.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;
import com.rokuan.calliopecore.sentence.structure.nominal.PronounTarget;

public class NominalGroupConverter {
	// NominalGroup
	public static final WordPattern PRONOUN_PATTERN = WordPattern.simple(WordType.PERSONAL_PRONOUN);
	public static final WordPattern ABSTRACT_TARGET_PATTERN = WordPattern.simple(WordType.POSSESSIVE_ADJECTIVE); 
	public static final WordPattern OBJECT_PATTERN = WordPattern.sequence(			
			WordPattern.simple(WordType.POSSESSIVE_PRONOUN),
			WordPattern.or(WordPattern.simple(WordType.NUMBER))
			);
	public static final WordPattern PLACE_PATTERN = PlaceConverter.PLACE_PATTERN;		
	
	// ComplementObject
	public static final WordPattern DIRECT_OBJECT_PATTERN = WordPattern.sequence(
			WordPattern.optional(NumberConverter.COUNT_PATTERN),
			WordPattern.simple(WordType.COMMON_NAME),
			WordPattern.optional(NumberConverter.MULTIPLE_ITEMS_PATTERN),
			WordPattern.optional(CriterionConverter.CRITERIA_PATTERN));
	
	/*public static final WordPattern personPattern = WordPattern.sequence(
			WordPattern.nonEmptyList(WordPattern.simple(WordType.FIRSTNAME)),
			WordPattern.optional(pattern)
			);*/
	
	public static final WordPattern TO_PATTERN = WordPattern.or(
			WordPattern.sequence(WordPattern.simple(WordType.PREPOSITION_AT, "à"), WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE, "la"))),
			WordPattern.simple(WordType.PREPOSITION_AT, "au.*")
			);
	
	public static final WordPattern FIRST_NAME_SEQUENCE_PATTERN = WordPattern.nonEmptyList(WordPattern.simple(WordType.FIRSTNAME));
	public static final WordPattern LAST_NAME_SEQUENCE_PATTERN = WordPattern.nonEmptyList(WordPattern.simple(WordType.PROPER_NAME));
	
	public static final WordPattern personPattern = WordPattern.or(LAST_NAME_SEQUENCE_PATTERN,
			FIRST_NAME_SEQUENCE_PATTERN,
			WordPattern.sequence(FIRST_NAME_SEQUENCE_PATTERN, LAST_NAME_SEQUENCE_PATTERN),
			WordPattern.sequence(LAST_NAME_SEQUENCE_PATTERN, FIRST_NAME_SEQUENCE_PATTERN)
			);
	
	public static final WordPattern INDIRECT_OBJECT_PATTERN = WordPattern.sequence(TO_PATTERN,
			WordPattern.or(personPattern)	// TODO: ajouter le cas groupe nominal
					);
	
	public static boolean isANominalGroup(WordBuffer words){
		return words.syntaxStartsWith(PRONOUN_PATTERN);
				/*|| words.syntaxStartsWith(abstractTargetPattern)
				|| words.syntaxStartsWith(objectPattern);*/
	}
	
	public static NominalGroup parseNominalGroup(WordBuffer words){
		if(words.syntaxStartsWith(PRONOUN_PATTERN)){
			Pronoun pronoun = Type.parseSubjectPronoun(words.getCurrentElement().getValue());
			words.consume();
			return new PronounTarget(pronoun);
		} else if(words.syntaxStartsWith(OBJECT_PATTERN)){
			
		}
		
		return null;
	}
	
	public static boolean isADirectObject(WordBuffer words){
		return words.syntaxStartsWith(DIRECT_OBJECT_PATTERN) 
				|| words.syntaxStartsWith(personPattern);
	}
	
	public static NominalGroup parseDirectObject(WordBuffer words){
		ComplementObject obj = new ComplementObject();
		
		if(words.syntaxStartsWith(DIRECT_OBJECT_PATTERN)){
			if(NumberConverter.isACountData(words)){
				obj.count = NumberConverter.parseCountObject(words);
			}
			
			// TODO: parser les adjectifs et autres
			obj.object = words.getCurrentElement().getValue();
			words.consume();
			
			if(NumberConverter.isASuffixCountData(words)){
				obj.count = NumberConverter.parseSuffixCountObject(words);
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
		} else if(words.syntaxStartsWith(personPattern)){
			obj.object = parsePerson(words);
		}
		
		return obj;
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

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
	public static final WordPattern pronounPattern = WordPattern.simple(WordType.PERSONAL_PRONOUN);
	public static final WordPattern abstractTargetPattern = WordPattern.simple(WordType.POSSESSIVE_ADJECTIVE); 
	public static final WordPattern objectPattern = WordPattern.sequence(
			
			WordPattern.simple(WordType.POSSESSIVE_PRONOUN),
			WordPattern.or(WordPattern.simple(WordType.NUMBER))
			);
	public static final WordPattern placePattern = PlaceConverter.placePattern;		
	
	// ComplementObject
	public static final WordPattern directObjectPattern = WordPattern.sequence(
			WordPattern.optional(NumberConverter.COUNT_PATTERN),
			WordPattern.simple(WordType.COMMON_NAME),
			WordPattern.optional(NumberConverter.MULTIPLE_ITEMS_PATTERN),
			WordPattern.optional(CriterionConverter.criteriaPattern));
	
	/*public static final WordPattern personPattern = WordPattern.sequence(
			WordPattern.nonEmptyList(WordPattern.simple(WordType.FIRSTNAME)),
			WordPattern.optional(pattern)
			);*/
	
	public static final WordPattern toPattern = WordPattern.or(
			WordPattern.sequence(WordPattern.simple(WordType.PREPOSITION_AT, "à"), WordPattern.optional(WordPattern.simple(WordType.DEFINITE_ARTICLE, "la"))),
			WordPattern.simple(WordType.PREPOSITION_AT, "au.*")
			);
	
	public static final WordPattern firstNameSequencePattern = WordPattern.nonEmptyList(WordPattern.simple(WordType.FIRSTNAME));
	public static final WordPattern lastNameSequencePattern = WordPattern.nonEmptyList(WordPattern.simple(WordType.PROPER_NAME));
	
	public static final WordPattern personPattern = WordPattern.or(lastNameSequencePattern,
			firstNameSequencePattern,
			WordPattern.sequence(firstNameSequencePattern, lastNameSequencePattern),
			WordPattern.sequence(lastNameSequencePattern, firstNameSequencePattern)
			);
	
	public static final WordPattern indirectObjectPattern = WordPattern.sequence(toPattern,
			WordPattern.or(personPattern)	// TODO: ajouter le cas groupe nominal
					);
	
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
		return words.syntaxStartsWith(directObjectPattern) 
				|| words.syntaxStartsWith(personPattern);
	}
	
	public static NominalGroup parseDirectObject(WordBuffer words){
		ComplementObject obj = new ComplementObject();
		
		if(words.syntaxStartsWith(directObjectPattern)){
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
		return words.syntaxStartsWith(indirectObjectPattern);
	}
	
	public static NominalGroup parseIndirectObject(WordBuffer words){
		ComplementObject obj = new ComplementObject();
		
		if(words.syntaxStartsWith(indirectObjectPattern)){
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

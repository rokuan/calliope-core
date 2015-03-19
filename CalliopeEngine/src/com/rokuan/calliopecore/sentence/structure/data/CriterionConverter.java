package com.rokuan.calliopecore.sentence.structure.data;

import java.util.ArrayList;
import java.util.List;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;
import com.rokuan.calliopecore.sentence.structure.data.criteria.CriterionObject;
import com.rokuan.calliopecore.sentence.structure.data.criteria.FieldCriterionObject;
import com.rokuan.calliopecore.sentence.structure.data.criteria.SuperlativeCriterionObject;

public class CriterionConverter {
	// Le plus proche
	public static final WordPattern criteriaPattern = WordPattern.sequence(
			WordPattern.simple(WordType.DEFINITE_ARTICLE),
			WordPattern.simple(WordType.SUPERLATIVE),
			WordPattern.simple(WordType.ADJECTIVE)
			);

	// Le plus petit homme
	//public static final WordPattern superlativePattern = WordPattern.sequence(criteriaPattern, WordPattern.optional(WordPattern.simple(WordType.COMMON_NAME)));
	public static final WordPattern superlativePattern = WordPattern.separatedNonEmptyList(criteriaPattern, WordPattern.optional(WordPattern.simple(WordType.PREPOSITION_AND)));

	public static final WordPattern superlativeAdjectiveFirstPattern = WordPattern.sequence(WordPattern.simple(WordType.SUPERLATIVE), WordPattern.simple(WordType.ADJECTIVE), WordPattern.simple(WordType.COMMON_NAME));
	public static final WordPattern superlativeNameFirstPattern = WordPattern.sequence(WordPattern.simple(WordType.COMMON_NAME), WordPattern.simple(WordType.DEFINITE_ARTICLE), WordPattern.simple(WordType.SUPERLATIVE), WordPattern.simple(WordType.ADJECTIVE)); 

	// L'homme le plus riche / le plus petit homme
	public static final WordPattern fieldCriteriaPattern = WordPattern.sequence(
			WordPattern.simple(WordType.DEFINITE_ARTICLE),
			WordPattern.or(
					superlativeAdjectiveFirstPattern,
					superlativeNameFirstPattern
					)
			);

	// TODO: avoir la possibilite d'ajouter un filtre sur la valeur du mot
	// qui a/ayant/avec la plus grande surface
	public static final WordPattern specificationHavePattern = WordPattern.sequence(
			WordPattern.or(
					WordPattern.simple(WordType.PREPOSITION_WITH),
					WordPattern.sequence(WordPattern.optional(WordPattern.simple(WordType.RELATIVE_PRONOUN, "qui")), WordPattern.simple(WordType.VERB, null, "avoir"))
					),
					WordPattern.separatedNonEmptyList(fieldCriteriaPattern, WordPattern.optional(WordPattern.simple(WordType.PREPOSITION_AND)))
			);
	// qui est/etant le plus proche
	public static final WordPattern specificationBePattern = WordPattern.sequence(
			WordPattern.optional(WordPattern.simple(WordType.RELATIVE_PRONOUN, "qui")),
			WordPattern.simple(WordType.VERB, null, "être"),
			WordPattern.separatedNonEmptyList(criteriaPattern, WordPattern.optional(WordPattern.simple(WordType.PREPOSITION_AND)))
			);

	public static boolean isACriterionData(WordBuffer words){
		return words.syntaxStartsWith(specificationHavePattern)
				|| words.syntaxStartsWith(specificationBePattern)
				|| words.syntaxStartsWith(superlativePattern);
	}

	public static List<CriterionObject> parseCriterionData(WordBuffer words){
		List<CriterionObject> criteria = new ArrayList<CriterionObject>();

		if(words.syntaxStartsWith(specificationHavePattern)){
			if(words.getCurrentElement().isOfType(WordType.PREPOSITION_WITH)){
				words.consume();	// avec
			} else {
				if(words.getCurrentElement().isOfType(WordType.RELATIVE_PRONOUN)){
					words.consume();	// qui
				}

				words.consume();	// avoir
			}

			do {
				FieldCriterionObject fCriterion = new FieldCriterionObject();
				words.consume();	// DEFINITE_ARTICLE

				if(words.syntaxStartsWith(superlativeNameFirstPattern)){
					fCriterion.field = words.getCurrentElement().getValue();
					words.consume();
					fCriterion.compare = CriterionObject.parseComparisonType(words.getCurrentElement().getValue());
					words.consume();
					fCriterion.criterion = words.getCurrentElement().getValue();
					words.consume();
				} else {
					// Adjective comes before common name
					fCriterion.compare = CriterionObject.parseComparisonType(words.getCurrentElement().getValue());
					words.consume();
					fCriterion.criterion = words.getCurrentElement().getValue();
					words.consume();
					fCriterion.field = words.getCurrentElement().getValue();
					words.consume();
				}

				criteria.add(fCriterion);

				if(words.hasNext() && words.getCurrentElement().isOfType(WordType.PREPOSITION_AND)){
					words.consume();
				}
			} while(words.syntaxStartsWith(fieldCriteriaPattern));
		} else if(words.syntaxStartsWith(specificationBePattern)){
			if(words.getCurrentElement().isOfType(WordType.RELATIVE_PRONOUN)){
				words.consume();
			}
			
			words.consume();	// être
			
			do {
				SuperlativeCriterionObject sCriterion = new SuperlativeCriterionObject();
				
				words.consume();	// DEFINITE_ARTICLE
				sCriterion.compare = CriterionObject.parseComparisonType(words.getCurrentElement().getValue());
				words.consume();
				sCriterion.criterion = words.getCurrentElement().getValue();
				words.consume();
				
				criteria.add(sCriterion);
				
				if(words.hasNext() && words.getCurrentElement().isOfType(WordType.PREPOSITION_AND)){
					words.consume();
				}
			} while(words.syntaxStartsWith(criteriaPattern));
		} else if(words.syntaxStartsWith(superlativePattern)){
			// TODO: fusionner avec le cas specificationBePattern ?
			do {
				SuperlativeCriterionObject sCriterion = new SuperlativeCriterionObject();
				
				words.consume();	// DEFINITE_ARTICLE
				sCriterion.compare = CriterionObject.parseComparisonType(words.getCurrentElement().getValue());
				words.consume();
				sCriterion.criterion = words.getCurrentElement().getValue();
				words.consume();
				
				criteria.add(sCriterion);
				
				if(words.hasNext() && words.getCurrentElement().isOfType(WordType.PREPOSITION_AND)){
					words.consume();
				}
			} while(words.syntaxStartsWith(criteriaPattern));
		}

		return criteria;
	}
}

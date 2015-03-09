package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;

public class CriteriaConverter {
	// Le plus proche
	public static final WordPattern criteriaPattern = WordPattern.sequence(
			WordPattern.simple(WordType.DEFINITE_ARTICLE),
			WordPattern.simple(WordType.SUPERLATIVE),
			WordPattern.simple(WordType.ADJECTIVE)
			);
	
	// Le plus petit homme
	public static final WordPattern superlativePattern = WordPattern.sequence(criteriaPattern, WordPattern.optional(WordPattern.simple(WordType.COMMON_NAME)));
	
	// L'homme le plus riche
	public static final WordPattern fieldCriteriaPattern = WordPattern.sequence(
			WordPattern.simple(WordType.DEFINITE_ARTICLE),
			WordPattern.simple(WordType.COMMON_NAME),
			WordPattern.simple(WordType.DEFINITE_ARTICLE),
			WordPattern.simple(WordType.SUPERLATIVE),
			WordPattern.simple(WordType.ADJECTIVE)
			);
	
	// TODO: avoir la possibilite d'ajouter un filtre sur la valeur du mot
	// Ayant/avec la plus grande surface || Etant le plus
	public static final WordPattern specificationHavePattern = WordPattern.sequence(
			WordPattern.optional(WordPattern.simple(WordType.RELATIVE_PRONOUN, "qui")),
			WordPattern.or(WordPattern.simple(WordType.VERB, "avoir"), WordPattern.simple(WordType.PREPOSITION_WITH)),
			fieldCriteriaPattern
			);
	public static final WordPattern specificationBePattern = WordPattern.sequence(
			WordPattern.optional(WordPattern.simple(WordType.RELATIVE_PRONOUN, "qui")),
			WordPattern.simple(WordType.VERB, "être"),
			criteriaPattern
			);
	
	public static boolean isACriteriaData(WordBuffer words){
		return words.syntaxStartsWith(specificationHavePattern)
				|| words.syntaxStartsWith(specificationBePattern)
				|| words.syntaxStartsWith(superlativePattern);
	}
}

package com.rokuan.calliopecore.sentence;

import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;

public class SentencePattern {
	public static final WordPattern yesNoQuestionPattern = WordPattern.sequence(
			WordPattern.optional(WordPattern.simple(WordType.PERSONAL_PRONOUN)),	// m', t'
			WordPattern.simple(WordType.AUXILIARY),
			WordPattern.optional(WordPattern.simple(WordType.CONJUGATION_LINK)),
			WordPattern.simple(WordType.PERSONAL_PRONOUN),
			WordPattern.simple(WordType.VERB)
			);
	public static final WordPattern orderPattern = WordPattern.sequence(
			WordPattern.simple(WordType.VERB), 
			WordPattern.optional(WordPattern.simple(WordType.PERSONAL_PRONOUN))
			);
	public static final WordPattern resultQuestionPattern = WordPattern.sequence(
			WordPattern.or(WordPattern.simple(WordType.INTERROGATIVE_PRONOUN), WordPattern.simple(WordType.INTERROGATIVE_ADJECTIVE)),
			WordPattern.simple(WordType.VERB)
			);
}

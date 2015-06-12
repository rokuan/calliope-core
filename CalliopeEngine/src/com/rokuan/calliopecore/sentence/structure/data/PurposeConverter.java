package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;

public class PurposeConverter {	
	/*public static final WordPattern conjugatedPurposePattern = WordPattern.sequence(
			WordPattern.simple(WordType.ANY, "afin"),
			WordPattern.optional(WordPattern.simple(WordType.ANY, "que")),
			WordPattern.optional(WordPattern.simple(WordType.TARGET_PRONOUN)),
			WordPattern.simple(WordType.VERB)
			);*/
	public static final WordPattern INFINITIVE_PURPOSE_PATTERN = WordPattern.sequence(
			WordPattern.or(WordPattern.simple(WordType.ANY, "pour"), 
					WordPattern.sequence(WordPattern.simple(WordType.ANY, "afin"), WordPattern.simple(WordType.ANY, "de"))),
					WordPattern.optional(WordPattern.simple(WordType.TARGET_PRONOUN)),
					WordPattern.simple(WordType.VERB)
			);
}

package com.rokuan.calliopecore.sentence.structure;

import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;

public class PrepositionPattern {
	public static final WordPattern toPattern = WordPattern.or(
			WordPattern.simple(WordType.PREPOSITION_AT),
			WordPattern.simple(WordType.ANY, "direction"),
			WordPattern.sequence(WordPattern.simple(WordType.ANY, "en"), WordPattern.simple(WordType.ANY, "direction"), WordPattern.simple(WordType.ANY, "de")),
			WordPattern.simple(WordType.ANY, "vers")
			);
	
	public static final WordPattern howPattern = WordPattern.or(
			WordPattern.simple(WordType.ANY, "par"),
			WordPattern.simple(WordType.ANY, "comme")
			);
}

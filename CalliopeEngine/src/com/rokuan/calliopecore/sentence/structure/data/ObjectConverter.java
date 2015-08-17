package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;

public class ObjectConverter {	
	public static final WordPattern OBJECT_PATTERN = WordPattern.or(
			WordPattern.sequence(CountConverter.COUNT_PATTERN, WordPattern.simpleWord(WordType.OBJECT)),
			WordPattern.simpleWord(WordType.PERSON),
			WordPattern.sequence(CountConverter.COUNT_PATTERN, WordPattern.nonEmptyList(WordPattern.simpleWord(WordType.COMMON_NAME)))
			);
}

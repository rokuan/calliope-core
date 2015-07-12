package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.pattern.WordPattern;
import com.rokuan.calliopecore.sentence.Word.WordType;

public class ObjectConverter {	
	public static final WordPattern OBJECT_PATTERN = WordPattern.or(
			WordPattern.sequence(CountConverter.COUNT_PATTERN, WordPattern.simple(WordType.OBJECT)),
			WordPattern.simple(WordType.PERSON),
			WordPattern.sequence(CountConverter.COUNT_PATTERN, WordPattern.nonEmptyList(WordPattern.simple(WordType.COMMON_NAME)))
			);
}

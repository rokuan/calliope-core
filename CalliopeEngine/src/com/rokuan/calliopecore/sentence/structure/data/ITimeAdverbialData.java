package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.IWord;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;

public interface ITimeAdverbialData<T extends IWord> {
	boolean isATimeAdverbialData(WordBuffer<T> words);
	ITimeObject parseTimeAdverbialData(WordBuffer<T> words);
}

package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.IWord;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;

public interface IPurposeAdverbialData<T extends IWord> {
	boolean isAPurposeAdverbialData(WordBuffer<T> words);
	IPurposeObject parsePurposeAdverbialData(WordBuffer<T> words);
}

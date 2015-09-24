package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.IWord;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;

public interface IWayAdverbialData<T extends IWord> {
	boolean isAWayAdverbialData(WordBuffer<T> words);
	IWayObject parseWayAdverbialData(WordBuffer<T> words);
}

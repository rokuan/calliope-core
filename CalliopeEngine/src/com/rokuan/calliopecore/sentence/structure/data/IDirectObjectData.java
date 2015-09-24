package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.IWord;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;

public interface IDirectObjectData<T extends IWord> {
	boolean isADirectObjectData(WordBuffer<T> words);
	INominalObject parseDirectObject(WordBuffer<T> words);
}

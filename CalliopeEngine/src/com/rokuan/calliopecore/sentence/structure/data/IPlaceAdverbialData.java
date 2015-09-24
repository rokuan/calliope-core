package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.IWord;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;

public interface IPlaceAdverbialData<T extends IWord> {
	boolean isAPlaceAdverbialData(WordBuffer<T> words);
	IPlaceObject parsePlaceAdverbialData(WordBuffer<T> words);
}

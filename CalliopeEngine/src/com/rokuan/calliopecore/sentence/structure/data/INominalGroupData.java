package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.IWord;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;

public interface INominalGroupData<T extends IWord> extends IDirectObjectData<T>, ISecondObjectData<T> {
	boolean isASubjectData(WordBuffer<T> words);
	IPlaceObject parseSubjectData(WordBuffer<T> words);
}

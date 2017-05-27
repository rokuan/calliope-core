package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.IWord;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;

public interface ISecondObjectData<T extends IWord> {
    boolean isANominalSecondObjectData(WordBuffer<T> words);

    INominalObject parseNominalSecondObjectData(WordBuffer<T> words);

    boolean isAVerbalSecondObjectData(WordBuffer<T> words);

    IPlaceObject parseVerbalSecondObjectData(WordBuffer<T> words);
}

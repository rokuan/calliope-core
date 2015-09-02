package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;

public interface ISecondObjectData {
	boolean isANominalSecondObjectData(WordBuffer words);
	INominalObject parseNominalSecondObjectData(WordBuffer words);
	boolean isAVerbalSecondObjectData(WordBuffer words);
	IPlaceObject parseVerbalSecondObjectData(WordBuffer words);
}

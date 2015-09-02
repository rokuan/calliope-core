package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;

public interface INominalGroupData extends IDirectObjectData, ISecondObjectData {
	boolean isASubjectData(WordBuffer words);
	IPlaceObject parseSubjectData(WordBuffer words);
}

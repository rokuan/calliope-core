package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;

public interface IPlaceAdverbialData {
	boolean isAPlaceAdverbialData(WordBuffer words);
	IPlaceObject parsePlaceAdverbialData(WordBuffer words);
}

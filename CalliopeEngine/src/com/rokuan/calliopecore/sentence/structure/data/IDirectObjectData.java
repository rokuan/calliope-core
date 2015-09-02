package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;

public interface IDirectObjectData {
	boolean isADirectObjectData(WordBuffer words);
	INominalObject parseDirectObject(WordBuffer words);
}

package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;

public interface IWayAdverbialData {
	boolean isAWayAdverbialData(WordBuffer words);
	IWayObject parseWayAdverbialData(WordBuffer words);
}

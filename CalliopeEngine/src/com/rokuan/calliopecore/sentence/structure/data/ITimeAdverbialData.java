package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;

public interface ITimeAdverbialData {
	boolean isATimeAdverbialData(WordBuffer words);
	ITimeObject parseTimeAdverbialData(WordBuffer words);
}

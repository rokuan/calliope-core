package com.rokuan.calliopecore.sentence.structure.data;

import com.rokuan.calliopecore.parser.WordBuffer;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;

public interface IPurposeAdverbialData {
	boolean isAPurposeAdverbialData(WordBuffer words);
	IPurposeObject parsePurposeAdverbialData(WordBuffer words);
}

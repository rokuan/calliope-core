package com.rokuan.calliopecore.sentence.structure.content;

import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial.PurposeContext;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial.PurposeType;

public interface IPurposeObject {
	PurposeType getPurposeType();
	
	PurposeContext getPurposePreposition();
	void setPurposePreposition(PurposeContext prep);
}

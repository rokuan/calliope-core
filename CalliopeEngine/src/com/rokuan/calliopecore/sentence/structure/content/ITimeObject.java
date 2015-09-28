package com.rokuan.calliopecore.sentence.structure.content;

import com.rokuan.calliopecore.sentence.ITimePreposition;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeType;

public interface ITimeObject {
	TimeType getTimeType();	
	ITimePreposition getTimePreposition();
	void setTimePreposition(ITimePreposition prep);
}

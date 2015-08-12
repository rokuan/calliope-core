package com.rokuan.calliopecore.sentence.structure.content;

import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.DateContext;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeType;

public interface ITimeObject {
	TimeType getTimeType();
	
	DateContext getTimePreposition();
	void setTimePreposition(DateContext prep);
}

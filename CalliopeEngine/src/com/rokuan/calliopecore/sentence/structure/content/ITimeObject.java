package com.rokuan.calliopecore.sentence.structure.content;

import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeContext;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeType;

public interface ITimeObject {
	TimeType getTimeType();
	
	TimeContext getTimePreposition();
	void setTimePreposition(TimeContext prep);
}

package com.rokuan.calliopecore.sentence.structure.data.time;

public class DayPartObject extends TimeAdverbial {
	public enum DayPartType {
		MORNING,
		NOON,
		AFTERNOON,
		EVENING,
		NIGHT
	}
	
	public DayPartType dayPartType = DayPartType.MORNING;
	
	@Override
	public TimeType getTimeType() {
		return TimeType.DAY_PART;
	}
}

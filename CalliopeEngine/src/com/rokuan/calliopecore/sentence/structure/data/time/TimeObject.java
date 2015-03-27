package com.rokuan.calliopecore.sentence.structure.data.time;

/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public abstract class TimeObject {
    public enum TimeUnit {
        SECONDS,
        MINUTES,
        HOURS,
        DAY,
        WEEK,
        MONTH,
        YEAR
    }

    public enum TimeTense {
        PAST,
        PRESENT,
        FUTURE
    }

    public enum TimeState {
        FIXED,
        RELATIVE
    }

    public enum TimeInterval {
        SINGLE,
        FROM_MIN_TO_MAX,
        FROM_PAST_TO_NOW,
        FROM_NOW_TO_FUTURE
    }

    public enum TimeType {
    	SINGLE,
    	RELATIVE,
    	PERIOD
    }
    
    public enum DateDefinition {
    	DATE_AND_TIME,
    	DATE_ONLY,
    	TIME_ONLY
    }

    public TimeTense tense = TimeTense.PRESENT;
    private TimeType type;
    public TimeInterval interval = TimeInterval.SINGLE;

    public TimeObject(TimeType ty){
    	type = ty;
    }
    
    public TimeType getType(){
    	return type;
    }
}

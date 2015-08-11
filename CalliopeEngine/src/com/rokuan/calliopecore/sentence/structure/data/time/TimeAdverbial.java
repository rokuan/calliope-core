package com.rokuan.calliopecore.sentence.structure.data.time;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.content.ITimeObject;

/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public abstract class TimeAdverbial implements ITimeObject {
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
    	PERIOD,
    	VERBAL
    }
    
    public enum DateDefinition {
    	DATE_AND_TIME,
    	DATE_ONLY,
    	TIME_ONLY
    }
    
    public enum DateContext {
    	BEFORE,
    	AFTER,
    	DURING,
    	UNTIL,
    	WHEN,
    	SINCE
    }

	@Expose
    public TimeTense tense = TimeTense.PRESENT;

	@Expose
    public TimeInterval interval = TimeInterval.SINGLE;
}

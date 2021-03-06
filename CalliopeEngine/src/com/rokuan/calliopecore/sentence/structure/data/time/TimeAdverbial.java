package com.rokuan.calliopecore.sentence.structure.data.time;

import com.rokuan.calliopecore.sentence.ITimePreposition;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.VerbalGroup;

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

    /*public enum TimeState {
        FIXED,
        RELATIVE
    }

    public enum TimeInterval {
        SINGLE,
        FROM_MIN_TO_MAX,
        FROM_PAST_TO_NOW,
        FROM_NOW_TO_FUTURE
    }*/

    public enum TimeType {
        SINGLE,
        RELATIVE,
        PERIOD,
        VERBAL,
        DAY_PART
    }

    public enum DateDefinition {
        DATE_AND_TIME,
        DATE_ONLY,
        TIME_ONLY
    }

    public enum TimeContext {
        BEFORE,
        AFTER,
        DURING,
        UNTIL,
        WHEN,
        SINCE,
        START,
        END
    }

    private ITimePreposition timePreposition = new ITimePreposition() {
        @Override
        public String getValue() {
            return "";
        }

        @Override
        public TimeContext getContext() {
            return TimeContext.WHEN;
        }
    };

    @Override
    public ITimePreposition getTimePreposition() {
        return timePreposition;
    }

    @Override
    public void setTimePreposition(ITimePreposition prep) {
        timePreposition = prep;
    }

    public static Class<? extends ITimeObject> getClassFromTimeType(TimeType ty) {
        Class<? extends ITimeObject> clazz = null;

        switch (ty) {
            case PERIOD:
                clazz = TimePeriodObject.class;
                break;
            case RELATIVE:
                clazz = RelativeTimeObject.class;
                break;
            case SINGLE:
                clazz = SingleTimeObject.class;
                break;
            case VERBAL:
                clazz = VerbalGroup.class;
                break;
            case DAY_PART:
                clazz = DayPartObject.class;
                break;
        }

        return clazz;
    }
}

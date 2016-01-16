package com.rokuan.calliopecore.sentence.structure.data.time;

import java.util.Calendar;
import java.util.Date;


/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class RelativeTimeObject extends TimeAdverbial {
    public int[] periods = new int[TimeUnit.values().length];
    public DateDefinition dateDefinition = DateDefinition.DATE_AND_TIME;	
	public TimeTense tense = TimeTense.PRESENT;

    public RelativeTimeObject(){    	
        for(int i=0; i<periods.length; i++){
            periods[i] = 0;
        }
    }

    public Date getDate(){
        Calendar calendar = Calendar.getInstance();

        if(this.tense == TimeTense.PRESENT){
            return calendar.getTime();
        }

        int factor = 1;

        if(this.tense == TimeTense.PAST) {
            factor = -1;
        }

        calendar.add(Calendar.YEAR, factor * periods[TimeUnit.YEAR.ordinal()]);
        calendar.add(Calendar.MONTH, factor * periods[TimeUnit.MONTH.ordinal()]);
        calendar.add(Calendar.DATE, 7 * factor * periods[TimeUnit.WEEK.ordinal()]);
        calendar.add(Calendar.DATE, factor * periods[TimeUnit.DAY.ordinal()]);
        calendar.add(Calendar.HOUR, factor * periods[TimeUnit.HOURS.ordinal()]);
        calendar.add(Calendar.MINUTE, factor * periods[TimeUnit.MINUTES.ordinal()]);
        calendar.add(Calendar.SECOND, factor * periods[TimeUnit.SECONDS.ordinal()]);

        return calendar.getTime();
    }

	@Override
	public TimeType getTimeType() {
		return TimeType.RELATIVE;
	}
}

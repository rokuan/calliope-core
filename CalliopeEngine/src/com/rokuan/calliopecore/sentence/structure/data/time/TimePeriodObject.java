package com.rokuan.calliopecore.sentence.structure.data.time;

import java.util.Date;

import com.google.gson.annotations.Expose;


/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class TimePeriodObject extends TimeAdverbial {
	@Expose
	public DateDefinition fromDateDefinition = DateDefinition.DATE_AND_TIME;

	@Expose
	public DateDefinition toDateDefinition = DateDefinition.DATE_AND_TIME;

	@Expose
    public Date from;

	@Expose
    public Date to;

    public Date getFrom(){
        return from;
    }

    public Date getTo(){
        return to;
    }

	@Override
	public TimeType getTimeType() {
		return TimeType.PERIOD;
	}
}

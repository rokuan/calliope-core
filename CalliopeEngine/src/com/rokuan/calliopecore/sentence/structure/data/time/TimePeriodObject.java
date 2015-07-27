package com.rokuan.calliopecore.sentence.structure.data.time;

import java.util.Date;

import com.google.gson.annotations.Expose;


/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class TimePeriodObject extends TimeObject {
	@Expose
	public DateDefinition fromDateDefinition = DateDefinition.DATE_AND_TIME;

	@Expose
	public DateDefinition toDateDefinition = DateDefinition.DATE_AND_TIME;

	@Expose
    public Date from;

	@Expose
    public Date to;
    
    public TimePeriodObject(){
    	super(TimeType.PERIOD);
    }

    public Date getFrom(){
        return from;
    }

    public Date getTo(){
        return to;
    }
}

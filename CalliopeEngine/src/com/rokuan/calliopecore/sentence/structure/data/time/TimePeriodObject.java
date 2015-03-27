package com.rokuan.calliopecore.sentence.structure.data.time;

import java.util.Date;


/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class TimePeriodObject extends TimeObject {
	public DateDefinition fromDateDefinition = DateDefinition.DATE_AND_TIME;
	public DateDefinition toDateDefinition = DateDefinition.DATE_AND_TIME;
    public Date from;
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

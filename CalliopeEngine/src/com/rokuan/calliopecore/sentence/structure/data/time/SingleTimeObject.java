package com.rokuan.calliopecore.sentence.structure.data.time;

import java.util.Date;

import com.google.gson.annotations.Expose;


/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class SingleTimeObject extends TimeAdverbial {
	@Expose
	public DateContext preposition;

	@Expose
	public DateDefinition dateDefinition = DateDefinition.DATE_AND_TIME;

	@Expose
	public Date date;

	@Override
	public TimeType getTimeType() {
		return TimeType.SINGLE;
	}
}

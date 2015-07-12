package com.rokuan.calliopecore.sentence.structure.data.time;

import java.util.Date;


/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class SingleTimeObject extends TimeObject {
	public DateContext preposition;
	public DateDefinition dateDefinition = DateDefinition.DATE_AND_TIME;
	public Date date;
	
	public SingleTimeObject(){
		super(TimeType.SINGLE);
	}
}

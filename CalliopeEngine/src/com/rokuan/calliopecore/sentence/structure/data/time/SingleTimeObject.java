package com.rokuan.calliopecore.sentence.structure.data.time;

import java.util.Date;

/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class SingleTimeObject extends TimeObject {
	/*public int date = -1;
	public int month = -1;
	public int year = -1;*/
	public Date date;
	
	public SingleTimeObject(){
		super(TimeType.SINGLE);
	}
}

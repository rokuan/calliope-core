package com.rokuan.calliopecore.sentence.structure.data.time;

import java.util.Date;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NominalGroup.GroupType;


/**
 * Created by LEBEAU Christophe on 20/02/2015.
 */
public class SingleTimeObject extends TimeAdverbial implements INominalObject {
	@Expose
	public DateDefinition dateDefinition = DateDefinition.DATE_AND_TIME;

	@Expose
	public Date date;

	@Override
	public TimeType getTimeType() {
		return TimeType.SINGLE;
	}

	@Override
	public GroupType getGroupType() {
		return GroupType.DATE;
	}
}

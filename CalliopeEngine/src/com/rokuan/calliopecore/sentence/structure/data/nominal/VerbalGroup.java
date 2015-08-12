package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial.PurposeContext;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial.PurposeType;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.DateContext;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial.TimeType;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayContext;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;


public class VerbalGroup extends NominalGroup implements IWayObject, ITimeObject, IPurposeObject {
	private WayContext wayPreposition;
	private DateContext timePreposition;
	private PurposeContext purposePreposition;
	
	@Override
	public GroupType getGroupType() {
		return GroupType.VERB;
	}

	@Override
	public WayType getWayType() {
		return WayType.VERBAL;
	}

	@Override
	public PurposeType getPurposeType() {
		return PurposeType.VERBAL;
	}

	@Override
	public TimeType getTimeType() {
		return TimeType.VERBAL;
	}

	@Override
	public PurposeContext getPurposePreposition() {
		return purposePreposition;
	}

	@Override
	public void setPurposePreposition(PurposeContext prep) {
		purposePreposition = prep;
	}

	@Override
	public DateContext getTimePreposition() {
		return timePreposition;
	}

	@Override
	public void setTimePreposition(DateContext prep) {
		timePreposition = prep;
	}

	@Override
	public WayContext getWayPreposition() {
		return wayPreposition;
	}

	@Override
	public void setWayPreposition(WayContext prep) {
		wayPreposition = prep;		
	}
}

package com.rokuan.calliopecore.sentence.structure.content;

import com.rokuan.calliopecore.sentence.ActionObject;

public interface IVerbalObject {
	void setSubject(INominalObject subject);
	void setAction(ActionObject action);
	void setDirectObject(INominalObject dObject);
	void setTarget(INominalObject target);
	void setPlaceAdverbial(IPlaceObject pObject);
	void setTimeAdverbial(ITimeObject tObject);
	void setWayAdverbial(IWayObject wObject);
	void setPurposeAdverbial(IPurposeObject pObject);
	
	INominalObject getSubject();
	ActionObject getAction();
	INominalObject getDirectObject();
	INominalObject getTarget();
	IPlaceObject getPlaceAdverbial();
	ITimeObject getTimeAdverbial();
	IWayObject getWayAdverbial();
	IPurposeObject getPurposeAdverbial();
}
package com.rokuan.calliopecore.sentence.structure.content;

import com.rokuan.calliopecore.sentence.Action;

public interface IVerbalObject {
	void setSubject(INominalObject subject);
	void setAction(Action.VerbAction action);
	void setDirectObject(INominalObject dObject);
	void setTarget(INominalObject target);
	void setPlaceAdverbial(IPlaceObject pObject);
	void setTimeAdverbial(ITimeObject tObject);
	void setWayAdverbial(IWayObject wObject);
	void setPurposeAdverbial(IPurposeObject pObject);
	
	INominalObject getSubject();
	Action.VerbAction getAction();
	INominalObject getDirectObject();
	INominalObject getTarget();
	IPlaceObject getPlaceAdverbial();
	ITimeObject getTimeAdverbial();
	IWayObject getWayAdverbial();
	IPurposeObject getPurposeAdverbial();
}

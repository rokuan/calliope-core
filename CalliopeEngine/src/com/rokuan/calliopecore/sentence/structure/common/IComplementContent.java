package com.rokuan.calliopecore.sentence.structure.common;

import com.rokuan.calliopecore.sentence.structure.content.INominalObject;
import com.rokuan.calliopecore.sentence.structure.content.IPlaceObject;
import com.rokuan.calliopecore.sentence.structure.content.IPurposeObject;
import com.rokuan.calliopecore.sentence.structure.content.ITimeObject;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;

public interface IComplementContent {
	void setDirectObject(INominalObject direct);
	void setIndirectObject(INominalObject indirect);
	void setPlaceAdverbial(IPlaceObject place);
	void setTimeAdverbial(ITimeObject time);
	void setWayAdverbial(IWayObject way);
	void setPurposeAdverbial(IPurposeObject purpose);
	
	INominalObject getDirectObject();
	INominalObject getIndirectObject();
	IPlaceObject getPlaceAdverbial();
	ITimeObject getTimeAdverbial();
	IWayObject getWayAdverbial();
	IPurposeObject getPurposeAdverbial();
}

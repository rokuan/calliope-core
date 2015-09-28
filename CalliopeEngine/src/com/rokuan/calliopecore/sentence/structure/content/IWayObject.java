package com.rokuan.calliopecore.sentence.structure.content;

import com.rokuan.calliopecore.sentence.IWayPreposition;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;

public interface IWayObject {
	WayType getWayType();	
	IWayPreposition getWayPreposition();
	void setWayPreposition(IWayPreposition prep);
}

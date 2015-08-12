package com.rokuan.calliopecore.sentence.structure.content;

import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayContext;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;

public interface IWayObject {
	WayType getWayType();
	
	WayContext getWayPreposition();
	void setWayPreposition(WayContext prep);
}

package com.rokuan.calliopecore.sentence.structure.way;

import com.rokuan.calliopecore.content.IWayObject;

public abstract class WayAdverbial implements IWayObject {
	public enum WayType {
		CUSTOM,
		VERBAL,
		NOMINAL,
		LANGUAGE,
		COLOR
	}
	
	public enum WayContext {
		BY
	}
}

package com.rokuan.calliopecore.sentence.structure.way;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.structure.nominal.ComplementObject;

public class NominalWayObject extends WayAdverbial {
	@Expose
	public WayContext preposition;
	@Expose
	public ComplementObject object;

	@Override
	public WayType getWayType() {
		return WayType.NOMINAL;
	}
}

package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.structure.data.nominal.UnitObject.UnitType;

public class QuantityObject extends NominalGroup {
	@Expose
	public double amount = 0;
	
	@Expose
	public UnitType unitType;
		
	@Override
	public GroupType getGroupType() {
		return GroupType.QUANTITY;
	}
}

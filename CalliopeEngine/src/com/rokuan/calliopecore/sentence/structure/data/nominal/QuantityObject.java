package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.structure.data.nominal.UnitObject.UnitType;

public class QuantityObject extends NominalGroup {
	public double amount = 0;
	
	public UnitType unitType;
		
	@Override
	public GroupType getGroupType() {
		return GroupType.QUANTITY;
	}
}

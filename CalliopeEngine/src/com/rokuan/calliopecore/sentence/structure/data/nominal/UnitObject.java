package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;

public class UnitObject extends NominalGroup {
	public enum UnitType {
		MILLIMETER,
		CENTIMETER,
		DECIMETER,
		METER,
		DECAMETER,
		HECTOMETER,
		KILOMETER,
		// TODO: ajouter les autres
		BYTE,
		KILOBYTE,
		MEGABYTE,
		GIGABYTE,
		TERABYTE,
		
		MILLISECOND,
		SECOND,
		MINUTE,
		HOUR,
		DAY,
		YEAR,
		// TODO: ajouter les autres
		
		WATT,
		
		AMPERE,
		
		JOULE,
		
		BAR
	}
	
	@Expose
	public double amount = 0;
	
	@Expose
	public UnitType unitType;
		
	@Override
	public GroupType getGroupType() {
		return GroupType.UNIT;
	}
}

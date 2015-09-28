package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.IWayPreposition;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;

public class UnitObject extends NominalGroup implements IWayObject {
	public enum UnitType {
		NONE,
		
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
	private IWayPreposition wayPreposition;
	
	@Expose
	public UnitType unitType = UnitType.NONE;
		
	@Override
	public GroupType getGroupType() {
		return GroupType.UNIT;
	}

	@Override
	public WayType getWayType() {
		return WayType.UNIT;
	}

	@Override
	public IWayPreposition getWayPreposition() {
		return wayPreposition;
	}

	@Override
	public void setWayPreposition(IWayPreposition prep) {
		wayPreposition = prep;
	}
}

package com.rokuan.calliopecore.sentence.structure.data.way;

import com.rokuan.calliopecore.sentence.IWayPreposition;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.ColorObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.NameObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.LanguageObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.UnitObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.VerbalGroup;

public abstract class WayAdverbial implements IWayObject {
	public enum WayType {
		CUSTOM,
		VERBAL,
		NOMINAL,
		LANGUAGE,
		COLOR,
		TRANSPORT,
		UNIT
	}

	public enum WayContext {
		BY
	}

	private IWayPreposition wayPreposition;

	@Override
	public IWayPreposition getWayPreposition() {
		return wayPreposition;
	}

	@Override
	public void setWayPreposition(IWayPreposition prep) {
		wayPreposition = prep;
	}	

	public static Class<? extends IWayObject> getClassFromWayType(WayType ty){
		Class<? extends IWayObject> clazz = null;

		switch(ty){
		case CUSTOM:
			clazz = AdditionalMode.class;
			break;
		case LANGUAGE:
			clazz = LanguageObject.class;
			break;
		case NOMINAL:
			clazz = NameObject.class;
			break;
		case VERBAL:
			clazz = VerbalGroup.class;
			break;
		case COLOR:
			clazz = ColorObject.class;
			break;
		case TRANSPORT:
			clazz = TransportObject.class;
			break;
		case UNIT:
			clazz = UnitObject.class;
			break;
		}

		return clazz;
	}
}

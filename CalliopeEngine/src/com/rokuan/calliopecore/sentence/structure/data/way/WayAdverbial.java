package com.rokuan.calliopecore.sentence.structure.data.way;

import com.google.gson.annotations.Expose;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.ColorObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.ComplementObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.LanguageObject;
import com.rokuan.calliopecore.sentence.structure.data.nominal.VerbalGroup;

public abstract class WayAdverbial implements IWayObject {
	public enum WayType {
		CUSTOM,
		VERBAL,
		NOMINAL,
		LANGUAGE,
		COLOR,
		TRANSPORT
	}

	public enum WayContext {
		BY
	}

	@Expose
	private WayContext wayPreposition;

	@Override
	public WayContext getWayPreposition() {
		return wayPreposition;
	}

	@Override
	public void setWayPreposition(WayContext prep) {
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
			clazz = ComplementObject.class;
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
		}

		return clazz;
	}
}

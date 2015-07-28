package com.rokuan.calliopecore.sentence.structure.common;

import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial;
import com.rokuan.calliopecore.sentence.structure.data.time.TimeAdverbial;
import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;
import com.rokuan.calliopecore.sentence.structure.way.WayAdverbial;

public interface IComplementContent {
	void setDirectObject(NominalGroup direct);
	void setIndirectObject(NominalGroup indirect);
	void setPlaceAdverbial(PlaceAdverbial place);
	void setTimeAdverbial(TimeAdverbial time);
	void setWayAdverbial(WayAdverbial way);
	void setPurposeAdverbial(PurposeAdverbial purpose);
	
	NominalGroup getDirectObject();
	NominalGroup getIndirectObject();
	PlaceAdverbial getPlaceAdverbial();
	TimeAdverbial getTimeAdverbial();
	WayAdverbial getWayAdverbial();
	PurposeAdverbial getPurposeAdverbial();
}

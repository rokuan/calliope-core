package com.rokuan.calliopecore.sentence.structure.common;

import com.rokuan.calliopecore.sentence.structure.nominal.NominalGroup;

public interface IComplementContent {
	void setDirectObject(NominalGroup direct);
	void setIndirectObject(NominalGroup indirect);
	void setPlaceAdverbial(NominalGroup place);
	void setTimeAdverbial(NominalGroup time);
	void setWayAdverbial(NominalGroup way);
	void setPurposeAdverbial(NominalGroup purpose);
	
	NominalGroup getDirectObject();
	NominalGroup getIndirectObject();
	NominalGroup getPlaceAdverbial();
	NominalGroup getTimeAdverbial();
	NominalGroup getWayAdverbial();
	NominalGroup getPurposeAdverbial();
}

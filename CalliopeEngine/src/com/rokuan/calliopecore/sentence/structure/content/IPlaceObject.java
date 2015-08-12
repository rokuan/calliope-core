package com.rokuan.calliopecore.sentence.structure.content;

import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceContext;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;

public interface IPlaceObject {
	PlaceType getPlaceType();
	PlaceContext getPlacePreposition();
	void setPlacePreposition(PlaceContext prep);
}

package com.rokuan.calliopecore.sentence.structure.content;

import com.rokuan.calliopecore.sentence.IPlacePreposition;
import com.rokuan.calliopecore.sentence.structure.data.place.PlaceAdverbial.PlaceType;

public interface IPlaceObject {
    PlaceType getPlaceType();

    IPlacePreposition getPlacePreposition();

    void setPlacePreposition(IPlacePreposition prep);
}

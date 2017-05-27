package com.rokuan.calliopecore.sentence.structure.data.place;

public class AddressObject extends PlaceAdverbial {
    public String number;
    public String streetType;
    public String addressName;

    @Override
    public PlaceType getPlaceType() {
        return PlaceType.ADDRESS;
    }
}

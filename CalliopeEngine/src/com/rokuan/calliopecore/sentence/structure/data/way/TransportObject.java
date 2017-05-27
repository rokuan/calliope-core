package com.rokuan.calliopecore.sentence.structure.data.way;


public class TransportObject extends WayAdverbial {
    public enum TransportType {
        WALK,
        BUS,
        CAR,
        TRAIN,
        PLANE,
        BOAT,
        BIKE
    }

    public TransportType transportType = TransportType.WALK;

    public TransportObject() {

    }

    @Override
    public WayType getWayType() {
        return WayType.TRANSPORT;
    }
}

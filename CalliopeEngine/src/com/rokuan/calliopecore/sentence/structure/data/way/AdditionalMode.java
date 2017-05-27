package com.rokuan.calliopecore.sentence.structure.data.way;

import com.rokuan.calliopecore.sentence.ICustomMode;


public class AdditionalMode extends WayAdverbial {
    public ICustomMode mode;

    @Override
    public WayType getWayType() {
        return WayType.CUSTOM;
    }
}

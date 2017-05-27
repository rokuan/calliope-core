package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.IColorInfo;
import com.rokuan.calliopecore.sentence.IWayPreposition;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;

public class ColorObject extends NominalGroup implements IWayObject {
    private IWayPreposition wayPreposition;
    public IColorInfo color;

    @Override
    public GroupType getGroupType() {
        return GroupType.COLOR;
    }

    @Override
    public WayType getWayType() {
        return WayType.COLOR;
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

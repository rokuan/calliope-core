package com.rokuan.calliopecore.sentence.structure.data.nominal;

import com.rokuan.calliopecore.sentence.ILanguageInfo;
import com.rokuan.calliopecore.sentence.IWayPreposition;
import com.rokuan.calliopecore.sentence.structure.content.IWayObject;
import com.rokuan.calliopecore.sentence.structure.data.way.WayAdverbial.WayType;

public class LanguageObject extends NominalGroup implements IWayObject {
    private IWayPreposition wayPreposition;
    public ILanguageInfo language;

    @Override
    public WayType getWayType() {
        return WayType.LANGUAGE;
    }

    @Override
    public GroupType getGroupType() {
        return GroupType.LANGUAGE;
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

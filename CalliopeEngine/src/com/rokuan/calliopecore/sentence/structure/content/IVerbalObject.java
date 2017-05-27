package com.rokuan.calliopecore.sentence.structure.content;

import com.rokuan.calliopecore.sentence.ActionObject;

public interface IVerbalObject {
    INominalObject getSubject();

    void setSubject(INominalObject subject);

    ActionObject getAction();

    void setAction(ActionObject action);

    INominalObject getDirectObject();

    void setDirectObject(INominalObject dObject);

    INominalObject getTarget();

    void setTarget(INominalObject target);

    IPlaceObject getPlaceAdverbial();

    void setPlaceAdverbial(IPlaceObject pObject);

    ITimeObject getTimeAdverbial();

    void setTimeAdverbial(ITimeObject tObject);

    IWayObject getWayAdverbial();

    void setWayAdverbial(IWayObject wObject);

    IPurposeObject getPurposeAdverbial();

    void setPurposeAdverbial(IPurposeObject pObject);
}

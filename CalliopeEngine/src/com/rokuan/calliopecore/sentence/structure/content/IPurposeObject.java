package com.rokuan.calliopecore.sentence.structure.content;

import com.rokuan.calliopecore.sentence.IPurposePreposition;
import com.rokuan.calliopecore.sentence.structure.data.purpose.PurposeAdverbial.PurposeType;

public interface IPurposeObject {
    PurposeType getPurposeType();

    IPurposePreposition getPurposePreposition();

    void setPurposePreposition(IPurposePreposition prep);
}

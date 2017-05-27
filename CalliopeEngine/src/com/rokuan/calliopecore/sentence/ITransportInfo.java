package com.rokuan.calliopecore.sentence;

import com.rokuan.calliopecore.sentence.structure.data.way.TransportObject.TransportType;

public interface ITransportInfo extends IValue {
    TransportType getTransportType();
}

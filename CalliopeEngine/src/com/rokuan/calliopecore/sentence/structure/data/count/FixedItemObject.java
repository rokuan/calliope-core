package com.rokuan.calliopecore.sentence.structure.data.count;

public class FixedItemObject extends CountObject {
    public long position = 1;

    public FixedItemObject(long pos) {
        super(CountType.FIXED);
        position = pos;
    }
}

package com.rokuan.calliopecore.sentence.structure.data.count;

/**
 * Created by LEBEAU Christophe on 01/03/2015.
 */
public class CountObject {
    public enum Range {
        FIRST,
        LAST,
        FIXED
    }

    public enum CountType {
        ALL,
        LIMIT
    }

    public long position = 1;
    public Range range = Range.FIRST;
    public CountType countType = CountType.ALL;
    public long count = 1;
}

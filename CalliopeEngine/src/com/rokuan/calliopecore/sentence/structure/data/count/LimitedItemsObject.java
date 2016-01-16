package com.rokuan.calliopecore.sentence.structure.data.count;

public class LimitedItemsObject extends CountObject {
    public Range range = Range.FIRST;
    public long count = 1;
	
	public LimitedItemsObject(Range r, long c) {
		super(CountType.LIMIT);
		range = r;
		count = c;
	}
}

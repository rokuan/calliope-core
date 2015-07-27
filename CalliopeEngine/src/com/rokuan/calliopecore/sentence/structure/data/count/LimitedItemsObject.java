package com.rokuan.calliopecore.sentence.structure.data.count;

import com.google.gson.annotations.Expose;


public class LimitedItemsObject extends CountObject {
	@Expose
    public Range range = Range.FIRST;
	
	@Expose
    public long count = 1;
	
	public LimitedItemsObject(Range r, long c) {
		super(CountType.LIMIT);
		range = r;
		count = c;
	}
}

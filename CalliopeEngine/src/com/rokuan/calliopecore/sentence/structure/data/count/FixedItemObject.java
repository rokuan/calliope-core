package com.rokuan.calliopecore.sentence.structure.data.count;

import com.google.gson.annotations.Expose;

public class FixedItemObject extends CountObject {
	@Expose
	public long position = 1;

	public FixedItemObject(long pos) {
		super(CountType.FIXED);
		position = pos;
	}	
}

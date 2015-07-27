package com.rokuan.calliopecore.sentence.structure.data.count;

import com.google.gson.annotations.Expose;

public class QuantityObject extends CountObject {
	@Expose
	public int quantity = 1;
	
	public QuantityObject(int qty){
		super(CountType.QUANTITY);
		quantity = qty;
	}
}

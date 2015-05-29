package com.rokuan.calliopecore.sentence.structure.data.count;

import java.util.HashSet;
import java.util.Set;

public class MultipleItemsObject extends CountObject {
	public Set<Integer> items = new HashSet<Integer>();
	
	public MultipleItemsObject(Set<Integer> i){
		super(CountType.MULTIPLE);
		this.items.addAll(i);
	}
}

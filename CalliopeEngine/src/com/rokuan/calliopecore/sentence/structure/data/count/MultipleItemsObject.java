package com.rokuan.calliopecore.sentence.structure.data.count;

import java.util.Arrays;
import java.util.Set;

import com.google.gson.annotations.Expose;

public class MultipleItemsObject extends CountObject {
	@Expose
	public Integer[] items;
	
	public MultipleItemsObject(Set<Integer> i){
		super(CountType.MULTIPLE);
		
		if(i.size() > 0){
			items = new Integer[i.size()];
			i.toArray(items);
			Arrays.sort(items);
		} 
	}
}

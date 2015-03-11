package com.rokuan.calliopecore.source;

import java.util.List;

import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.criteria.CriterionObject;

public interface DataSource<DataType> {	
	public DataSource<DataType> getData(CountObject count);
	public DataSource<DataType> getData(CountObject count, List<CriterionObject> criteria);
		
	public DataType getData(int index);
	public DataType previousData();
	public DataType currentData();
	public DataType nextData();
	
	public boolean hasPreviousData();
	public boolean hasNextData();
}

package com.rokuan.calliopecore.source;

import java.util.List;

import com.rokuan.calliopecore.sentence.structure.data.count.CountObject;
import com.rokuan.calliopecore.sentence.structure.data.criteria.CriterionObject;

public interface DataSource<DataType> {
    DataSource<DataType> getData(CountObject count);

    DataSource<DataType> getData(CountObject count, List<CriterionObject> criteria);

    DataSource<DataType> removeData(CountObject count);

    DataSource<DataType> removeData(CountObject count, List<CriterionObject> criteria);

    DataType getData(int index);

    DataType removeData(int index);

    DataType previousData();

    DataType currentData();

    DataType nextData();

    boolean hasPreviousData();

    boolean hasNextData();
}

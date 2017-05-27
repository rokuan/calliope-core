package com.rokuan.calliopecore.sentence.structure.data.count;

import java.util.Arrays;
import java.util.Set;

public class MultipleItemsObject extends CountObject {
    public Integer[] items;

    public MultipleItemsObject(Set<Integer> i) {
        super(CountType.MULTIPLE);

        if (i.size() > 0) {
            items = new Integer[i.size()];
            i.toArray(items);
            Arrays.sort(items);
        }
    }
}

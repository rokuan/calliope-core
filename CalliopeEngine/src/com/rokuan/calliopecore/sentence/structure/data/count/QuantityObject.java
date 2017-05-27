package com.rokuan.calliopecore.sentence.structure.data.count;

public class QuantityObject extends CountObject {
    public int quantity = 1;

    public QuantityObject(int qty) {
        super(CountType.QUANTITY);
        quantity = qty;
    }
}

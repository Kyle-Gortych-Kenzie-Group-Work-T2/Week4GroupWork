package com.kenzie.groupactivity.bigspender.compare;

import com.kenzie.groupactivity.bigspender.types.CustomerTotalSpend;

import java.util.Comparator;

public class CustomerTotalSpendBySpend implements Comparator<CustomerTotalSpend> {
    @Override
    public int compare(CustomerTotalSpend left, CustomerTotalSpend right) {
        return Long.compare(left.getTotalSpend(),right.getTotalSpend());
    }
}
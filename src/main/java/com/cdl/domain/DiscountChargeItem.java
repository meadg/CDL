package com.cdl.domain;

import com.cdl.domain.price.Price;

public class DiscountChargeItem extends ChargeItem {

    public DiscountChargeItem(StockItem stockItem, String chargeDescription, Price chargeValue) {
        super(stockItem, chargeDescription, chargeValue);
    }

    @Override
    public boolean isChargeItem() {
        return false;
    }

    @Override
    public boolean isDiscountAble() {
        return false;
    }
}

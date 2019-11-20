package com.cdl.domain.charge;

import com.cdl.domain.StockItem;
import com.cdl.domain.price.Price;

public class UnitChargeItem extends ChargeItem {

    public UnitChargeItem(StockItem stockItem, String chargeDescription, Price chargeValue) {
        super(stockItem, chargeDescription, chargeValue);
    }

    @Override
    public boolean isChargeItem() {
        return true;
    }

    @Override
    public boolean isDiscountAble() {
        return true;
    }
}

package com.cdl.pricing.rules;

import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.domain.StockItem;
import com.cdl.domain.charge.UnitChargeItem;
import com.cdl.domain.price.UnitPrice;

public class UnitPriceRule implements PriceRule {

    public static final String ITEM_PRICE = "Item price";

    @Override
    public void applyPriceRule(StockItem stockItem, UnitPrice unitPrice, ChargeItemAccumulator chargeItemAccumulator) {
        chargeItemAccumulator.addChargeItem(new UnitChargeItem(stockItem, ITEM_PRICE, unitPrice.getPrice()));

    }
}

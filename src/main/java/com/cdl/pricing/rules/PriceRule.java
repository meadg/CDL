package com.cdl.pricing.rules;


import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.UnitPrice;

public interface PriceRule {
    void applyPriceRule(StockItem stockItem, UnitPrice unitPrice, ChargeItemAccumulator chargeItemAccumulator);
}

package com.cdl.pricing.rules;

import com.cdl.domain.StockItem;

import java.util.HashMap;
import java.util.Map;

public class StockItemPricingRules {

    private Map<StockItem, StockItemPricingRule> itemPricingRules = new HashMap<>();

    public StockItemPricingRule retrievePricingRulesForStockItem(StockItem stockItem) {
        return itemPricingRules.get(stockItem);
    }

    public void addStockItemPricingRule(StockItem stockItem, StockItemPricingRule pricingRule) {
        this.itemPricingRules.put(stockItem, pricingRule);
    }
}

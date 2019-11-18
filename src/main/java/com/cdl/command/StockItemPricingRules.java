package com.cdl.command;

import com.cdl.domain.StockItem;
import com.cdl.pricing.rules.PriceRule;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;

public class StockItemPricingRules {

    private Map<StockItem,StockItemPricingRule> itemPricingRules;

    public StockItemPricingRule retrievePricingRulesForStockItem(StockItem stockItem) {
        return itemPricingRules.get(stockItem);
    }
}

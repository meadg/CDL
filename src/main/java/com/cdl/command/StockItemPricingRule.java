package com.cdl.command;

import com.cdl.domain.StockItem;
import com.cdl.domain.price.UnitPrice;
import com.cdl.pricing.rules.PriceRule;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class StockItemPricingRule {

    private StockItem stockItem;
    private UnitPrice unitPrice;
    private List<PriceRule> priceRules;

    public UnitPrice getUnitPrice() {
        return unitPrice;
    }

    public List<PriceRule> getPriceRules() {
        return priceRules;
    }
}

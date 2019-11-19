package com.cdl.command;

import com.cdl.domain.StockItem;
import com.cdl.domain.price.UnitPrice;
import com.cdl.pricing.rules.PriceRule;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class StockItemPricingRule {

    private StockItem stockItem;
    private UnitPrice unitPrice;
    private List<PriceRule> priceRules;

    private StockItemPricingRule(StockItemPricingRuleBuilder builder) {
        this.stockItem = builder.stockItem;
        this.unitPrice = builder.unitPrice;
        this.priceRules = builder.priceRules;
    }


    public UnitPrice getUnitPrice() {
        return unitPrice;
    }

    public List<PriceRule> getPriceRules() {
        return priceRules;
    }

    public static class StockItemPricingRuleBuilder {

        private StockItem stockItem;
        private UnitPrice unitPrice;
        private List<PriceRule> priceRules = Lists.newArrayList();

        public StockItemPricingRuleBuilder withStockItem(StockItem stockItem) {
            this.stockItem = stockItem;
            return this;
        }

        public StockItemPricingRuleBuilder withUnitPrice(UnitPrice unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public StockItemPricingRuleBuilder withPriceRules(List<PriceRule> priceRules) {
            this.priceRules = priceRules;
            return this;
        }

        public StockItemPricingRule build() {
            return new StockItemPricingRule(this);
        }
    }
}

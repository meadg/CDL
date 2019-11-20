package com.cdl.command.scanning;

import com.cdl.application.CheckOutSession;
import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.UnitPrice;
import com.cdl.pricing.rules.PriceRule;
import com.cdl.pricing.rules.StockItemPricingRule;
import com.cdl.pricing.rules.StockItemPricingRule.StockItemPricingRuleBuilder;
import com.cdl.pricing.rules.StockItemPricingRules;
import com.cdl.pricing.rules.UnitPriceRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StockItemChargingHandler {

    private StockItemPricingRules stockItemPricingRules;

    public StockItemChargingHandler(StockItemPricingRules stockItemPricingRules) {
        this.stockItemPricingRules = stockItemPricingRules;
    }

    public boolean isValidProduct(StockItem stockItem) {
        final StockItemPricingRule stockItemPricingRule = stockItemPricingRules.retrievePricingRulesForStockItem(stockItem);
        if (stockItemPricingRule == null) {
            return false;
        }
        return true;
    }

    public void applyStockItemPricingRules(StockItem stockItem, CheckOutSession checkoutSession) {
        final StockItemPricingRule stockItemPricingRule = stockItemPricingRules.retrievePricingRulesForStockItem(stockItem);

        UnitPrice stockItemUnitPrice = stockItemPricingRule.getUnitPrice();
        ChargeItemAccumulator chargeItemAccumulator = checkoutSession.getItemAccumulator();

        for (PriceRule priceRule : stockItemPricingRule.getPriceRules()) {
            priceRule.applyPriceRule(stockItem, stockItemUnitPrice, chargeItemAccumulator);
        }


    }

    public void addNewProduct(StockItem stockItem, UnitPrice price) {

        List<PriceRule> initialPriceRules = Arrays.asList(new UnitPriceRule());
       StockItemPricingRule stockItemPricingRule = new StockItemPricingRuleBuilder()
                                                        .withStockItem(stockItem)
                                                        .withUnitPrice(price)
                                                        .withPriceRules(initialPriceRules)
                                                        .build();
        stockItemPricingRules.addStockItemPricingRule(stockItem, stockItemPricingRule);
    }
}

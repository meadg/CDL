package com.cdl.command;

import com.cdl.application.CheckOutSession;
import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.UnitPrice;
import com.cdl.pricing.rules.PriceRule;
import com.cdl.pricing.rules.StockItemPricingRule;
import com.cdl.pricing.rules.StockItemPricingRules;

public class StockItemChargingHandler {

    private StockItemPricingRules stockItemPricingRules;

    public StockItemChargingHandler(StockItemPricingRules stockItemPricingRules) {
        this.stockItemPricingRules = stockItemPricingRules;
    }

    public void applyStockItemPricingRules(StockItem stockItem, CheckOutSession checkoutSession) {
        final StockItemPricingRule stockItemPricingRule = stockItemPricingRules.retrievePricingRulesForStockItem(stockItem);

        UnitPrice stockItemUnitPrice = stockItemPricingRule.getUnitPrice();
        ChargeItemAccumulator chargeItemAccumulator = checkoutSession.getItemAccumulator();

        for (PriceRule priceRule : stockItemPricingRule.getPriceRules())
        {
            priceRule.applyPriceRule(stockItem,stockItemUnitPrice,chargeItemAccumulator);
        }


    }


}

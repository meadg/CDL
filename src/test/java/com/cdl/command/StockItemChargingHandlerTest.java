package com.cdl.command;

import com.cdl.application.CheckOutSession;
import com.cdl.charging.ChargeItemAccumulator;
import com.cdl.command.scanning.StockItemChargingHandler;
import com.cdl.domain.StockItem;
import com.cdl.domain.price.UnitPrice;
import com.cdl.pricing.rules.PriceRule;
import com.cdl.pricing.rules.StockItemPricingRule;
import com.cdl.pricing.rules.StockItemPricingRules;
import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StockItemChargingHandlerTest {

    @Mock
    private StockItemPricingRules stockItemPricingRules;
    @Mock
    private StockItem stockItem;
    @Mock
    private PriceRule priceRule1,priceRule2,priceRule3,priceRule4;
    @Mock
    private CheckOutSession checkOutSession;
    @Mock
    private UnitPrice unitPrice;
    @Mock
    private StockItemPricingRule stockItemPricingRule;
    @Mock
    private ChargeItemAccumulator chargeItemAccumulator;

    private StockItemChargingHandler stockItemChargingHandler;
    private List<PriceRule> pricingRules;

    @Before
    public void setUp(){
        pricingRules = Lists.newArrayList(priceRule1,priceRule2,priceRule3,priceRule4);
        when(checkOutSession.getItemAccumulator()).thenReturn(chargeItemAccumulator);
        stockItemChargingHandler = new StockItemChargingHandler(stockItemPricingRules);
    }

    @Test
    public void appliesRulesToSessionAccumulator(){
        when(stockItemPricingRules.retrievePricingRulesForStockItem(stockItem)).thenReturn(stockItemPricingRule);
        when(stockItemPricingRule.getUnitPrice()).thenReturn(unitPrice);
        when(stockItemPricingRule.getPriceRules()).thenReturn(pricingRules);

        stockItemChargingHandler.applyStockItemPricingRules(stockItem,checkOutSession);

        verify(priceRule1).applyPriceRule(stockItem,unitPrice,chargeItemAccumulator);
    }


}